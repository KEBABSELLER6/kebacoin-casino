package kebacoinCasino.slotmachine.manager

import kebacoinCasino.slotmachine.machine.SlotMachine

interface SlotMachineManager {

    fun addSlotMachine(slotMachine: SlotMachine)

    fun getSlotMachine(id:Int): SlotMachine

    fun getNextFreeMachine():Int

    fun removeSlotMachine(index:Int)

}