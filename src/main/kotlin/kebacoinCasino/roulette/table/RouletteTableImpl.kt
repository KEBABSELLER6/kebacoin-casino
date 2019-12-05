package kebacoinCasino.roulette.table

import kebacoinCasino.roulette.table.field.RouletteBoardInitializer
import kebacoinCasino.roulette.table.field.RouletteField
import kebacoinCasino.roulette.response.ResponseRouletteWinner

class RouletteTableImpl(
    override var fields: Array<RouletteField> = initializeRouletteBoard(),
    override var balance: Int,
    override val username: String
) : RouletteTable {

    override fun takeBet(type: String, betFields: List<RouletteField>, betAmount: Int): ResponseRouletteWinner {
        val winner = fields.toMutableList().apply { shuffle() }[0]
        val newAmount: Int = if (winner in betFields) {
            when (type) {
                "plain" -> balance + betAmount * 34
                "split" -> balance + betAmount * 16
                "firstFour" -> balance + betAmount * 7
                "street" -> balance + betAmount * 10
                "sixLine" -> balance + betAmount * 4
                "dozen" -> balance + betAmount * 2
                "column" -> balance + betAmount * 2
                "color" -> balance + betAmount
                else -> balance
            }
        } else {
            balance - betAmount
        }
        balance = newAmount
        return ResponseRouletteWinner(winner = winner, amount = balance, username = username)
    }

    companion object : RouletteBoardInitializer {
        override fun initializeRouletteBoard(): Array<RouletteField> {
            return Array(37) { i -> RouletteField(i) }
        }
    }
}