package roulette

import roulette.responses.ResponseWinner

class RouletteTable(
    var player: String,
    var amount: Int,
    private var fields: Array<RouletteField> = getBoard()
) {

    fun takeBet(type: String, betFields: List<RouletteField>, betAmount: Int): ResponseWinner {

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
        return ResponseWinner(playerName = player, winner = winner, amount = amount)
    }

    companion object {
        private fun getBoard() = Array(36) { i -> RouletteField(i) }
    }

}