package kebacoinCasino.controller

import kebacoinCasino.entity.User
import kebacoinCasino.exception.IllegalBetException
import kebacoinCasino.exception.LowBalanceException
import kebacoinCasino.roulette.manager.RouletteManager
import kebacoinCasino.roulette.request.RequestRouletteBet
import kebacoinCasino.roulette.request.RequestRouletteTable
import kebacoinCasino.roulette.response.ResponseRouletteTable
import kebacoinCasino.roulette.response.ResponseRouletteWinner
import kebacoinCasino.roulette.table.RouletteTableImpl
import kebacoinCasino.roulette.table.field.RouletteField
import kebacoinCasino.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class RouletteController {

    @Autowired
    lateinit var rouletteManager: RouletteManager

    @Autowired
    lateinit var userService: UserService

    companion object {
        const val basePath = "/roulette"
    }

    @PostMapping("$basePath/table")
    fun getTable(@RequestBody request: RequestRouletteTable): ResponseRouletteTable {
        val tableId=rouletteManager.getNextFreeTable()
        val user = userService.getUserByUsername(username = request.username).apply {
            if (this.balance -request.rouletteAmount < 0) {
                throw LowBalanceException()
            } else this.balance -= request.rouletteAmount
        }
        rouletteManager.addTable(RouletteTableImpl(amount = request.rouletteAmount, username = request.username))
        userService.updateUser(user.id, user)
        return ResponseRouletteTable(
            tableId,
            amount = request.rouletteAmount,
            username = request.username,
            balance = user.balance
        )
    }


    @PostMapping("$basePath/table/{tableId}/bet")
    fun takeBet(@RequestBody request: RequestRouletteBet, @PathVariable tableId: Int): ResponseRouletteWinner {
        if(checkTable(request.username, tableId)){
            throw IllegalBetException()
        }else if(!checkIfBetIsCorrect(request.type,request.fields)){
            throw IllegalBetException()
        }else if(checkBetAmount(request.rouletteBetAmount,rouletteManager.getTable(tableId).amount)){
            throw IllegalBetException()
        }else return rouletteManager.getTable(tableId).takeBet(request.type, request.fields, request.rouletteBetAmount)
            .apply {
                this.tableId = tableId
            }
    }

    @PostMapping("$basePath/table/{tableId}/exit")
    fun exitFromTable(@PathVariable tableId: Int): UserDto {
        val user = userService.getUserByUsername(username = rouletteManager.getTable(tableId).username)
        if(checkTable(user.username, tableId)){
            throw IllegalBetException()
        }
        user.balance += rouletteManager.getTable(tableId).amount
        userService.updateUser(user.id, user)
        rouletteManager.removeTable(tableId)
        return UserDto(userService.getUserById(user.id))
    }

    inner class UserDto(user: User) {
        val id: Int = user.id
        val username: String = user.username
        val balance: Int = user.balance
    }

    private fun checkTable(username: String, id: Int) = (rouletteManager.getNextFreeTable() <= id || rouletteManager.getTable(id).username != username)

    private fun checkIfBetIsCorrect(type: String, fields: List<RouletteField>): Boolean {
        when (type) {
            "plain" -> {
                if (fields.size != 1)
                    return false
            }
            "split" -> {
                if (fields.size != 2)
                    return false
            }
            "firstFour" -> {
                fields.forEach {
                    if (it.number !in 0..3)
                        return false
                }
            }
            "street" -> {
                val min = fields.minBy { it.number }?.number
                fields.forEach {
                    if (min != null) {
                        if (it.number !in min..min + 3)
                            return false
                    }
                }
            }
            "sixLine" -> {
                val min = fields.minBy { it.number }?.number
                fields.forEach {
                    if (min != null) {
                        if (it.number !in min..min + 6)
                            return false
                    }
                }
            }
            "dozen" -> {
                val dozen = when (fields.minBy { it.number }?.number) {
                    in 1..12 -> 1
                    in 12..24 -> 2
                    in 25..36 -> 3
                    else -> -1
                }

                fields.forEach {
                    when (dozen) {
                        1 -> {
                            if (it.number !in 1..12)
                                return false
                        }
                        2 -> {
                            if (it.number !in 13..24)
                                return false
                        }
                        3 -> {
                            if (it.number !in 25..36)
                                return false
                        }
                    }
                }
            }
            "column" -> {
                val column = when (fields.minBy { it.number }?.number) {
                    1 -> 1
                    2 -> 2
                    3 -> 3
                    else -> -1
                }
                val columnNumbers = Array(12) { i -> column + i * 3 }

                fields.forEach {
                    if (it.number !in columnNumbers)
                        return false
                }
            }
            "color" -> {
                val color = (fields.minBy { it.number }?.number ?: -1) % 2
                val colorNumbers = Array(36) { i -> i + 1 }.filter {
                    if (color == 1) {
                        it % 2 == 1
                    } else it % 2 == 0
                }

                fields.forEach {
                    if (it.number !in colorNumbers)
                        return false
                }
            }
        }

        return true
    }

    private fun checkBetAmount(betAmount:Int,amount:Int) = betAmount>amount

}