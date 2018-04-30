package com.tormenteddan.storecontrol

import com.tormenteddan.storecontrol.sandwiches.ingredients.*
import com.tormenteddan.storecontrol.stores.SandwichStoreClerk
import com.tormenteddan.storecontrol.stores.SandwichStoreSupervisor
import java.util.*

/** Name of the torterías restaurant chain */
const val TORTERIA = "Juanito's Tortería"

/** Address of Main St Tortería */
const val ADDRESS_MAIN_ST = "Main St 1031"

/** Address of Tesla Av Tortería */
const val ADDRESS_TESLA_AV = "Tesla Av 72"

/**
 * This function prints a dashboard that displays the supervisor's information.
 */
fun SandwichStoreSupervisor.printDashboard() {
    println("\u001Bc")
    val width = 100
    println("|".padEnd(width, '=') + "|")
    println("|" +
            "DASHBOARD"
                    .padStart((width / 2) + 4)
                    .padEnd(width - 1) + "|")
    println("|".padEnd(width, '=') + "|")

    println("|  Balance: ${globalBalance / 100.0}".padEnd(width) + "|")
    for ((store, balance) in balanceMap) {
        println("|    $store : ${balance / 100.0}".padEnd(width) + "|")
    }

    println("|  Shopping lists:".padEnd(width) + "|")
    for ((store, shoppingList) in shoppingMap) {
        println("|    $store:".padEnd(width) + "|")
        shoppingList.filter { it.required - it.current > 0 }.forEach {
            println(("|      Missing ${it.required - it.current} of " +
                    it.description).padEnd(width) + "|")
        }
    }

    println("|  Transactions:".padEnd(width) + "|")
    for ((type, origin, concept, cents) in ledger) {
        println("|    $type @$origin for $concept ${cents / 100.0}"
                .padEnd(width) + "|")
    }

    println("|".padEnd(width, '=') + "|")
    Thread.sleep(200)
}

/**
 * This function demos the creating of a supervisor, two stores (with on
 * clerk each) and the sale of 6 tortas (2 in one, 4 in the other). The
 * supervisor then replenishes the stores' inventories.
 *
 * @param args console arguments
 */
fun main(args: Array<String>) {
    val simplestTorta = listOf(PLAIN_BOLILLO, LETTUCE, TOMATO, MAYONNAISE,
            MUSTARD, KETCHUP)
    val torteriaMenu = listOf(
            simplestTorta + WHITE_CHEESE,
            simplestTorta + SPANISH_CHEESE,
            simplestTorta + HAM,
            simplestTorta + CHICKEN,
            simplestTorta + HEAD_CHEESE,
            simplestTorta + BEEF_MILANESA,
            simplestTorta + HAM + WHITE_CHEESE,
            simplestTorta + CHICKEN + WHITE_CHEESE,
            simplestTorta + HEAD_CHEESE + WHITE_CHEESE,
            simplestTorta + BEEF_MILANESA + WHITE_CHEESE,
            simplestTorta + HAM + SPANISH_CHEESE,
            simplestTorta + CHICKEN + SPANISH_CHEESE,
            simplestTorta + HEAD_CHEESE + SPANISH_CHEESE,
            simplestTorta + BEEF_MILANESA + SPANISH_CHEESE
    )

    val supervisor = object : SandwichStoreSupervisor(){
        override fun update(o: Observable?, arg: Any?) {
            super.update(o, arg)
            printDashboard()
        }
    }

    val mainStClerk = MainStTorteria.addNewClerk("John")
    MainStTorteria.addObserver(supervisor)
    val teslaAvClerk = TeslaAvTorteria.addNewClerk("John")
    TeslaAvTorteria.addObserver(supervisor)

    val random = Random()

    fun demo(sandwichStoreClerk: SandwichStoreClerk) {
        var sandwich =
                sandwichStoreClerk.makeSandwich(torteriaMenu
                        [random.nextInt(torteriaMenu.size)])
        sandwichStoreClerk.sellSandwich(sandwich!!)
        sandwich =
                sandwichStoreClerk.makeSandwich(torteriaMenu
                        [random.nextInt(torteriaMenu.size)])
        sandwichStoreClerk.sellSandwich(sandwich!!)
    }

    demo(mainStClerk)
    demo(teslaAvClerk)
    demo(mainStClerk)
    supervisor.buyMissingItems()
}