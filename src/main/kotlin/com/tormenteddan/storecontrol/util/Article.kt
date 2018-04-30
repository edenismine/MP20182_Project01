package com.tormenteddan.storecontrol.util

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
 * @property cost Item's associated cost. Its backing property is [_cost]
 * and defaults to 0 if it's negative.
 * @property current The current quantity of this particular item inside an
 * inventory. Its backing property is [_current] and defaults to 0 if it's
 * negative.
 * @property required The required quantity of this particular item inside an
 * inventory. Its backing property is [_required] and defaults to 0 if it's
 * negative.
 *
 * @param _cost unchecked initial cost.
 * @param _current unchecked initial quantity.
 * @param _required unchecked initial required quantity.
 *
 *
 * @author daniel.aragon@ciencias.unam.mx
 */
data class Article(
        val id: Int,
        val description: String,
        private var _cost: Int,
        private var _current: Int,
        private var _required: Int) {
    var cost
        get() = if (_cost < 0) 0 else _cost
        set(value) {
            if (value >= 0)
                _cost = value
        }
    var current
        get() = if (_current < 0) 0 else _current
        set(value) {
            if (value >= 0)
                _current = value
        }

    var required
        get() = if (_required < 0) 0 else _required
        set(value) {
            if (value >= 0)
                _required = value
        }

    /**
     * Returns a string representation of the entry.
     */
    override fun toString(): String {
        return "Article(id=$id, description=$description, cost=$cost," +
                "current=$current, required=$required)"
    }
}