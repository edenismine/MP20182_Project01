package mx.unam.fciencias.mp.sandwiches

class BaseSandwich(private val bread: Bread) : Sandwich {
    override val ingredients: List<String>
        get() = listOf()
    override val base: String
        get() = bread.type
    override val cost: Int
        get() = bread.cost
}