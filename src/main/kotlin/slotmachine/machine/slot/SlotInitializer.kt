package slotmachine.machine.slot

import slotmachine.machine.slot.Slot

interface SlotInitializer {
    fun initializeSlot():List<Slot>
}