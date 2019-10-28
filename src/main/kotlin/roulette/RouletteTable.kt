package roulette

import entity.Player
import roulette.responses.ResponseWinner

class RouletteTable(
    var player: Player,
    private var fields: Array<RouletteField> = getBoard()
) {

    fun takeBet(type: String, betFields: List<RouletteField>, betAmount: Int): ResponseWinner {

        val winner = fields.toMutableList().apply { shuffle() }[0]
        val newAmount: Int = if (winner in betFields) {
            when (type) {
                "plain" -> player.amount + betAmount * 34
                "split" -> player.amount + betAmount * 16
                "firstFour" -> player.amount + betAmount * 7
                "street" -> player.amount + betAmount * 10
                "sixLine" -> player.amount + betAmount * 4
                "dozen" -> player.amount + betAmount * 2
                "column" -> player.amount + betAmount * 2
                "color" -> player.amount + betAmount
                else -> player.amount
            }
        } else {
            player.amount - betAmount
        }
        player.amount = newAmount
        return ResponseWinner(winner = winner,player = player)
    }

    companion object {
        private fun getBoard() = Array(36) { i -> RouletteField(i) }
    }

}