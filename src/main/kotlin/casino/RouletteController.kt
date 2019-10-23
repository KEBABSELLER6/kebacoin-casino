package casino

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import roulette.responses.ResponseEmptyTable
import roulette.RouletteManager
import roulette.RouletteTable
import roulette.requests.RequestBet
import roulette.requests.RequestTable
import roulette.responses.ResponseTable
import roulette.responses.ResponseWinner

@RestController
class RouletteController{

    @Autowired
    lateinit var rouletteManager:RouletteManager

    companion object{
        const val basePath="/roulette"
    }

    @GetMapping("$basePath/getTable")
    fun getTable() : ResponseEmptyTable {
        return ResponseEmptyTable("roulette", rouletteManager.tables.size + 1)
    }

    @PostMapping("$basePath/table/{tableId}")
    fun playerToTable(@RequestBody request: RequestTable, @PathVariable tableId:Int) :ResponseTable{
        //TODO if table has player return error
        //TODO check if player balance > amount

        rouletteManager.tables.add(RouletteTable(request.playerName,request.amount))
        return ResponseTable(tableId,request.playerName,request.amount)
    }

    @PostMapping("$basePath/table/{tableId}/bet")
    fun takeBet(@RequestBody bet:RequestBet,@PathVariable tableId: Int):ResponseWinner{
        //TODO check if the player is there
        //TODO check if table is not empty
        //TODO check if right player
        //TODO check is bet < amount

        return ResponseWinner("${rouletteManager.tables[tableId].player}  ${bet.bet.toString()}")
    }
}