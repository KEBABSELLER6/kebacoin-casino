package slotmachine.responses

import entity.Player
import slotmachine.Slot

data class ResponseWinner(val player: Player, val firstSlot: Slot, val secondSlot:Slot, val thirdSlot:Slot,var machineId:Int=-1)