package kebacoinCasino.roulette.table

import kebacoinCasino.roulette.table.field.RouletteField
import kebacoinCasino.roulette.response.ResponseRouletteWinner

interface RouletteTable{

    var amount:Int
    val username:String
    var fields: Array<RouletteField>

    fun takeBet(type: String, betFields: List<RouletteField>, betAmount: Int): ResponseRouletteWinner

}