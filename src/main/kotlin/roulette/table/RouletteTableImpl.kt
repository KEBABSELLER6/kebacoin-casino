package roulette.table

import entity.Player
import roulette.table.field.RouletteBoardInitializer
import roulette.table.field.RouletteField
import roulette.response.ResponseWinner

class RouletteTableImpl(
    override var player: Player,
    override var fields: Array<RouletteField> = initializeRouletteBoard()
) : RouletteTable {

    override fun checkIfBetIsCorrect(type: String, fields: List<RouletteField>): Boolean {
        when (type) {
            "plain" -> {
                if (fields.size != 1)
                    return false
            }
            "split" -> {
                if (fields.size != 2)
                    return false
            }
            "firstFour" -> {
                fields.forEach {
                    if (it.number !in 0..3)
                        return false
                }
            }
            "street" -> {
                val min = fields.minBy { it.number }?.number
                fields.forEach {
                    if (min != null) {
                        if (it.number !in min..min + 3)
                            return false
                    }
                }
            }
            "sixLine" -> {
                val min = fields.minBy { it.number }?.number
                fields.forEach {
                    if (min != null) {
                        if (it.number !in min..min + 6)
                            return false
                    }
                }
            }
            "dozen" -> {
                val dozen = when (fields.minBy { it.number }?.number) {
                    in 1..12 -> 1
                    in 12..24 -> 2
                    in 25..36 -> 3
                    else -> -1
                }

                fields.forEach {
                    when (dozen) {
                        1 -> {
                            if (it.number !in 1..12)
                                return false
                        }
                        2 -> {
                            if (it.number !in 13..24)
                                return false
                        }
                        3 -> {
                            if (it.number !in 25..36)
                                return false
                        }
                    }
                }
            }
            "column" -> {
                val column = when (fields.minBy { it.number }?.number) {
                    1 -> 1
                    2 -> 2
                    3 -> 3
                    else -> -1
                }
                val columnNumbers = Array(12) { i -> column + i * 3 }

                fields.forEach {
                    if (it.number !in columnNumbers)
                        return false
                }
            }
            "color" -> {
                val color = (fields.minBy { it.number }?.number ?: -1) % 2
                val colorNumbers = Array(36) { i -> i + 1 }.filter {
                    if (color == 1) {
                        it % 2 == 1
                    } else it % 2 == 0
                }

                fields.forEach {
                    if(it.number !in colorNumbers)
                        return false
                }
            }
        }

        return true
    }

    override fun takeBet(type: String, betFields: List<RouletteField>, betAmount: Int): ResponseWinner {
        if (!checkIfBetIsCorrect(type, betFields)) {
            //TODO return with error
        }

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
        return ResponseWinner(winner = winner, player = player)
    }

    companion object : RouletteBoardInitializer {
        override fun initializeRouletteBoard(): Array<RouletteField> {
            return Array(37) { i -> RouletteField(i) }
        }
    }
}