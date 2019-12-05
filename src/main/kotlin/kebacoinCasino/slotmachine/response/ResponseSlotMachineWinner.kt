package kebacoinCasino.slotmachine.response

import kebacoinCasino.slotmachine.machine.slot.Slot

data class ResponseSlotMachineWinner(val balance: Int, val username:String, val firstSlot: Slot, val secondSlot: Slot, val thirdSlot: Slot, var machineId:Int=-1)