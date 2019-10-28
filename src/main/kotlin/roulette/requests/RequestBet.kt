package roulette.requests

import entity.Player
import roulette.RouletteField

data class RequestBet(val player:Player,val betAmount:Int,val type:String,val fields:List<RouletteField>)