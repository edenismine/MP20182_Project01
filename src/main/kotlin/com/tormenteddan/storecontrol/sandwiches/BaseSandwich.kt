package com.tormenteddan.storecontrol.sandwiches

import com.tormenteddan.storecontrol.sandwiches.ingredients.Bread
import com.tormenteddan.storecontrol.sandwiches.ingredients.Ingredient

/**
 * Only sandwich implementor with a constructor.
 *
 * @property bread The bread used for this sandwich.
 */
class BaseSandwich(private val bread: Bread) : Sandwich {

    override val ingredients: List<Ingredient>
        get() = listOf()
    override val base: Bread
        get() = bread
    override val cost: Int
        get() = bread.cost

    override operator fun invoke(bread: Bread): Sandwich = BaseSandwich(bread)

    override operator fun minus(ingredient: Ingredient): Sandwich = this
}