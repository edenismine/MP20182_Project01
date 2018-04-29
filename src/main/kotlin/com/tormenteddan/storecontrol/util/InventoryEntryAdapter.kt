package com.tormenteddan.storecontrol.util

/**
 * Bijective mapper that transforms objects of type [T] into [InventoryEntry]
 * and vice versa.
 *
 * @param T Objects of this type can be transformed back and forth between
 * [inventory entries][InventoryEntry] using this class.
 *
 * @see Inventory
 * @see InventoryEntry
 *
 * @author daniel.aragon@ciencias.unam.mx
 */
interface InventoryEntryAdapter<T> {
    /**
     * Given an [item], retrieves its corresponding inventory `entry` such that
     * > `item = toItem(entry)`
     *
     * @return the inventory `entry`
     */
    fun toInventoryEntry(item: T, current:Int, required: Int) : InventoryEntry
    /**
     * Given an inventory [entry], retrieves the `object` such that
     *
     * > `entry = toInventoryEntry(object, current, required)`
     *
     * where `current` and `required` correspond to [InventoryEntry.current]
     * and [InventoryEntry.required].
     *
     * @return the `object`
     */
    fun toItem(entry: InventoryEntry) : T
}