package roulette.responses

import roulette.RouletteField

data class ResponseWinner(val winner:RouletteField,var tableId:Int?=null,val playerName:String,val amount:Int)