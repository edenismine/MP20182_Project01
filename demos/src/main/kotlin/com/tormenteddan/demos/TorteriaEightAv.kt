package com.tormenteddan.demos

import com.tormenteddan.stores.SandwichStore
import com.tormenteddan.util.Article

/**
 * This object represent the Tortería at [ADDRESS_EIGHT_AV].
 */
object TorteriaEightAv : SandwichStore(TORTERIA, ADDRESS_EIGHT_AV, TORTERIA_MENU) {
    init {
        populateInventory()
        addObserver(Supervisor)
    }

    override fun populateInventory(): Boolean {
        if (inventory.isEmpty()) {
            val array = arrayOf(
                    Article(0, address, WHITE_CHEESE_TAG, WHITE_CHEESE_COST, 100, 100),
                    Article(1, address, SPANISH_CHEESE_TAG, SPANISH_CHEESE_COST, 100,100),
                    Article(2, address, TOMATO_TAG, TOMATO_COST, 100, 100),
                    Article(3, address, LETTUCE_TAG, LETTUCE_COST, 100, 100),
                    Article(4, address, HAM_TAG, HAM_COST, 100, 100),
                    Article(5, address, CHICKEN_TAG, CHICKEN_COST, 100, 100),
                    Article(6, address, HEAD_CHEESE_TAG, HEAD_CHEESE_COST, 100, 100),
                    Article(7, address, BEEF_MILANESA_TAG, BEEF_MILANESA_COST, 100, 100),
                    Article(8, address, MAYONNAISE_TAG, MAYONNAISE_COST, 100, 100),
                    Article(9, address, MUSTARD_TAG, MUSTARD_COST, 100, 100),
                    Article(10, address, KETCHUP_TAG, KETCHUP_COST, 100, 100),
                    Article(11, address, PLAIN_BOLILLO_TAG, PLAIN_BOLILLO_COST, 100,100),
                    Article(12, address, WHOLEGRAIN_BOLILLO_TAG, WHOLEGRAIN_BOLILLO_COST, 100, 100))
            for (article in array)
                inventory += article
        }
        return inventory.isNotEmpty()
    }
}