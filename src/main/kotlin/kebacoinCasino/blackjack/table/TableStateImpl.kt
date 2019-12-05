package kebacoinCasino.blackjack.table

import kebacoinCasino.blackjack.table.card.Card

data class TableStateImpl(
    override var playerCards: List<Card>,
    override var playerValue: Int,
    override var houseCards: List<Card>,
    override var houseValue: Int,
    override val betAmount: Int,
    override var playerOut: Boolean,
    override var houseOut: Boolean,
    override var over: Boolean,
    override var amount: Int,
    override var won: String
) : TableState