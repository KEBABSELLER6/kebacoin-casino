package slotmachine.machine

import entity.Player
import slotmachine.machine.slot.Slot
import slotmachine.response.ResponseWinner

interface SlotMachine {

    val player:Player
    var slots:Array<MutableList<Slot>>

    fun takeBet(betAmount:Int):ResponseWinner

}