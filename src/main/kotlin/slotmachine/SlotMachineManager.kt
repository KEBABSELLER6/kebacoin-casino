package slotmachine

interface SlotMachineManager {

    fun addSlotMachine(slotMachine: SlotMachine)

    fun getSlotMachine(id:Int):SlotMachine

    fun getNextFreeMachine():Int
}