package slotmachine.requests

import entity.Player

data class RequestBet(val player: Player,val betAmount:Int)