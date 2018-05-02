package com.tormenteddan.sandwiches

import com.tormenteddan.sandwiches.ingredients.BRACKET
import com.tormenteddan.sandwiches.ingredients.Bread
import com.tormenteddan.sandwiches.ingredients.Ingredient
import com.tormenteddan.sandwiches.ingredients.SandwichIngredient

/**
 * A sandwich.
 *
 * @property name Optional sandwich description.
 * @property base The base of the sandwich, aka the bread.
 * @property ingredients A complete list of the sandwich's ingredients.
 * @property cost The cost (in cents) of the sandwich.
 * @property price The retail price (in cents) of the sandwich.
 *
 * @see SandwichIngredient
 * @see BaseSandwich
 * @see DecoratedSandwich
 *
 * @author daniel.aragon@ciencias.unam.mx
 */
interface Sandwich {
    val name: String
    val base: Bread
    val ingredients: List<SandwichIngredient>
    val cost: Int
    val price: Int
        get() = (cost * BRACKET).toInt()

    /**
     * Returns the sandwich that would result from swapping this sandwich's
     * base with the provided bread.
     *
     * @param bread the new bread.
     * @return the sandwich that would result from swapping this sandwich's
     * base with the provided bread.
     */
    operator fun invoke(bread: Bread): Sandwich

    /**
     * Returns the sandwich that would result from adding a new ingredient to
     * this sandwich.
     *
     * @param ingredient the new ingredient.
     * @return the sandwich that would result from adding a new ingredient to
     * this sandwich.
     */
    operator fun plus(ingredient: Ingredient): Sandwich =
            DecoratedSandwich(ingredient, this)

    /**
     * Given an [ingredient], returns the sandwich that would result from
     * removing one piece of that ingredient. If the sandwich doesn't have
     * any pieces of that particular [ingredient], the sandwich itself is
     * returned.
     *
     * @param ingredient the ingredient that should be removed.
     * @return returns the sandwich that would result from
     * removing one piece of that ingredient. If the sandwich doesn't have
     * any pieces of that particular ingredient, the sandwich itself is
     * returned.
     */
    operator fun minus(ingredient: Ingredient): Sandwich

    /**
     * Returns true if [ingredient] is found in sandwiches [ingredients].
     */
    operator fun contains(ingredient: SandwichIngredient): Boolean {
        return ingredients.contains(ingredient)
    }
}