package slotmachine.request

import entity.Player

data class RequestMachine(val player: Player,val amount:Int)