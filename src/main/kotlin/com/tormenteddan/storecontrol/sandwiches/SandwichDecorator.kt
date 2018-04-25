package com.tormenteddan.storecontrol.sandwiches

import com.tormenteddan.storecontrol.sandwiches.ingredients.Bread
import com.tormenteddan.storecontrol.sandwiches.ingredients.Ingredient

/**
 * Decorated [sandwich] with an [ingredient].
 *
 * @property sandwich the sandwich this decorator is decorating.
 * @property ingredient the ingredient that's added on top of the [sandwich].
 */
class SandwichDecorator(
        private val ingredient: Ingredient,
        private val sandwich: Sandwich) : Sandwich {

    override val ingredients: List<Ingredient>
        get() = sandwich.ingredients + ingredient
    override val cost: Int
        get() = sandwich.cost + ingredient.cost
    override val base: Bread
        get() = sandwich.base

    override operator fun invoke(bread: Bread): Sandwich {
        return SandwichDecorator(ingredient, sandwich.invoke(bread))
    }

    override operator fun minus(ingredient: Ingredient): Sandwich {
        return if (ingredient == this.ingredient) {
            sandwich
        } else {
            SandwichDecorator(this.ingredient, sandwich.minus(ingredient))
        }
    }
}