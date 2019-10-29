package slotmachine

interface SlotMachineManager {

    var slotMachines:MutableList<SlotMachine>

    fun addSlotMachine(slotMachine: SlotMachine)

    fun getSlotMachine(id:Int):SlotMachine
}