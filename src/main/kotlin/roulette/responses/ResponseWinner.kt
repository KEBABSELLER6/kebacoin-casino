package roulette.responses

import entity.Player
import roulette.RouletteField

data class ResponseWinner(val player: Player,val winner:RouletteField, var tableId:Int = -1)