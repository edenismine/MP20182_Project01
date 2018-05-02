package com.tormenteddan.util

/**
 * An inventory article should have a unique [id], an identifying [description]
 * and an associated [cost].
 *
 * It should also have a pair of associated quantities:
 *
 * 1. [current] quantity.
 * 2. [required] quantity.
 *
 * @property id Item's unique id.
 * @property description Item's identifying description.
 * @property cost Item's associated cost. Its backing property is
 * [unsafeCost] and defaults to 0 if it's negative.
 * @property current The current quantity of this particular item inside an
 * inventory. Its backing property is [unsafeCurrent] and defaults to 0 if it's
 * negative.
 * @property required The required quantity of this particular item inside an
 * inventory. Its backing property is [unsafeRequired] and defaults to 0 if it's
 * negative.
 *
 * @param unsafeCost unchecked initial cost. May be negative.
 * @param unsafeCurrent unchecked initial quantity. May be negative.
 * @param unsafeRequired unchecked initial required quantity. May be negative.
 *
 *
 * @author daniel.aragon@ciencias.unam.mx
 */
data class Article(
        val id: Int,
        val description: String,
        var unsafeCost: Int,
        var unsafeCurrent: Int,
        var unsafeRequired: Int) {
    var cost: Int = if (unsafeCost < 0) 0 else unsafeCost
        get() = if (unsafeCost < 0) 0 else unsafeCost
        set(value) {
            field = if (value < 0) 0 else value
            unsafeCost = value
        }

    var current: Int = if (unsafeCurrent < 0) 0 else unsafeCurrent
        get() = if (unsafeCurrent < 0) 0 else unsafeCurrent
        set(value) {
            field = if (value < 0) 0 else value
            unsafeCurrent = value
        }

    var required: Int = if (unsafeRequired < 0) 0 else unsafeRequired
        get() = if (unsafeRequired < 0) 0 else unsafeRequired
        set(value) {
            field = if (value < 0) 0 else value
            unsafeRequired = value
        }
}