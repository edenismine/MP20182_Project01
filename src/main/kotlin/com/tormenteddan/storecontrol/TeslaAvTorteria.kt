package com.tormenteddan.storecontrol

import com.tormenteddan.storecontrol.sandwiches.ingredients.TORTA_COMPONENTS
import com.tormenteddan.storecontrol.sandwiches.ingredients.cost
import com.tormenteddan.storecontrol.sandwiches.ingredients.description
import com.tormenteddan.storecontrol.stores.SandwichStore
import com.tormenteddan.storecontrol.util.Article

/**
 * This object represent the Tortería at [ADDRESS_TESLA_AV].
 */
object TeslaAvTorteria : SandwichStore(TORTERIA, ADDRESS_TESLA_AV) {
    override var inventory: Collection<Article> = TORTA_COMPONENTS
            .mapIndexed{ index,it->
                Article(index, it.description(), it.cost(), 100, 100)
            }
            .toMutableSet()
        set(value) {
            field = value.distinct().toMutableList()
            setChanged()
            notifyObservers()
        }
}