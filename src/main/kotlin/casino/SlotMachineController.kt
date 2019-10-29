package casino

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import slotmachine.SlotMachineImpl
import slotmachine.SlotMachineManager
import slotmachine.requests.RequestBet
import slotmachine.requests.RequestMachine
import slotmachine.responses.ResponseEmptyMachine
import slotmachine.responses.ResponseMachine
import slotmachine.responses.ResponseWinner

@RestController
class SlotMachineController {

    @Autowired
    lateinit var slotMachineManager: SlotMachineManager

    companion object {
        const val basePath = "/slotmachine"
    }

    @GetMapping("$basePath/machine")
    fun getTable(): ResponseEmptyMachine {
        return ResponseEmptyMachine(machineId = slotMachineManager.slotMachines.size)
    }

    @PostMapping("$basePath/machine/{machineId}")
    fun playerToTable(@RequestBody request: RequestMachine, @PathVariable machineId: Int): ResponseMachine {
        //TODO if machine has player return error
        //TODO check if player balance > amount
        //TODO remove amount from balance

        slotMachineManager.addSlotMachine(SlotMachineImpl(request.player))
        return ResponseMachine(request.player,machineId)
    }

    @PostMapping("$basePath/machine/{machineId}/bet")
    fun takeBet(@RequestBody bet: RequestBet, @PathVariable machineId: Int): ResponseWinner {
        //TODO check if the player is there
        //TODO check if machine is not empty
        //TODO check if right player
        //TODO check is bet < amount

        return slotMachineManager.getSlotMachine(machineId).takeBet(betAmount = bet.betAmount).apply {
            this.machineId =machineId
        }
    }
}