package com.tormenteddan.storecontrol.sandwiches.ingredients

/**
 * [ingredient] with [cost].
 *
 * @property ingredient The ingredient.
 * @property cost The ingredient's cost (in cents).
 */
enum class Ingredient(val ingredient: String, val cost: Int) {
    /** White cheese. */
    WHITE_CHEESE(WHITE_CHEESE_TAG, WHITE_CHEESE_COST),
    /** Spanish cheese. */
    SPANISH_CHEESE(SPANISH_CHEESE_TAG, SPANISH_CHEESE_COST),
    /** Tomato. */
    TOMATO(TOMATO_TAG, TOMATO_COST),
    /** Lettuce. */
    LETTUCE(LETTUCE_TAG, LETTUCE_COST),
    /** Ham. */
    HAM(HAM_TAG, HAM_COST),
    /** Chicken. */
    CHICKEN(CHICKEN_TAG, CHICKEN_COST),
    /** Head cheese. */
    HEAD_CHEESE(HEAD_CHEESE_TAG, HEAD_CHEESE_COST),
    /** Beef milanesa. */
    BEEF_MILANESA(BEEF_MILANESA_TAG, BEEF_MILANESA_COST),
    /** Mayonnaise */
    MAYONNAISE(MAYONNAISE_TAG, MAYONNAISE_COST),
    /** Mustard */
    MUSTARD(MUSTARD_TAG, MUSTARD_COST),
    /** Ketchup */
    KETCHUP(KETCHUP_TAG, KETCHUP_COST)
}

