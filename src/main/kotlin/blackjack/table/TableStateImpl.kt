package blackjack.table

import blackjack.table.TableState
import blackjack.table.card.Card

data class TableStateImpl(
    override var playerCards: List<Card>,
    override var playerValue: Int,
    override var houseCards: List<Card>,
    override var houseValue: Int,
    override val betAmount: Int,
    override var playerOut: Boolean,
    override var houseOut: Boolean,
    override var over:Boolean
): TableState