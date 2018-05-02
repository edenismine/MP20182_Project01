package com.tormenteddan.storecontrol.sandwiches

import com.tormenteddan.storecontrol.sandwiches.ingredients.Bread
import com.tormenteddan.storecontrol.sandwiches.ingredients.Ingredient
import com.tormenteddan.storecontrol.sandwiches.ingredients.SandwichIngredient

/**
 * A sandwich that has a certain kind of [bread] as a base, and a [custom]
 * name.
 *
 * Only [Sandwich] implementor with a constructor.
 *
 * @param bread The bread used for this sandwich.
 * @param custom A custom name for the sandwich.
 *
 * @see Sandwich
 * @see SandwichIngredient
 * @see DecoratedSandwich
 *
 * @author daniel.aragon@ciencias.unam.mx
 */
class BaseSandwich
(private val bread: Bread, private val custom: String? = null) : Sandwich {
    override val name: String
        get() = custom ?: bread.name
    override val ingredients: List<SandwichIngredient>
        get() = listOf(bread)
    override val base: Bread
        get() = bread
    override val cost: Int
        get() = bread.cost

    override operator fun invoke(bread: Bread): Sandwich = BaseSandwich(bread)

    override operator fun minus(ingredient: Ingredient): Sandwich = this
}