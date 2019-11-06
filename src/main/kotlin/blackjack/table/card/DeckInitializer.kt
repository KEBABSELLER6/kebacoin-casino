package blackjack.table.card

import blackjack.table.card.Card

interface DeckInitializer{

    fun initializeBlackjackDeck():List<Card>
}