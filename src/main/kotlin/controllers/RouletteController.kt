package controllers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import roulette.responses.ResponseEmptyTable
import roulette.RouletteManager
import roulette.RouletteTableImpl
import roulette.requests.RequestBet
import roulette.requests.RequestTable
import roulette.responses.ResponseTable
import roulette.responses.ResponseWinner

@RestController
class RouletteController {

    @Autowired
    lateinit var rouletteManager: RouletteManager

    companion object {
        const val basePath = "/roulette"
    }

    @GetMapping("$basePath/table")
    fun getTable(): ResponseEmptyTable {
        return ResponseEmptyTable(tableNumber = rouletteManager.tables.size)
    }

    @PostMapping("$basePath/table/{tableId}")
    fun playerToTable(@RequestBody request: RequestTable, @PathVariable tableId: Int): ResponseTable {
        //TODO if table has player return error
        //TODO check if player balance > amount
        //TODO remove amount from balance

        rouletteManager.addTable(RouletteTableImpl(request.player))
        return ResponseTable(tableId, request.player)
    }

    @PostMapping("$basePath/table/{tableId}/bet")
    fun takeBet(@RequestBody bet: RequestBet, @PathVariable tableId: Int): ResponseWinner {
        //TODO check if the player is there
        //TODO check if table is not empty
        //TODO check if right player
        //TODO check is bet < amount

        return rouletteManager.tables[tableId].takeBet(bet.type, bet.fields, bet.betAmount).apply {
            this.tableId=tableId
        }
    }
}