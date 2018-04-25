package com.tormenteddan.storecontrol.sandwiches

import com.tormenteddan.storecontrol.util.PLAIN_BOLILLO_COST
import com.tormenteddan.storecontrol.util.PLAIN_BOLILLO_TAG
import com.tormenteddan.storecontrol.util.WHOLEGRAIN_BOLILLO_COST
import com.tormenteddan.storecontrol.util.WHOLEGRAIN_BOLILLO_TAG

enum class Bread(val type: String, val cost: Int) {
    PLAIN_BOLILLO(PLAIN_BOLILLO_TAG, PLAIN_BOLILLO_COST),
    WHOLEGRAIN_BOLILLO(WHOLEGRAIN_BOLILLO_TAG, WHOLEGRAIN_BOLILLO_COST)
}