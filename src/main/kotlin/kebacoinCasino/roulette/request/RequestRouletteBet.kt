package kebacoinCasino.roulette.request

import kebacoinCasino.roulette.table.field.RouletteField

data class RequestRouletteBet(val rouletteBetAmount:Int=-1, val type:String, val fields:List<RouletteField>, val username:String)