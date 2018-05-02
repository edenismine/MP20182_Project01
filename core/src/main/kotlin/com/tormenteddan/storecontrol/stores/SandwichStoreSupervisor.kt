package com.tormenteddan.storecontrol.stores

import com.tormenteddan.storecontrol.util.Article
import com.tormenteddan.storecontrol.util.Transaction
import com.tormenteddan.storecontrol.util.TransactionType
import java.util.*

/**
 * A sandwich store supervisor that watches over and collects information
 * from the stores it controls.
 *
 * @property globalBalance The supervisor's current balance, calculated by
 * adding the supervisor's stores' balance.
 * @property balanceMap Maps each of the supervised stores to their balance.
 * @property shoppingMap Maps each of the supervised stores to a list of
 * their missing items.
 * @property ledger Using this collection, the supervisor keeps track of all
 * the transactions that take place inside his/her network.
 */
open class SandwichStoreSupervisor : Observer {
    val globalBalance: Int
        get() = balanceMap.toList().sumBy { (_, b) -> b }
    val balanceMap = hashMapOf<SandwichStore, Int>()
    val shoppingMap = hashMapOf<SandwichStore, List<Article>>()
    val ledger = arrayListOf<Transaction>()

    /**
     * This method is called whenever the observed object is changed. An
     * application calls an [Observable] object's
     * [notifyObservers][Observable.notifyObservers] method to have all the
     * object's observers notified of the change.
     *
     * @param o the observable object.
     * @param arg argument passed to the
     * [notifyObservers][Observable.notifyObservers] method.
     */
    override fun update(o: Observable?, arg: Any?) {
        if (o is SandwichStore) {
            if (arg is Transaction) ledger.add(arg)
            balanceMap[o] = o.balance
            shoppingMap[o] = o.missingArticles
        }
    }

    /**
     * This function iterates over all the stores that have reported to the
     * supervisor and have [missing articles][SandwichStore.missingArticles];
     * it[replenishes][SandwichStore.replenish] their inventories, creates a
     * [transaction][Transaction] and makes each store update its supervisors
     * accordingly.
     */
    fun buyMissingItems() {
        for (store in shoppingMap.keys) {
            store.missingArticles.forEach {
                val missing = it.required - it.current
                if (store.replenish(it, missing)) {
                    val transaction = Transaction(TransactionType.SPENT,
                            store.address, it.description,
                            (missing * it.cost))
                    store.update(store, transaction)
                }
            }
        }
    }
}

