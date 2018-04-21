package mx.unam.fciencias.mp.main

import mx.unam.fciencias.mp.sandwiches.Ingredient
import mx.unam.fciencias.mp.sandwiches.BaseSandwich
import mx.unam.fciencias.mp.sandwiches.Bread
import mx.unam.fciencias.mp.sandwiches.SandwichDecorator

/**
 * This is the demo's main function
 */
fun main(args: Array<String>) {
    val miltorta =
            SandwichDecorator(Ingredient.BEEF_MILANESA,
                    SandwichDecorator(Ingredient.MAYONNAISE,
                            SandwichDecorator(Ingredient.LETTUCE,
                                    SandwichDecorator(Ingredient.TOMATO,
                                            SandwichDecorator(Ingredient.WHITE_CHEESE,
                                                    BaseSandwich(Bread.PLAIN_BOLILLO)
                                            )))))
    miltorta.ingredients.forEach {
        println("Ingrediente: $it")
    }
    println("Costo total: ${miltorta.cost / 100.0}")
    println("Precio total: ${miltorta.price / 100.0}")
}
