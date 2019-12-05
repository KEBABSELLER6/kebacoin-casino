package kebacoinCasino.blackjack.table

import kebacoinCasino.blackjack.table.card.CardImpl.Companion.COLORS
import kebacoinCasino.blackjack.table.card.CardImpl.Companion.TYPES
import kebacoinCasino.blackjack.table.card.CardImpl.Companion.VALUES
import kebacoinCasino.blackjack.response.ResponseBlackjackTableState
import kebacoinCasino.blackjack.table.card.Card
import kebacoinCasino.blackjack.table.card.CardImpl
import kebacoinCasino.blackjack.table.card.DeckInitializer

class BlackjackTableImpl(
    override val deck: MutableList<Card> = initializeBlackjackDeck().toMutableList(),
    override var state: TableState = initTableState().apply { this.amount = amount },
    override var cardCounter: Int = 0, override val username: String, override var amount: Int
) : BlackjackTable {

    companion object : DeckInitializer {
        override fun initializeBlackjackDeck(): List<Card> {
            val deck: MutableList<CardImpl> = mutableListOf()
            COLORS.forEach { color ->
                TYPES.zip(VALUES).toMap().forEach { (type, value) ->
                    deck.add(CardImpl(type, value, color))
                }
            }
            return deck
        }

        fun initTableState() = TableStateImpl(
            emptyList(), 0, emptyList(), 0, 0,
            playerOut = false,
            houseOut = false,
            over = false,
            amount = 0,
            won = "none"
        )
    }

    override fun takeBet(betAmount: Int): ResponseBlackjackTableState {
        deck.shuffle()
        val playerCards = arrayListOf(deck[0], deck[2])
        val houseCards = arrayListOf(deck[1], deck[3])
        cardCounter += 3
        state = TableStateImpl(
            playerCards,
            playerCards.sumBy { it.value },
            houseCards,
            houseCards.sumBy { it.value },
            betAmount,
            playerOut = false,
            houseOut = false,
            over = false,
            amount = this.amount,
            won = "none"
        )

        if (state.houseValue == 21 || state.playerValue == 21) {
            if (state.houseValue == 21 && state.playerValue == 21) {
                //DO nothing with player amount
                return ResponseBlackjackTableState(state.apply {
                    over = true
                    won="both"
                })
            } else if (state.playerValue == 21) {
                return ResponseBlackjackTableState(state.apply {
                    over = true
                    won="player"
                    amount+= betAmount / 2
                })
            } else if (state.houseValue == 21) {
                return ResponseBlackjackTableState(state.apply {
                    over = true
                    won="player"
                    amount-= betAmount
                })
            }
        }

        state.houseOut = checkIfHouseOut()

        return ResponseBlackjackTableState(state)
    }

    private fun checkIfHouseOut(): Boolean {
        return state.houseValue > 15
    }

    private fun checkState() {
        state.let {
            if (it.playerValue > 21) {
                amount -= it.betAmount
            } else if (it.houseValue > 21) {
                amount += it.betAmount
            }
            if (it.over) {
                if (it.playerValue > it.houseValue) {
                    amount += it.betAmount
                    it.won = "player"
                } else {
                    amount -= state.betAmount
                    it.won="house"
                }
            }
        }
    }

    override fun takeAction(action: String): ResponseBlackjackTableState {
        if (action == "out" && state.houseOut) {
            state.apply {
                this.playerOut = true
                this.over = true
            }

            checkState()
            return ResponseBlackjackTableState(state)
        } else if (action == "out" && !state.houseOut) {
            while (!state.houseOut) {
                cardCounter++
                val houseCards = state.houseCards.toMutableList()
                houseCards.add(deck[cardCounter])
                state.apply {
                    this.houseCards = houseCards
                    this.houseValue = houseCards.sumBy { it.value }
                    this.playerOut = true
                    this.houseOut = checkIfHouseOut()
                }
            }

            checkState()
            return ResponseBlackjackTableState(state.apply { over = true })
        } else if (action == "in" && state.houseOut) {
            cardCounter++
            val playerCards = state.playerCards.toMutableList()
            playerCards.add(deck[cardCounter])
            state.apply {
                this.playerCards = playerCards
                this.playerValue = playerCards.sumBy { it.value }
            }

            checkState()
            return ResponseBlackjackTableState(state)
        } else {
            cardCounter++
            val playerCards = state.playerCards.toMutableList()
            playerCards.add(deck[cardCounter])
            cardCounter++
            val houseCards = state.houseCards.toMutableList()
            houseCards.add(deck[cardCounter])

            state.apply {
                this.playerCards = playerCards
                this.playerValue = playerCards.sumBy { it.value }
                this.houseCards = houseCards
                this.houseValue = houseCards.sumBy { it.value }
                this.houseOut = checkIfHouseOut()
            }

            checkState()
            return ResponseBlackjackTableState(state)
        }
    }
}