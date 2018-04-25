package com.tormenteddan.storecontrol.sandwiches

class SandwichDecorator(
        private val ingredient: Ingredient,
        private val sandwich: Sandwich) : Sandwich {

    override val ingredients: List<String>
        get() = sandwich.ingredients + ingredient.ingredient
    override val cost: Int
        get() = sandwich.cost + ingredient.cost
    override val base: String
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