package kebacoinCasino.slotmachine.machine

import kebacoinCasino.slotmachine.machine.slot.Slot
import kebacoinCasino.slotmachine.response.ResponseSlotMachineWinner

interface SlotMachine {
    val username:String
    var balance:Int
    var slots:Array<MutableList<Slot>>

    fun takeBet(betAmount:Int):ResponseSlotMachineWinner

}