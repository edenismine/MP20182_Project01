package com.tormenteddan.storecontrol.stores

import com.tormenteddan.storecontrol.sandwiches.ingredients.Bread
import com.tormenteddan.storecontrol.sandwiches.ingredients.Ingredient
import com.tormenteddan.storecontrol.sandwiches.ingredients.SandwichIngredient
import com.tormenteddan.storecontrol.util.Article
import com.tormenteddan.storecontrol.util.InventoryManager
import com.tormenteddan.storecontrol.util.Transaction
import com.tormenteddan.storecontrol.util.TransactionType
import java.util.*

/**
 * A sandwich store with a [name] located at an [address]. It can calculate
 * its [shopping list][missingArticles] by checking its [inventory]. It notifies
 * its [observers][obs] about its transactions and its [balance].  Its
 * [clerks][clerks] help it [make][SandwichStoreClerk.makeSandwich]
 * and [sell sandwiches][SandwichStoreClerk.sellSandwich].
 *
 * It implements the [InventoryManager] interface which means that
 * administrators, employees or supervisors can also [consume] the store's
 * items or [replenish] them to keep its inventory full.
 *
 * A sandwich store is [Observable], and is usually observed by
 * [some supervisor][SandwichStoreSupervisor].
 *
 * @param name The store's description.
 * @param address The store's address.
 *
 * @property name The store's description.
 * @property address The store's address.
 * @property inventory The store's inventory.
 * @property clerks The store's clerks.
 * @property missingArticles The store's current shopping list.
 * @property balance The store's current balance.
 *
 * @see SandwichStoreSupervisor
 * @see SandwichStoreClerk
 * @see InventoryManager
 * @see Observable
 *
 * @author daniel.aragon@ciencias.unam.mx
 */
abstract class SandwichStore
(val name: String, val address: String) : Observable(), Observer,
        InventoryManager {
    protected abstract var inventory : Collection<Article>

    protected var clerks = emptyList<SandwichStoreClerk>()

    val missingArticles: List<Article>
        get() = this.inventory.filter{ (it.required - it.current) >= 0 }

    var balance : Int = 0
        private set

    /**
     * Creates a new Clerk
     */
    fun addNewClerk(name: String) : SandwichStoreClerk {
        val new = object : SandwichStoreClerk(this@SandwichStore, name){}
        clerks += new
        return new
    }

    /**
     * Unsafe method that modifies the current quantity of a an [ingredient]
     * inside the store's [inventory]. It adds the given [amount] to the
     * item's entry but doesn't check if the amount is positive
     * or negative.
     *
     * @param ingredient The ingredient.
     * @param amount The quantity that should be added to the
     * [ingredient's][ingredient] current quantity inside the store's
     * [inventory].
     *
     * @return true if the store's inventory changed as result of this call.
     */
    private fun Collection<Article>.unsafeMod(
            ingredient: SandwichIngredient, amount: Int = 1): Boolean {
        val (description, cost) = when (ingredient) {
            is Bread -> ingredient.type to ingredient.cost
            is Ingredient -> ingredient.description to ingredient.cost
        }
        val candidate = this.find {
            it.description == description && it.cost == cost
        }
        if(candidate == null) {
            return false
        } else {
            candidate.current += amount
            return amount != 0
        }
    }

    /**
     * Creates a string representation of the store:
     *
     * ```kotlin
     * "$name located at $address"
     * ```
     *
     * @return A string representation of the store.
     */
    override fun toString(): String {
        return "$name located at $address"
    }

    override fun consume(item: Any?, amount: Int): Boolean {
        if (amount <= 0) return false

        if (item is SandwichIngredient){
            val success = inventory.unsafeMod(item, -amount)
            if (success){
                setChanged()
                notifyObservers()
            }
            return success
        } else if (item is Article){
            val candidate = inventory.find {it == item}
            if(candidate == null) {
                return false
            } else if(candidate.current >= amount){
                candidate.current -= amount
                setChanged()
                notifyObservers()
                return true
            }
        }
        return false
    }

    override fun replenish(item: Any?, amount: Int): Boolean {
        if (amount <= 0) return false

        if (item is SandwichIngredient){
            val success = inventory.unsafeMod(item, amount)
            if (success){
                setChanged()
                notifyObservers()
            }
            return success
        } else if (item is Article){
            val candidate = inventory.find {it == item}
            if(candidate == null) {
                return false
            } else {
                candidate.current += amount
                setChanged()
                notifyObservers()
                return true
            }
        }
        return false
    }

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an [Observable] object's
     * [notifyObservers][Observable.notifyObservers] method to have all the
     * object's observers notified of the change.
     *
     * @param o the observable object.
     * @param arg argument passed to the
     * [notifyObservers][Observable.notifyObservers] method.
     */
    override fun update(o: Observable?, arg: Any?) {
        if (arg is Transaction)
            balance += when(arg.type){
                TransactionType.EARNED -> arg.cents
                TransactionType.SPENT -> -arg.cents
            }
        setChanged()
        notifyObservers(arg)
    }
}