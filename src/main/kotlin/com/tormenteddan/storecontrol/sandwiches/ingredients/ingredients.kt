package com.tormenteddan.storecontrol.sandwiches.ingredients

/**
 * Sealed class. Extended by [Ingredient] and [Bread].
 */
sealed class SandwichIngredient

/**
 * Ingredient with [description] and [cost].
 *
 * @property description The description.
 * @property cost The description's cost (in cents).
 */
data class Ingredient
(val description: String, val cost: Int) : SandwichIngredient()

/**
 * Bread [type] with [cost]. A sandwich will be named according to the type
 * of bread that's used.
 *
 * @property type Type of bread, e.g. plain white bread.
 * @property cost The cost (in cents) of this description of bread.
 * @property name The name of a sandwich that uses this bread.
 */
data class Bread
(val type: String, val cost: Int, val name: String) : SandwichIngredient()

/**
 * Extension function that exhaustively attempts to retrieve a description
 * from a [SandwichIngredient] object.
 */
fun SandwichIngredient.description(): String = when (this) {
    is Bread -> this.type
    is Ingredient -> this.description
}

/**
 * Extension function that exhaustively attempts to retrieve a cost
 * from a [SandwichIngredient] object.
 */
fun SandwichIngredient.cost(): Int = when (this) {
    is Bread -> this.cost
    is Ingredient -> this.cost
}

/** Profit bracket multiplier */
const val BRACKET = 100 / 20.0


/** Tag corresponding to white cheese*/
const val WHITE_CHEESE_TAG = "Queso blanco"
/** Tag corresponding to spanish cheese*/
const val SPANISH_CHEESE_TAG = "Queso manchego"
/** Tag corresponding to tomato*/
const val TOMATO_TAG = "Jitomate"
/** Tag corresponding to lettuce*/
const val LETTUCE_TAG = "Lechuga"
/** Tag corresponding to ham*/
const val HAM_TAG = "Jamón"
/** Tag corresponding to chicken*/
const val CHICKEN_TAG = "Pollo"
/** Tag corresponding to head cheese*/
const val HEAD_CHEESE_TAG = "Queso de puerco"
/** Tag corresponding to beef milanesa*/
const val BEEF_MILANESA_TAG = "Milanesa de res"
/** Tag corresponding to mayonnaise*/
const val MAYONNAISE_TAG = "Mayonesa"
/** Tag corresponding to mustard*/
const val MUSTARD_TAG = "Mostaza"
/** Tag corresponding to ketchup*/
const val KETCHUP_TAG = "Catsup"

/** Tag corresponding to plain bolillo*/
const val PLAIN_BOLILLO_TAG = "Bolillo blanco"
/** Tag corresponding to wholegrain bolillo*/
const val WHOLEGRAIN_BOLILLO_TAG = "Bolillo integral"

/* INGREDIENT COSTS */
/** Cost (in cents) of a portion of white cheese*/
const val WHITE_CHEESE_COST = 100
/** Cost (in cents) of a portion of spanish cheese*/
const val SPANISH_CHEESE_COST = 110
/** Cost (in cents) of a portion of tomato*/
const val TOMATO_COST = 40
/** Cost (in cents) of a portion of lettuce*/
const val LETTUCE_COST = 30
/** Cost (in cents) of a portion of ham*/
const val HAM_COST = 500
/** Cost (in cents) of a portion of chicken*/
const val CHICKEN_COST = 550
/** Cost (in cents) of a portion of head cheese*/
const val HEAD_CHEESE_COST = 450
/** Cost (in cents) of a portion of beef milanesa*/
const val BEEF_MILANESA_COST = 800
/** Cost (in cents) of a portion of mayonnaise*/
const val MAYONNAISE_COST = 30
/** Cost (in cents) of a portion of mustard*/
const val MUSTARD_COST = 30
/** Cost (in cents) of a portion of ketchup*/
const val KETCHUP_COST = 30

/** Cost (in cents) of a portion of plain bolillo*/
const val PLAIN_BOLILLO_COST = 100
/** Cost (in cents) of a portion of wholegrain bolillo*/
const val WHOLEGRAIN_BOLILLO_COST = 150

/** The name of a torta with plain bolillo*/
const val PLAIN_BOLILLO_NAME = "Bolillo blanco"
/** The name of a torta with wholegrain bolillo*/
const val WHOLEGRAIN_BOLILLO_NAME = "Bolillo integral"

/** White cheese. */
val WHITE_CHEESE = Ingredient(WHITE_CHEESE_TAG, WHITE_CHEESE_COST)

/** Spanish cheese. */
val SPANISH_CHEESE = Ingredient(SPANISH_CHEESE_TAG, SPANISH_CHEESE_COST)
/** Tomato. */
val TOMATO = Ingredient(TOMATO_TAG, TOMATO_COST)
/** Lettuce. */
val LETTUCE = Ingredient(LETTUCE_TAG, LETTUCE_COST)
/** Ham. */
val HAM = Ingredient(HAM_TAG, HAM_COST)
/** Chicken. */
val CHICKEN = Ingredient(CHICKEN_TAG, CHICKEN_COST)
/** Head cheese. */
val HEAD_CHEESE = Ingredient(HEAD_CHEESE_TAG, HEAD_CHEESE_COST)
/** Beef milanesa. */
val BEEF_MILANESA = Ingredient(BEEF_MILANESA_TAG, BEEF_MILANESA_COST)
/** Mayonnaise. */
val MAYONNAISE = Ingredient(MAYONNAISE_TAG, MAYONNAISE_COST)
/** Mustard. */
val MUSTARD = Ingredient(MUSTARD_TAG, MUSTARD_COST)
/** Ketchup. */
val KETCHUP = Ingredient(KETCHUP_TAG, KETCHUP_COST)
/** Plain bolillo. */
val PLAIN_BOLILLO = Bread(PLAIN_BOLILLO_TAG, PLAIN_BOLILLO_COST, PLAIN_BOLILLO_NAME)
/** Wholegrain bolillo. */
val WHOLEGRAIN_BOLILLO = Bread(WHOLEGRAIN_BOLILLO_TAG,
        WHOLEGRAIN_BOLILLO_COST, WHOLEGRAIN_BOLILLO_NAME)

/** All torta ingredients. */
val TORTA_INGREDIENTS = listOf(WHITE_CHEESE, SPANISH_CHEESE, TOMATO, LETTUCE,
        HAM, CHICKEN, HEAD_CHEESE, BEEF_MILANESA, MAYONNAISE, MUSTARD, KETCHUP)

/** All torta bread types. */
val TORTA_BREAD = listOf(PLAIN_BOLILLO, WHOLEGRAIN_BOLILLO)

/** All torta components */
val TORTA_COMPONENTS = TORTA_INGREDIENTS + TORTA_BREAD


/**
 * Assigns a sandwich description depending on the bread description.
 *
 * @return A fitting sandwich description.
 */
fun Bread.name(): String {
    return when (type) {
        PLAIN_BOLILLO_TAG -> "Torta simple"
        WHOLEGRAIN_BOLILLO_TAG -> "Torta ingtegral"
        else -> "Generic sandwich"
    }
}
