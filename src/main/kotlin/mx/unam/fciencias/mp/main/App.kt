package mx.unam.fciencias.mp.main

import mx.unam.fciencias.mp.sandwiches.Ingredient
import mx.unam.fciencias.mp.sandwiches.BaseSandwich
import mx.unam.fciencias.mp.sandwiches.BreadType
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
                                                    BaseSandwich(BreadType.PLAIN_BOLILLO)
                                            )))))
    miltorta.getIngredients().forEach {
        println("Ingrediente: $it")
    }
    println("Costo total: ${miltorta.getCost() / 100.0}")
    println("Precio total: ${miltorta.getPrice()}")
}
