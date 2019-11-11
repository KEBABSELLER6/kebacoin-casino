package blackjack.table

import blackjack.table.card.Card
import blackjack.response.ResponseTableState
import entity.Player

interface BlackjackTable{
    val player:Player
    val deck:MutableList<Card>
    var state: TableState
    var cardCounter:Int

    fun takeBet(betAmount:Int):ResponseTableState

    fun takeAction(action:String):ResponseTableState
}