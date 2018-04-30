package com.tormenteddan.storecontrol.stores

import com.tormenteddan.storecontrol.sandwiches.BaseSandwich
import com.tormenteddan.storecontrol.sandwiches.Sandwich
import com.tormenteddan.storecontrol.sandwiches.ingredients.Bread
import com.tormenteddan.storecontrol.sandwiches.ingredients.Ingredient
import com.tormenteddan.storecontrol.sandwiches.ingredients.SandwichIngredient
import com.tormenteddan.storecontrol.util.Transaction
import com.tormenteddan.storecontrol.util.TransactionType
import java.util.*

/**
 * A clerk's job is to [sell][sellSandwich] and [prepare][makeSandwich]
 * [sandwiches][Sandwich] for its [store]. It
 * [notifies][Observable.notifyObservers] its store whenever it makes a
 * [sale][Transaction].
 *
 * @param name The clerk's name.
 * @param store The store where the clerk works.
 */
abstract class SandwichStoreClerk(val store: SandwichStore, val name: String) :
        Observable() {
    /**
     * When a clerk is created, it automatically adds its store to its
     * observers.
     */
    init {
        addObserver(store)
    }

    /**
     * Given a list of [ingredients], tries to create a [sandwich][Sandwich].
     *
     * @return A sandwich if the [list][ingredients] is valid (has exactly one
     * bread component) or null otherwise.
     */
    fun makeSandwich(ingredients: List<SandwichIngredient>): Sandwich? {
        val (bread, notBread) = ingredients.partition { it is Bread }
        if (bread.size != 1)
            return null
        var sandwich: Sandwich = BaseSandwich(bread.first() as Bread)
        val otherIngredients = notBread.map { it as Ingredient }
        for (ingredient in otherIngredients)
            sandwich += ingredient
        return sandwich
    }

    /**
     * After a [sandwich] has been designed, the clerk will try to prepare it
     * and sell it. If the [store] doesn't have enough ingredients, then its
     * [inventory][SandwichStore.inventory] remains untouched by this call. If the
     * [sandwich] is sold successfully, the clerk's [observers][obs] will be
     * notified.
     *
     * @param sandwich The sandwich the clerk is attempting to prepare and sale.
     *
     * @return true if the [store's][store] [inventory][SandwichStore.inventory]
     * changed as a result of this call, false otherwise.
     */
    fun sellSandwich(sandwich: Sandwich): Boolean {
        val accumulator = arrayListOf<Pair<SandwichIngredient, Int>>()
        val prepared = sandwich.ingredients
                .groupBy{it}.map { (k, v) -> k to v.size }
                .all {
                    (item, amount) ->
                    val consumed = store.consume(item, amount)
                    if (consumed)
                        accumulator.add(item to amount)
                    return@all consumed
                }
        // If the sandwich cannot be completed, restore the consumed items
        // and return false
        if (!prepared) {
            for ((item, amount) in accumulator) store.replenish(item, amount)
        } else {
            // Return true if the sandwich was prepared successfully and
            // notify observers.
            val transaction = Transaction(TransactionType.EARNED, store.address,
                    sandwich.name, sandwich.price)
            setChanged()
            notifyObservers(transaction)
        }
        return prepared
    }


    /**
     * A clerk can only add its [store] to its observers list.
     *
     * @param o The clerk's store.
     */
    final override fun addObserver(o: Observer?) {
        if (o == null)
            throw NullPointerException()
        if (o == store) {
            super.addObserver(o)
        }
    }

    @Deprecated("This does nothing when called by a SandwichStoreClerk")
    final override fun deleteObserver(o: Observer?) {
    }

    @Deprecated("This does nothing when called by a SandwichStoreClerk")
    final override fun deleteObservers() {
    }
}
