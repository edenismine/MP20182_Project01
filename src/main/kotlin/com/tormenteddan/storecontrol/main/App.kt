package com.tormenteddan.storecontrol.main

import com.tormenteddan.storecontrol.sandwiches.*

/**
 * This is the demo's main function
 */
fun main(args: Array<String>) {
    var miltorta: Sandwich =
            BaseSandwich(Bread.PLAIN_BOLILLO) +
            Ingredient.BEEF_MILANESA +
            Ingredient.KETCHUP +
            Ingredient.MAYONNAISE +
            Ingredient.TOMATO +
            Ingredient.SPANISH_CHEESE

    println("Base: ${miltorta.base}")
    miltorta.ingredients.forEach {println("Ingrediente: $it")}
    println("Costo total: ${miltorta.cost / 100.0}")
    println("Precio total: ${miltorta.price / 100.0}")

    println("\nPlease remove beef milanesa\n")

    miltorta -= Ingredient.BEEF_MILANESA
    println("Base: ${miltorta.base}")
    miltorta.ingredients.forEach {println("Ingrediente: $it")}
    println("Costo total: ${miltorta.cost / 100.0}")
    println("Precio total: ${miltorta.price / 100.0}")

    println("\nPlease add chicken\n")

    miltorta += Ingredient.CHICKEN
    println("Base: ${miltorta.base}")
    miltorta.ingredients.forEach {println("Ingrediente: $it")}
    println("Costo total: ${miltorta.cost / 100.0}")
    println("Precio total: ${miltorta.price / 100.0}")

    println("\nPlease make it whole grain\n")

    miltorta = miltorta(Bread.WHOLEGRAIN_BOLILLO)
    println("Base: ${miltorta.base}")
    miltorta.ingredients.forEach {println("Ingrediente: $it")}
    println("Costo total: ${miltorta.cost / 100.0}")
    println("Precio total: ${miltorta.price / 100.0}")
}
