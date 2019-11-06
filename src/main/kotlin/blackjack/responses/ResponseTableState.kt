package blackjack.responses

import blackjack.table.TableState
import entity.Player

data class ResponseTableState(val player: Player,val tableState: TableState)