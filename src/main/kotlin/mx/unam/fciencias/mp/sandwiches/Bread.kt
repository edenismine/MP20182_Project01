package mx.unam.fciencias.mp.sandwiches

import mx.unam.fciencias.mp.util.PLAIN_BOLILLO_COST
import mx.unam.fciencias.mp.util.PLAIN_BOLILLO_TAG
import mx.unam.fciencias.mp.util.WHOLEGRAIN_BOLILLO_COST
import mx.unam.fciencias.mp.util.WHOLEGRAIN_BOLILLO_TAG

enum class Bread(val type: String, val cost: Int){
    PLAIN_BOLILLO(PLAIN_BOLILLO_TAG, PLAIN_BOLILLO_COST),
    WHOLEGRAIN_BOLILLO(WHOLEGRAIN_BOLILLO_TAG, WHOLEGRAIN_BOLILLO_COST)
}