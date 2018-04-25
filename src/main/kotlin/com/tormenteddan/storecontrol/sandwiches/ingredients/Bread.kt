package com.tormenteddan.storecontrol.sandwiches.ingredients

/**
 * Bread [type] with [cost].
 *
 * @property type Type of bread, e.g. plain white bread.
 * @property cost The cost (in cents) of this type of bread.
 */
enum class Bread(val type: String, val cost: Int) {
    PLAIN_BOLILLO(PLAIN_BOLILLO_TAG, PLAIN_BOLILLO_COST),
    WHOLEGRAIN_BOLILLO(WHOLEGRAIN_BOLILLO_TAG, WHOLEGRAIN_BOLILLO_COST)
}