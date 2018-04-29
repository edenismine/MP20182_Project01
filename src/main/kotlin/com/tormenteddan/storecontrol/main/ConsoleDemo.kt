package com.tormenteddan.storecontrol.main

import com.tormenteddan.storecontrol.sandwiches.BaseSandwich
import com.tormenteddan.storecontrol.sandwiches.Sandwich
import com.tormenteddan.storecontrol.sandwiches.ingredients.*
import com.tormenteddan.storecontrol.sandwiches.SandwichStore
import com.tormenteddan.storecontrol.util.Transaction
import java.util.*
import kotlin.collections.Map
import kotlin.collections.arrayListOf
import kotlin.collections.component1
import kotlin.collections.component2
import kotlin.collections.filterValues
import kotlin.collections.hashMapOf
import kotlin.collections.iterator
import kotlin.collections.set
import kotlin.collections.sumBy
import kotlin.collections.toList

val simpleSandwich = BaseSandwich(PLAIN_BOLILLO) + MAYONNAISE +
        LETTUCE + TOMATO
val chickenSandwich = simpleSandwich + CHICKEN
val beefSandwich = simpleSandwich + BEEF_MILANESA
val headSandwich = simpleSandwich + HEAD_CHEESE
val hamSandwich = simpleSandwich + HAM
val tortas = arrayListOf(
        chickenSandwich, beefSandwich, headSandwich, hamSandwich,
        chickenSandwich + WHITE_CHEESE, beefSandwich + WHITE_CHEESE,
        headSandwich + WHITE_CHEESE, hamSandwich + WHITE_CHEESE,
        chickenSandwich + SPANISH_CHEESE, beefSandwich + SPANISH_CHEESE,
        headSandwich + SPANISH_CHEESE, hamSandwich + SPANISH_CHEESE
)

val torteriaName = "Juanito's Tortería"

val mainSt =
        SandwichStore(torteriaName, "Main St", mainStInventory,
                tortas)

val teslaAv =
        SandwichStore(torteriaName, "Tesla Av", teslaAvInventory,
                tortas)
/**
 * This is the demo's main function.
 *
 * @author daniel.aragon@ciencias.unam.mx
 */
fun main(args: Array<String>) {
    val supervisor = object : Observer {
        val balance: Int
            get() = balanceMap.toList().sumBy { (_, b) -> b }
        val balanceMap = hashMapOf<SandwichStore, Int>()
        val shoppingMap = hashMapOf<SandwichStore, Map<SandwichIngredient, Int>>()
        val ledger = arrayListOf<Transaction>()

        override fun update(o: Observable?, arg: Any?) {
            println("\u001Bc")
            if (o is SandwichStore) {
                if (arg is Transaction) ledger.add(arg)
                balanceMap[o] = o.balance
                shoppingMap[o] = o.shoppingList
            }
            printDashboard()
        }

        fun printDashboard() {
            val width = 100
            println("|".padEnd(width, '=') + "|")
            println("|" +
                    "DASHBOARD"
                            .padStart((width / 2) + 4)
                            .padEnd(width - 1) + "|")
            println("|".padEnd(width, '=') + "|")

            println("|  Balance: ${balance / 100.0}".padEnd(width) + "|")
            for ((store, balance) in balanceMap) {
                println("|    $store : ${balance / 100.0}".padEnd(width) + "|")
            }

            println("|  Shopping lists:".padEnd(width) + "|")
            for ((store, shoppingList) in shoppingMap) {
                println("|    $store:".padEnd(width) + "|")
                shoppingList.filterValues { it > 0 }.forEach { item, int ->
                    println("|      Missing $int of ${item.description()}".padEnd(width) +
                            "|")
                }
            }

            println("|  Transactions:".padEnd(width) + "|")
            for ((type, origin, concept, cents) in ledger) {
                println("|    $type @$origin for $concept ${cents / 100.0}"
                        .padEnd(width) + "|")
            }

            println("|".padEnd(width, '=') + "|")
        }
    }

    val random = Random()
    mainSt.addObserver(supervisor)
    teslaAv.addObserver(supervisor)

    fun demo(store: SandwichStore) {
        val menu = store.menu
        store.sellSandwich(menu.elementAt(random.nextInt(menu.size)))
        Thread.sleep(1000)
        store.sellSandwich(menu.elementAt(random.nextInt(menu.size)))
        Thread.sleep(1000)
    }
    demo(mainSt)
    demo(teslaAv)
    demo(mainSt)
    for (store in listOf(mainSt, teslaAv))
        for ((k, v) in store.shoppingList.filterValues { it > 0 }) {
            store.buy(k, v)
            Thread.sleep(1000)
        }
}
