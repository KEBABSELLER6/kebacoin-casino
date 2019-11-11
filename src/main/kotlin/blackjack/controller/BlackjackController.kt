package blackjack.controller

import blackjack.manager.BlackjackManager
import blackjack.table.BlackjackTableImpl
import blackjack.request.RequestAction
import blackjack.request.RequestBet
import org.springframework.beans.factory.annotation.Autowired
import blackjack.response.ResponseEmptyTable
import blackjack.response.ResponseTable
import org.springframework.web.bind.annotation.*
import roulette.request.RequestTable

@RestController
class BlackjackController {

    @Autowired
    lateinit var blackjackManager: BlackjackManager

    companion object {
        const val basePath: String = "blackjack"
    }

    @GetMapping("$basePath/table")
    fun getTable(): ResponseEmptyTable {
        return ResponseEmptyTable(tableId = blackjackManager.getNextFreeTable())
    }

    @PostMapping("$basePath/table/{tableId}")
    fun playerToTable(@RequestBody requestBody: RequestTable, @PathVariable tableId: Int): ResponseTable {
        blackjackManager.addTable(BlackjackTableImpl(requestBody.player))
        return ResponseTable(player = requestBody.player, tableId = tableId)
    }

    @PostMapping("$basePath/table/{tableId}/bet")
    fun getFirstBet(@RequestBody requestBody: RequestBet, @PathVariable tableId: Int) =
        blackjackManager.getTable(tableId).takeBet(requestBody.betAmount)


    @PostMapping("$basePath/table/{tableId}/bet/action")
    fun getBet(@RequestBody requestBody: RequestAction, @PathVariable tableId: Int) =
        blackjackManager.getTable(tableId).takeAction(requestBody.action)


}