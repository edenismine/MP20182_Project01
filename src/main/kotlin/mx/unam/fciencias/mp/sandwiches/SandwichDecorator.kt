package mx.unam.fciencias.mp.sandwiches

class SandwichDecorator(
        private val ingredient: Ingredient,
        private val sandwich: Sandwich
) : Sandwich {
    override val ingredients: List<String>
        get() {
            val ingredients = ArrayList(sandwich.ingredients)
            ingredients.add(ingredient.ingredient)
            return ingredients.toList()
        }
    override val cost: Int
        get() = sandwich.cost + ingredient.cost
    override val base: String
        get() = sandwich.base
}