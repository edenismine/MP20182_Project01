package com.tormenteddan.storecontrol.demos

import com.tormenteddan.storecontrol.stores.SandwichStore
import com.tormenteddan.storecontrol.util.Article

/**
 * This object represent the Tortería at [ADDRESS_TESLA_AV].
 */
object TeslaAvTorteria : SandwichStore(TORTERIA, ADDRESS_TESLA_AV, TORTERIA_MENU) {
    init {
        makeInventory()
        addObserver(Supervisor)
    }

    override fun makeInventory(): Boolean {
        if (inventory.isEmpty()) {
            inventory = TORTA_COMPONENTS
                    .mapIndexed { index, it ->
                        Article(index, it.description(), it.cost(), 100, 100)
                    }
                    .toSet()
        }
        return inventory.isNotEmpty()
    }
}