package blackjack.request

import entity.Player

data class RequestTable(val player: Player, val amount:Int)