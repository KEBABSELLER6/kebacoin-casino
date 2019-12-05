package kebacoinCasino.roulette.response

import kebacoinCasino.roulette.table.field.RouletteField

data class ResponseRouletteWinner(val amount:Int, val username:String, val winner: RouletteField, var tableId:Int = -1)