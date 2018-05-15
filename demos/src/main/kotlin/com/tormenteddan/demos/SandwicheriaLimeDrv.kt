package com.tormenteddan.demos

import com.tormenteddan.sandwiches.Sandwich
import com.tormenteddan.sandwiches.ingredients.Ingredient
import com.tormenteddan.stores.SandwichStore
import com.tormenteddan.stores.SandwichStoreClerk
import com.tormenteddan.util.Article

/**
 * This object represent the Sandwichería at [ADDRESS_LIME_DR].
 */
object SandwicheriaLimeDrv : SandwichStore(SANDWICHERIA, ADDRESS_LIME_DR, SANDWICHERIA_MENU) {
    init {
        populateInventory()
        addObserver(Supervisor)
    }

    /** Tag corresponding to a bread discount*/
    const val BREAD_DISCOUNT_TAG = "Descuento de pan"

    /** Cost (in cents) of a bread discount */
    const val BREAD_DISCOUNT_COST = -50

    /** Plain bread discount */
    val BREAD_DISCOUNT = Ingredient(BREAD_DISCOUNT_TAG, BREAD_DISCOUNT_COST)

    /**
     * Clerks of the Lime Drv Sandwichería must add a bread discount to every
     * sandwich they make.
     */
    override fun buildClerk(name: String): SandwichStoreClerk {
        return object : SandwichStoreClerk(this@SandwicheriaLimeDrv, name) {
            /**
             * Adds the bread discount to a sandwich if it doesn't have it yet.
             */
            override fun Sandwich.withDiscounts(): Sandwich {
                return if (BREAD_DISCOUNT !in this)
                    this + BREAD_DISCOUNT else this
            }
        }
    }

    override fun populateInventory(): Boolean {
        if (inventory.isEmpty()) {
            inventory = arrayListOf(
                    Article(0, address, LETTUCE_TAG, LETTUCE_COST, 100, 100),
                    Article(1, address, HAM_TAG, HAM_COST, 100, 100),
                    Article(2, address, CHICKEN_TAG, CHICKEN_COST, 100, 100),
                    Article(3, address, HEAD_CHEESE_TAG, HEAD_CHEESE_COST, 100, 100),
                    Article(4, address, SAUSAGE_TAG, SAUSAGE_COST, 100, 100),
                    Article(5, address, TOMATO_TAG, TOMATO_COST, 100, 100),
                    Article(6, address, WHITE_CHEESE_TAG, WHITE_CHEESE_COST, 100, 100),
                    Article(7, address, MAYONNAISE_TAG, MAYONNAISE_COST, 100, 100),
                    Article(8, address, KETCHUP_TAG, KETCHUP_COST, 100, 100),
                    Article(9, address, PLAIN_BOLILLO_TAG, PLAIN_BOLILLO_COST, 100, 100),
                    Article(10, address, WHOLEGRAIN_BOLILLO_TAG,
                            WHOLEGRAIN_BOLILLO_COST, 100, 100),
                    Article(11, address, BREAD_DISCOUNT_TAG,
                            BREAD_DISCOUNT_COST,
                            2147483647, 2147483647))
        }
        return inventory.isNotEmpty()
    }
}