package com.tormenteddan.storecontrol.sandwiches

class BaseSandwich(private val bread: Bread) : Sandwich {

    override val ingredients: List<String>
        get() = listOf()
    override val base: String
        get() = bread.type
    override val cost: Int
        get() = bread.cost

    override operator fun invoke(bread: Bread): Sandwich = BaseSandwich(bread)

    override operator fun minus(ingredient: Ingredient): Sandwich = this
}