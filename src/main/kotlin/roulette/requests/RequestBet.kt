package roulette.requests

import roulette.RouletteField

data class RequestBet(val betAmount:Int,val type:String,val fields:List<RouletteField>)