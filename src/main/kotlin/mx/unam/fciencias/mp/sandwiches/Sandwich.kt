package mx.unam.fciencias.mp.sandwiches

import mx.unam.fciencias.mp.util.BRACKET

interface Sandwich {
    val base : String
    val ingredients: List<String>
    val cost: Int
    val price : Int
        get() = (cost * BRACKET).toInt()
}