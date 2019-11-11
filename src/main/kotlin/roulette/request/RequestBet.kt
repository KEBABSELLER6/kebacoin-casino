package roulette.request

import entity.Player
import roulette.table.field.RouletteField

data class RequestBet(val player:Player,val betAmount:Int,val type:String,val fields:List<RouletteField>)