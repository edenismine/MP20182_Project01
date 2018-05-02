package demos

import java.util.*

/**
 * # Main function
 *
 * This function runs a demo depending on the [arguments][args] passed to
 * the program.
 *
 * - If "1" is passed, this function will execute the [first demo][demo1].
 * - If "2" is passed, this function will execute the [second demo][demo2].
 * - Any other option is passed the program will just end.
 *
 * @param args console arguments.
 */
fun main(args: Array<String>) {
    when {
        args.isEmpty() -> println("Empty arguments. Did not run any demo!")
        args.first().toIntOrNull() == 1 -> demo1()
        args.first().toIntOrNull() == 2 -> demo2()
        else -> println("Demo ${args.first()} not recognized.")
    }
}

/** John is a store clerk of the Main St Tortería */
val john = TorteriaMainSt.addNewClerk("John")

/** Joy is a store clerk of the Tesla Blvd Tortería */
val joy = TorteriaTeslaBlvd.addNewClerk("Joy")

/** Kim is a store clerk of the Lime Drv Sandwichería */
val kim = SandwicheriaLimeDrv.addNewClerk("Kim")

/** Jane is a store clerk of the Eight Av Tortería */
val jane = TorteriaEightAv.addNewClerk("Jane")

/** A list of all the store clerks */
val clerks = listOf(john, joy, kim, jane)

/**
 * ## Demo 1
 *
 * Makes each clerk prepare a random menu item from their store.
 *
 * The supervisor replenishes all the stores' inventories afterwards.
 */
fun demo1() {
    val random = Random()
    for (clerk in clerks) {
        val bound = clerk.store.menu.size
        val s = clerk.getSandwich(random.nextInt(bound))
        if (s != null) clerk.sellSandwich(s.ingredients)
    }
    Supervisor.buyMissingItems()
}

/**
 * ## Demo 2
 *
 * It makes the clerks from Main St and Lime Drv prepare the same sandwich.
 * Since the clerk at Mains St works at a tortería he doesn't have to add a
 * discount to the sandwich, but the clerk from Lime Drv should.
 *
 * The supervisor replenishes all the stores afterwards.
 */
fun demo2() {
    val ingredients = listOf(
            WHOLEGRAIN_BOLILLO,
            TOMATO,
            CHICKEN,
            MAYONNAISE
    )

    john.sellSandwich(ingredients)
    kim.sellSandwich(ingredients)

    Supervisor.buyMissingItems()
}