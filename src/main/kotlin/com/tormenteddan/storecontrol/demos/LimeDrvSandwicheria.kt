package com.tormenteddan.storecontrol.demos

import com.tormenteddan.storecontrol.sandwiches.Sandwich
import com.tormenteddan.storecontrol.sandwiches.ingredients.Ingredient
import com.tormenteddan.storecontrol.sandwiches.ingredients.SandwichIngredient
import com.tormenteddan.storecontrol.stores.SandwichStore
import com.tormenteddan.storecontrol.stores.SandwichStoreClerk
import com.tormenteddan.storecontrol.util.Article

/**
 * This object represent the Sandwichería at [ADDRESS_LIME_DR].
 */
object LimeDrvSandwicheria : SandwichStore(SANDWICHERIA, ADDRESS_LIME_DR, SANDWICHERIA_MENU) {
    init {
        makeInventory()
        addObserver(Supervisor)
    }

    /** Tag corresponding to a bread discount*/
    const val BREAD_DISCOUNT_TAG = "Descuento de pan"

    /** Cost (in cents) of a bread discount */
    const val BREAD_DISCOUNT_COST = -50

    /** Plain bread discount */
    val BREAD_DISCOUNT = Ingredient(BREAD_DISCOUNT_TAG, BREAD_DISCOUNT_COST)

    /**
     * Adds the bread discount to a sandwich if it doesn't have it yet.
     */
    fun Sandwich?.discounted(): Sandwich? {
        this ?: return null
        return if (BREAD_DISCOUNT !in this)
            this + BREAD_DISCOUNT else this
    }

    /**
     * Clerks of the Lime Drv Sandwichería must add a bread discount to every
     * sandwich they make.
     */
    override fun buildClerk(name: String): SandwichStoreClerk {
        return object : SandwichStoreClerk(this@LimeDrvSandwicheria, name) {
            override fun makeSandwich(ingredients: Collection<SandwichIngredient>): Sandwich? =
                    super.makeSandwich(ingredients).discounted()

            override fun makeSandwich(n: Int): Sandwich? =
                    super.makeSandwich(n).discounted()

            override fun sellSandwich(sandwich: Sandwich): Boolean =
                    super.sellSandwich(sandwich.discounted()!!)
        }
    }

    override fun makeInventory(): Boolean {
        if (inventory.isEmpty()) {
            inventory = listOf(
                    Article(0, LETTUCE_TAG, LETTUCE_COST, 100, 100),
                    Article(1, HAM_TAG, HAM_COST, 100, 100),
                    Article(2, CHICKEN_TAG, CHICKEN_COST, 100, 100),
                    Article(3, HEAD_CHEESE_TAG, HEAD_CHEESE_COST, 100, 100),
                    Article(4, SAUSAGE_TAG, SAUSAGE_COST, 100, 100),
                    Article(5, TOMATO_TAG, TOMATO_COST, 100, 100),
                    Article(6, WHITE_CHEESE_TAG, WHITE_CHEESE_COST, 100, 100),
                    Article(7, MAYONNAISE_TAG, MAYONNAISE_COST, 100, 100),
                    Article(8, KETCHUP_TAG, KETCHUP_COST, 100, 100),
                    Article(9, PLAIN_BOLILLO_TAG, PLAIN_BOLILLO_COST, 100, 100),
                    Article(10, WHITE_CHEESE_TAG, WHITE_CHEESE_COST, 100, 100),
                    Article(11, WHOLEGRAIN_BOLILLO_TAG, WHOLEGRAIN_BOLILLO_COST, 100, 100),
                    Article(12, BREAD_DISCOUNT_TAG, BREAD_DISCOUNT_COST, 2147483647,
                            2147483647))
        }
        return inventory.isNotEmpty()
    }
}