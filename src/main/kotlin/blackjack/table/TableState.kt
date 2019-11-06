package blackjack.table

import blackjack.table.card.Card

interface TableState{
    var playerCards:List<Card>
    var playerValue:Int
    var playerOut:Boolean
    var houseCards:List<Card>
    var houseValue:Int
    var houseOut:Boolean
    val betAmount:Int
    var over:Boolean
}