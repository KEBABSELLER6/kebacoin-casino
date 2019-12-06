package kebacoinCasino.roulette.table

import kebacoinCasino.roulette.table.field.RouletteBoardInitializer
import kebacoinCasino.roulette.table.field.RouletteField
import kebacoinCasino.roulette.response.ResponseRouletteWinner

class RouletteTableImpl(
    override var fields: Array<RouletteField> = initializeRouletteBoard(),
    override var amount: Int,
    override val username: String
) : RouletteTable {

    override fun takeBet(type: String, betFields: List<RouletteField>, betAmount: Int): ResponseRouletteWinner {
        val winner = fields.toMutableList().apply { shuffle() }[0]
        val newAmount: Int = if (winner in betFields) {
            when (type) {
                "plain" -> amount + betAmount * 34
                "split" -> amount + betAmount * 16
                "firstFour" -> amount + betAmount * 7
                "street" -> amount + betAmount * 10
                "sixLine" -> amount + betAmount * 4
                "dozen" -> amount + betAmount * 2
                "column" -> amount + betAmount * 2
                "color" -> amount + betAmount
                else -> amount
            }
        } else {
            amount - betAmount
        }
        amount = newAmount
        return ResponseRouletteWinner(winner = winner, amount = amount, username = username)
    }

    companion object : RouletteBoardInitializer {
        override fun initializeRouletteBoard(): Array<RouletteField> {
            return Array(37) { i -> RouletteField(i) }
        }
    }
}