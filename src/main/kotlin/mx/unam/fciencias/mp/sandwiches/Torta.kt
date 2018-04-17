package mx.unam.fciencias.mp.sandwiches

import mx.unam.fciencias.mp.util.BRACKET

abstract class Torta {
    abstract fun getIngredients(): MutableList<String>
    abstract fun getCost(): Int
    fun getPrice(): Double {
        return getCost() * BRACKET
    }
}