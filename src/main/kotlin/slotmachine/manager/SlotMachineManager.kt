package slotmachine.manager

import slotmachine.machine.SlotMachine

interface SlotMachineManager {

    fun addSlotMachine(slotMachine: SlotMachine)

    fun getSlotMachine(id:Int): SlotMachine

    fun getNextFreeMachine():Int
}