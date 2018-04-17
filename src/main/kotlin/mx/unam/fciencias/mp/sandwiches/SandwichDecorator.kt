package mx.unam.fciencias.mp.sandwiches

class SandwichDecorator(
        private val ingredient: Ingredient,
        private val torta: Torta
) : Torta() {
    override fun getIngredients(): MutableList<String> {
        val ingredients = ArrayList(torta.getIngredients())
        ingredients.add(ingredient.ingredient)
        return ingredients
    }

    override fun getCost(): Int {
        return torta.getCost() + ingredient.cost
    }
}