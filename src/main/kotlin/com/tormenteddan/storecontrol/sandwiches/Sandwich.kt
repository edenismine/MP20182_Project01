package com.tormenteddan.storecontrol.sandwiches

import com.tormenteddan.storecontrol.util.BRACKET

interface Sandwich {
    val base: String
    val ingredients: List<String>
    val cost: Int
    val price: Int
        get() = (cost * BRACKET).toInt()

    operator fun invoke(bread: Bread) : Sandwich

    operator fun plus(ingredient: Ingredient): Sandwich =
            SandwichDecorator(ingredient, this)

    operator fun minus(ingredient: Ingredient): Sandwich
}