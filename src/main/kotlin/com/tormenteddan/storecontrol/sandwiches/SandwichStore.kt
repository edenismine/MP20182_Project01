package com.tormenteddan.storecontrol.sandwiches

import com.tormenteddan.storecontrol.sandwiches.ingredients.Bread
import com.tormenteddan.storecontrol.sandwiches.ingredients.Ingredient
import com.tormenteddan.storecontrol.sandwiches.ingredients.SandwichIngredient
import com.tormenteddan.storecontrol.util.Inventory
import com.tormenteddan.storecontrol.util.Transaction
import com.tormenteddan.storecontrol.util.TransactionType
import java.util.Observable

/**
 * A sandwich store with a [name] located at an [address]. It can calculate
 * its [shopping list][shoppingList] by checking its [inventory]. It notifies
 * its [observers][obs] about its transactions and keeps track of its
 * [balance]. Its main goal is to [sell sandwiches][sellSandwich].
 *
 * Administrators, employees or supervisors can also [buy] the items that the
 * store needs to keep its inventory full.
 *
 * A sandwich store is [Observable]!
 *
 * @param name The store's description.
 * @param address The store's address.
 * @param inventory The store's inventory.
 * @param balance The store's initial balance in cents. Defaults to 0.
 *
 * @property name The store's description.
 * @property address The store's address.
 * @property shoppingList The store's current shopping list.
 * @property balance The store's current balance.
 *
 * @see Inventory
 * @see Observable
 *
 * @author daniel.aragon@ciencias.unam.mx
 */
abstract class SandwichStore
(val name: String, val address: String,
 private val inventory: Inventory<SandwichIngredient>, balance: Int = 0
) : Observable() {

    var balance = balance
        private set

    val shoppingList: Map<SandwichIngredient, Int>
        get() = inventory.missingItems.toMap()

    /**
     * Checks if a given [item] can be found in the store's inventory and if
     * the store has at least a certain [amount] of [item]s available.
     *
     * @return true if there are at least a certain [amount] of [item]s
     * available.
     */
    fun hasEnough(item: SandwichIngredient, amount: Int = 1): Boolean {
        if (amount < 0)
            return true
        val entry = inventory[inventory.idOf(item)]
        return (entry?.current ?: 0) - amount >= 0
    }

    /**
     * Attempts to [buy] a fixed [amount] of a particular [item]. It
     * checks the cost of the item in order to update the store's [balance]
     * accordingly and notify its [obs]ervers.
     *
     * @return true if the inventory changed as a result of this call, false
     * otherwise.
     */
    fun buy(item: SandwichIngredient, amount: Int = 1): Boolean {
        if (amount < 0)
            return false
        val success = inventory.replenish(item, amount)
        if (success) {
            val (cost, concept) = when(item){
                is Bread -> item.cost * amount to item.type
                is Ingredient -> item.cost * amount to item.description
            }
            balance -= cost
            val transaction = Transaction(TransactionType.SPENT,
                    toString(), concept, cost)
            setChanged()
            notifyObservers(transaction)
        }
        return success
    }

    /**
     * After a [sandwich] has been designed, the store will try to prepare it
     * and sell it. If the store doesn't have enough ingredients, then the
     * inventory remains untouched by this call. If the sandwich is sold
     * successfully, [obs]ervers will be notified.
     *
     * @param sandwich The sandwich the store is attempting to prepare and sale.
     *
     * @return true if the inventory changed as a result of this call, false
     * otherwise.
     */
    fun sellSandwich(sandwich: Sandwich): Boolean {
        // Attempt to prepare the sandwich and keep track of the items that
        // are consumed so we can restore them if we can't complete the sandwich
        val accumulator = arrayListOf<SandwichIngredient>()
        val prepared = sandwich.ingredients.all {
            val consumed = inventory.consume(it)
            if(consumed)
                accumulator.add(it)
            return@all consumed
        }
        // If the sandwich cannot be completed, restore the consumed items
        // and return false
        if (!prepared) {
            for(ingredient in accumulator)
                inventory.replenish(ingredient)
        } else {
            // Return true if the sandwich was prepared successfully and
            // notify observers.
            balance += sandwich.price
            val transaction = Transaction(TransactionType.EARNED, toString(),
                    sandwich.name, sandwich.price)
            setChanged()
            notifyObservers(transaction)
        }
        return prepared
    }

    /**
     * This function maps positive integers to default menu items (sandwiches).
     * Implementations should document the mapping.
     *
     * @param index Menu index of the desired item.
     *
     * @return the sandwich that corresponds to the given index.
     */
    abstract fun menuItem(index: Int): Sandwich

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
}
