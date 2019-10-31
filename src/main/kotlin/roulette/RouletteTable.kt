package roulette

import entity.Player
import roulette.responses.ResponseWinner

interface RouletteTable{

    var player: Player
    var fields: Array<RouletteField>

    fun takeBet(type: String, betFields: List<RouletteField>, betAmount: Int): ResponseWinner

    fun checkIfBetIsCorrect(type:String,fields:List<RouletteField>):Boolean

}