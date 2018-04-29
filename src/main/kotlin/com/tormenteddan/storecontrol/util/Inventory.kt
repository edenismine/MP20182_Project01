package com.tormenteddan.storecontrol.util

/**
 * An inventory that allows a client to [consume] and [replenish] existing
 * items. A client can also [add] new entries and access a list of
 * [missing items][missingItems], aka the items (and how many of each
 * one) a client would have to [replenish] to get the inventory to be fully
 * stocked.
 *
 * Several functions work directly with objects of type [T]:
 *
 * 1. [idOf].
 * 2. [add].
 * 3. [replenish].
 * 4. [consume].
 * 5. [unsafeModification].
 *
 * The inventory manages to do this easily with the help of an [adapter].
 *
 * The inventory is [Iterable]! Clients can iterate over the inventory's
 * items.
 *
 * @param entries A collection of objects of type [T].
 * @param adapter An [adapter][InventoryEntryAdapter] that can transform
 * objects of type [T] into [inventory entries][InventoryEntry].
 * @param transformer A transformation that modifies the default current and
 * required quantities for each entry after the inventory is first created.
 * If no transformation is supplied, entries will depend only on the
 * [adapter]'s output.
 *
 * @constructor takes the provided collection, [adapts][adapter] it and
 * adjusts the items' quantities via the provided transformation. The result
 * of this process becomes the underlying [map][entries], which maps unique
 * id's to the entries themselves.
 *
 * @property missingItems The calculated missing quantities for each item.
 * @property adapter The [adapter][InventoryEntryAdapter].
 * @property entries The underlying map, that maps unique id's to the items
 * themselves.
 *
 * @see InventoryEntryAdapter
 * @see InventoryEntry
 *
 * @author daniel.aragon@ciencias.unam.mx
 */
class Inventory<T> (entries: Collection<T>,
                    adapter:InventoryEntryAdapter<T>,
                    transformer: (InventoryEntry) -> Pair<Int, Int> =
                            {it.current to it.required}
) :  Iterable<InventoryEntry>{

    var adapter : InventoryEntryAdapter<T> = adapter
        set(value) {
            val items = entries.map { (_, v) ->
                        Triple(field.toItem(v), v.current, v.required)
                    }
            entries.clear()
            field = value
            val mapped = items.map {(i, c, r) ->
                field.toInventoryEntry(i, c, r)
            }
            for(entry in mapped){
                entries[entry.id] = entry
            }
        }

    val missingItems : List<Pair<T, Int>>
        get() = entries.values
                .map { adapter.toItem(it) to (it.required - it.current) }
                .filter { it.second >= 0 }

    private val entries = entries
            .groupBy { it }
            .map{(k, v) ->
                val entry = adapter.toInventoryEntry(k, v.size, v.size)
                val (current, required) = transformer(entry)
                return@map entry.copy(_current = current, _required = required)
            }
            .groupBy { it.id }
            .mapValues {(_, v) -> v.first()}
            .toMutableMap()

    /**
     * Using the inventory's [adapter] it retrieves the [item]'s
     * corresponding [id][InventoryEntry.id].
     *
     * @return the [item]'s corresponding [id][InventoryEntry.id].
     */
    fun idOf(item: T) : Int {
        return adapter.toInventoryEntry(item, 0,0).id
    }

    /**
     * Gets an entry by id.
     *
     * @param id the entry's id.
     *
     * @return the entry that has such [id] or null if there's no such entry.
     */
    operator fun get(id: Int) : InventoryEntry? {
        return entries[id]
    }

    /**
     * Adds a new [item] with a non-negative [current] and [required]
     * quantities.
     *
     * @param item The new item to be added.
     * @param current Initial non-negative quantity.
     * @param required Required or desired quantity.
     *
     * @return true if the inventory changed as a result of this call, false
     * otherwise.
     */
    fun add(item: T, current: Int, required: Int): Boolean {
        if (current < 0 || required < 0) return false
        val new = adapter.toInventoryEntry(item, current, required)
        val old = entries.put(new.id, new)
        return old != new
    }

    /**
     * Finds an [item] and adds an [amount] to its inventory.
     *
     * @param item The item.
     * @param amount The positive amount that should be added to the item's
     * inventory.
     * @return true if the inventory changed as a result of this call, false
     * otherwise.
     */
    fun replenish(item: T, amount: Int = 1): Boolean {
        if (amount < 0)
            return false
        return unsafeModification(item, amount)
    }

    /**
     * Finds an [item] and subtracts an [amount] from its inventory if the
     * [amount] cannot be covered with the item's inventory, it's not modified.
     *
     * @param item The item of type [T].
     * @param amount The positive amount that should be consumed from the item's
     * inventory.
     * @return true if the inventory changed as a result of this call, false
     * otherwise.
     */
    fun consume(item: T, amount: Int = 1): Boolean {
        if (amount < 0)
            return false
        return unsafeModification(item, -amount)
    }

    /**
     * Finds an item using its [item] and adds an [amount] to its inventory.
     * This method is unsafe as it doesn't check if the [amount] is positive
     * or negative, therefore the behaviour of this method can vary.
     *
     * @param item The item's id
     * @param amount The amount that should be added to the item's inventory.
     * @return true if the inventory changed as a result of this call, false
     * otherwise.
     */
    private fun unsafeModification(item: T, amount: Int = 1): Boolean {
        val old = entries[idOf(item)]
        if (old == null) {
            // if no matching id was found, return false
            return false
        } else {
            // else calculate new value
            val oldAmount = old.current
            val newAmount = oldAmount + amount
            if (newAmount >= 0 && newAmount != oldAmount) {
                // if the new value is not negative, assign it and return true
                old.current = newAmount
                return true
            } else {
                // else return false
                return false
            }
        }
    }

    /**
     * Returns an iterator over the elements of this object.
     */
    override fun iterator(): Iterator<InventoryEntry> {
        return entries.values.iterator()
    }
}
