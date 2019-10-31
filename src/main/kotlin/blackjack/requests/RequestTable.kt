package blackjack.requests

import entity.Player

data class RequestTable(val player: Player, val amount:Int)