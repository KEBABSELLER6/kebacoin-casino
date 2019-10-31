package blackjack

import entity.Player
import blackjack.CardImpl.Companion.COLORS
import blackjack.CardImpl.Companion.TYPES
import blackjack.CardImpl.Companion.VALUES
import blackjack.responses.ResponseTableState

class BlackjackTableImpl(
    override val player: Player,
    override val deck: MutableList<Card> = initializeBlackjackDeck().toMutableList(),
    override var state: TableState = initTableState(),
    override var cardCounter: Int = 0
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

        fun initTableState() = TableStateImpl(emptyList(), 0, emptyList(), 0, 0,
            playerOut = false,
            houseOut = false,
            over = false)
    }

    override fun takeBet(betAmount: Int): ResponseTableState {
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
            over = false
        )

        if (state.houseValue == 21 || state.playerValue == 21) {
            if (state.houseValue == 21 && state.playerValue == 21) {
                //DO nothing with player amount
            } else if (state.playerValue == 21) {
                player.amount += betAmount / 2
            } else if (state.houseValue == 21) {
                player.amount -= betAmount
            }

            return ResponseTableState(player, state.apply {
                over=true
            })
        }

        state.houseOut = checkIfHouseOut()

        return ResponseTableState(player, state)
    }

    private fun checkIfHouseOut(): Boolean {
        return state.houseValue > 15
    }

    private fun checkState() {
        state.let {
            if (it.playerValue > 21) {
                player.amount -= it.betAmount
            } else if (it.houseValue > 21) {
                player.amount += it.betAmount
            }
            if (it.over) {
                if (it.playerValue > it.houseValue) {
                    player.amount += it.betAmount
                } else player.amount -= state.betAmount
            }
        }
    }

    override fun takeAction(action: String): ResponseTableState {
        if (action == "out" && state.houseOut) {
            state.apply {
                this.playerOut = true
                this.over=true
            }

            checkState()
            return ResponseTableState(player, state)
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
            return ResponseTableState(player, state.apply { over=true })
        } else if (action == "in" && state.houseOut) {
            cardCounter++
            val playerCards = state.playerCards.toMutableList()
            playerCards.add(deck[cardCounter])
            state.apply {
                this.playerCards = playerCards
                this.playerValue = playerCards.sumBy { it.value }
            }

            checkState()
            return ResponseTableState(player, state)
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
            return ResponseTableState(player, state)
        }
    }
}