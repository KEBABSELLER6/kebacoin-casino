package kebacoinCasino.blackjack.table

import kebacoinCasino.blackjack.table.card.Card
import kebacoinCasino.blackjack.response.ResponseBlackjackTableState

interface BlackjackTable{
    var amount:Int
    val username:String
    val deck:MutableList<Card>
    var state: TableState
    var cardCounter:Int

    fun takeBet(betAmount:Int):ResponseBlackjackTableState

    fun takeAction(action:String):ResponseBlackjackTableState
}