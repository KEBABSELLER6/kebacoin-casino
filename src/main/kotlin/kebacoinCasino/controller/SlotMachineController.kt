package kebacoinCasino.controller

import kebacoinCasino.entity.User
import kebacoinCasino.exception.IllegalBetException
import kebacoinCasino.exception.LowBalanceException
import kebacoinCasino.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import kebacoinCasino.slotmachine.machine.SlotMachineImpl
import kebacoinCasino.slotmachine.manager.SlotMachineManager
import kebacoinCasino.slotmachine.request.RequestSlotMachineBet
import kebacoinCasino.slotmachine.request.RequestSlotMachine
import kebacoinCasino.slotmachine.response.ResponseSlotMachine
import kebacoinCasino.slotmachine.response.ResponseSlotMachineWinner

@RestController
class SlotMachineController {

    @Autowired
    lateinit var slotMachineManager: SlotMachineManager

    @Autowired
    lateinit var userService: UserService

    companion object {
        const val basePath = "slot"
    }

    @PostMapping("$basePath/machine")
    fun getTable(@RequestBody request: RequestSlotMachine): ResponseSlotMachine {
        val machineId=slotMachineManager.getNextFreeMachine()
        slotMachineManager.addSlotMachine(SlotMachineImpl(balance = request.slotAmount, username = request.username))
        val user =
            userService.getUserByUsername(username = request.username).apply {
                if (this.balance - request.slotAmount < 0) {
                    throw LowBalanceException()
                } else this.balance -= request.slotAmount
            }
        userService.updateUser(user.id, user)
        return ResponseSlotMachine(machineId, request.username, request.slotAmount, user.balance)
    }

    @PostMapping("$basePath/machine/{machineId}/bet")
    fun takeBet(@RequestBody request: RequestSlotMachineBet, @PathVariable machineId: Int): ResponseSlotMachineWinner {
        if(checkTable(request.username, machineId)){
            throw IllegalBetException()
        }
        if(checkBetAmount(request.slotBetAmount,slotMachineManager.getSlotMachine(machineId).balance)){
            throw IllegalBetException()
        }
        return slotMachineManager.getSlotMachine(machineId).takeBet(betAmount = request.slotBetAmount).apply {
            this.machineId = machineId
        }
    }

    @PostMapping("${basePath}/machine/{machineId}/exit")
    fun exitFromTable(@PathVariable machineId: Int): UserDto {
        val user =
            userService.getUserByUsername(username = slotMachineManager.getSlotMachine(machineId).username)
        if(checkTable(user.username, machineId)){
            throw IllegalBetException()
        }
        user.balance += slotMachineManager.getSlotMachine(machineId).balance

        userService.updateUser(user.id, user)
        return UserDto(userService.getUserById(user.id))
    }

    inner class UserDto(user: User) {
        val id: Int = user.id
        val username: String = user.username
        val balance: Int = user.balance
    }

    private fun checkTable(username: String, id: Int) = (slotMachineManager.getNextFreeMachine() <= id || slotMachineManager.getSlotMachine(id).username != username)

    private fun checkBetAmount(betAmount:Int,amount:Int) = betAmount>amount

}