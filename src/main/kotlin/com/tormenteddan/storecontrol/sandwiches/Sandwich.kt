package com.tormenteddan.storecontrol.sandwiches

import com.tormenteddan.storecontrol.sandwiches.ingredients.BRACKET
import com.tormenteddan.storecontrol.sandwiches.ingredients.Bread
import com.tormenteddan.storecontrol.sandwiches.ingredients.Ingredient

/**
 * A sandwich.
 */
interface Sandwich {
    /**
     * The base of the sandwich, aka the bread.
     */
    val base: Bread
    /**
     * A list of this sandwich's ingredients.
     */
    val ingredients: List<Ingredient>
    /**
     * The calculated cost (in cents) of making this sandwich.
     */
    val cost: Int
    /**
     * The retail price (in cents) of this sandwich.
     */
    val price: Int
        get() = (cost * BRACKET).toInt()

    /**
     * Returns the sandwich that would result from swapping this sandwich's
     * base with the provided bread type.
     *
     * @param bread the new bread type.
     * @return the sandwich that would result from swapping this sandwich's
     * base with the provided bread type.
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
            SandwichDecorator(ingredient, this)

    /**
     * Given an ingredient, returns the sandwich that would result from
     * removing one piece of that ingredient. If the sandwich doesn't have
     * any pieces of that particular ingredient, the sandwich itself is
     * returned.
     *
     * @param ingredient the ingredient that should be removed.
     * @return returns the sandwich that would result from
     * removing one piece of that ingredient. If the sandwich doesn't have
     * any pieces of that particular ingredient, the sandwich itself is
     * returned.
     */
    operator fun minus(ingredient: Ingredient): Sandwich
}