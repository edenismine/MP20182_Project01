package com.tormenteddan.storecontrol.main

import com.tormenteddan.storecontrol.sandwiches.ingredients.*
import com.tormenteddan.storecontrol.util.Inventory
import com.tormenteddan.storecontrol.util.InventoryEntry
import com.tormenteddan.storecontrol.util.InventoryEntryAdapter

object TortaInventoryEntryAdapter: InventoryEntryAdapter<SandwichIngredient>{

    override fun toInventoryEntry(item: SandwichIngredient, current: Int, required: Int): InventoryEntry {
        val (id, description, cost) = item.components()
        return InventoryEntry(id, description, cost, current, required)
    }

    override fun toItem(entry: InventoryEntry): SandwichIngredient {
        return if (entry.description
                        .contains("\\b([bB]olillo|[pP]an)\\b".toRegex()))
            Bread(entry.description, entry.cost)
        else
            Ingredient(entry.description, entry.cost)
    }

    /**
     * Transforms a [SandwichIngredient] into a triple of its associated
     * (unique) id, description and cost (in cents).
     *
     * @return a triple of the ingredient's associated (unique) id,
     * description and cost (in cents).
     */
    private fun SandwichIngredient.components() : Triple<Int, String, Int> {
        return when(this){
            is Bread -> Triple(
                    tortaBread.indexOf(this),
                    this.type, this.cost)
            is Ingredient -> Triple(
                    tortaBread.size + tortaIngredients.indexOf(this),
                    this.description, this.cost)
        }
    }
}
val mainStInventory = Inventory((tortaIngredients + tortaBread),
        TortaInventoryEntryAdapter){100 to 100}
val teslaAvInventory = Inventory(tortaIngredients + tortaBread,
        TortaInventoryEntryAdapter){100 to 100}
