package com.tormenteddan.demos

import com.tormenteddan.stores.SandwichStoreSupervisor
import java.util.*

/**
 * The general supervisor of the torterías and sandwicherías.
 */
object Supervisor : SandwichStoreSupervisor() {
    override fun update(o: Observable?, arg: Any?) {
        super.update(o, arg)
        printDashboard()
    }
}