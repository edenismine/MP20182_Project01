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
                    Article(0, WHITE_CHEESE_TAG, WHITE_CHEESE_COST, 100, 100),
                    Article(1, SPANISH_CHEESE_TAG, SPANISH_CHEESE_COST, 100,
                            100),
                    Article(2, TOMATO_TAG, TOMATO_COST, 100, 100),
                    Article(3, LETTUCE_TAG, LETTUCE_COST, 100, 100),
                    Article(4, HAM_TAG, HAM_COST, 100, 100),
                    Article(5, CHICKEN_TAG, CHICKEN_COST, 100, 100),
                    Article(6, HEAD_CHEESE_TAG, HEAD_CHEESE_COST, 100, 100),
                    Article(7, BEEF_MILANESA_TAG, BEEF_MILANESA_COST, 100, 100),
                    Article(8, MAYONNAISE_TAG, MAYONNAISE_COST, 100, 100),
                    Article(9, MUSTARD_TAG, MUSTARD_COST, 100, 100),
                    Article(10, KETCHUP_TAG, KETCHUP_COST, 100, 100),
                    Article(11, PLAIN_BOLILLO_TAG, PLAIN_BOLILLO_COST, 100,
                            100),
                    Article(12, WHOLEGRAIN_BOLILLO_TAG,
                            WHOLEGRAIN_BOLILLO_COST, 100, 100))
            for (article in array)
                inventory += article
        }
        return inventory.isNotEmpty()
    }
}