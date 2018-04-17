package mx.unam.fciencias.mp.sandwiches

class BaseSandwich(private val type: BreadType) : Torta() {
    override fun getIngredients(): MutableList<String> {
        return arrayListOf(type.type)
    }

    override fun getCost(): Int {
        return type.cost
    }
}