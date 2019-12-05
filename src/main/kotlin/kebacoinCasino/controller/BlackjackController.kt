package kebacoinCasino.controller

import kebacoinCasino.blackjack.manager.BlackjackManager
import kebacoinCasino.blackjack.table.BlackjackTableImpl
import kebacoinCasino.blackjack.request.RequestBlackjackAction
import kebacoinCasino.blackjack.request.RequestBlackjackBet
import kebacoinCasino.blackjack.request.RequestBlackjackTable
import org.springframework.beans.factory.annotation.Autowired
import kebacoinCasino.blackjack.response.ResponseEmptyBlackjackTable
import kebacoinCasino.blackjack.response.ResponseBlackjackTable
import kebacoinCasino.blackjack.response.ResponseBlackjackTableState
import kebacoinCasino.entity.User
import kebacoinCasino.exception.IllegalBetException
import kebacoinCasino.exception.LowBalanceException
import kebacoinCasino.exception.WrongTableException
import kebacoinCasino.service.UserService
import org.springframework.web.bind.annotation.*

@RestController
class BlackjackController {

    @Autowired
    lateinit var blackjackManager: BlackjackManager

    @Autowired
    lateinit var userService: UserService

    companion object {
        const val basePath: String = "blackjack"
    }

    @PostMapping( "$basePath/table")
    fun getBlackjackTable(@RequestBody request: RequestBlackjackTable): ResponseBlackjackTable {
        val tableId= blackjackManager.getNextFreeTable()
        val user=userService.getUserByUsername(username = request.username).apply {
            if(this.balance-request.blackjackAmount<0){
                throw LowBalanceException()
            }else this.balance-=request.blackjackAmount
        }
        userService.updateUser(user.id,user)
        blackjackManager.addTable(BlackjackTableImpl(username = request.username,amount = request.blackjackAmount))
        return ResponseBlackjackTable(tableId,request.username,request.blackjackAmount,user.balance)
    }

    @PostMapping("$basePath/table/{tableId}/bet")
    fun getFirstBet(@RequestBody request: RequestBlackjackBet, @PathVariable tableId: Int):ResponseBlackjackTableState{
        if(checkTable(request.username,tableId)){
            throw IllegalBetException()
        }
        if(checkBetAmount(request.blackjackBetAmount,blackjackManager.getTable(tableId).amount)){
            throw IllegalBetException()
        }
        return blackjackManager.getTable(tableId).takeBet(request.blackjackBetAmount)
    }


    @PostMapping("$basePath/table/{tableId}/bet/action")
    fun getBet(@RequestBody request: RequestBlackjackAction, @PathVariable tableId: Int) :ResponseBlackjackTableState{
        return if(request.action=="out"){
            checkTable(request.username,tableId)
            blackjackManager.getTable(tableId).takeAction(request.action)
        }else if(request.action=="in"){
            checkTable(request.username,tableId)
            blackjackManager.getTable(tableId).takeAction(request.action)
        }else throw IllegalBetException()
    }


    @PostMapping("${basePath}/table/{tableId}/exit")
    fun exitFromTable(@PathVariable tableId: Int):UserDto{
        val user=userService.getUserByUsername(username = blackjackManager.getTable(tableId).username)
        checkTable(user.username,tableId)
        user.balance+=blackjackManager.getTable(tableId).amount
        userService.updateUser(user.id,user)
        blackjackManager.removeTable(blackjackManager.getTable(tableId))
        return UserDto(userService.getUserById(user.id))
    }

    inner class UserDto(user: User){
        val id: Int=user.id
        val username:String=user.username
        val balance:Int=user.balance
    }

    private fun checkTable(username: String, id: Int) = ( blackjackManager.getTable(id).username != username)

    private fun checkBetAmount(betAmount:Int,amount:Int) = betAmount>amount

}