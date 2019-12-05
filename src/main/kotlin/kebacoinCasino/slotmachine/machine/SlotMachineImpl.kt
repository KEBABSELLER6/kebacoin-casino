package kebacoinCasino.slotmachine.machine

import kebacoinCasino.slotmachine.machine.slot.Slot
import kebacoinCasino.slotmachine.machine.slot.SlotImpl
import kebacoinCasino.slotmachine.machine.slot.SlotInitializer
import kebacoinCasino.slotmachine.response.ResponseSlotMachineWinner

class SlotMachineImpl(
    override var slots: Array<MutableList<Slot>> = Array(3) { i ->
        initializeSlot()
            .toMutableList()
    }, override val username: String, override var balance: Int
) : SlotMachine {

    companion object : SlotInitializer {
        override fun initializeSlot(): List<Slot> {
            return mutableListOf(
                SlotImpl("orange"),
                SlotImpl("yellow"),
                SlotImpl("green"),
                SlotImpl("blue"),
                SlotImpl("pink"),
                SlotImpl("blueSeven"),
                SlotImpl("pinkSeven"),
                SlotImpl("greenSeven")
            )
        }

    }

    override fun takeBet(betAmount: Int): ResponseSlotMachineWinner {
        val firstSlot = slots[0].apply { shuffle() }[0]
        val secondSlot = slots[1].apply { shuffle() }[0]
        val thirdSlot = slots[2].apply { shuffle() }[0]

        val newAmount: Int =
            if (firstSlot.type == secondSlot.type && firstSlot.type == thirdSlot.type && secondSlot.type == thirdSlot.type) {
                when (firstSlot.type) {
                    "orange" -> balance + betAmount * 600
                    "yellow" -> balance + betAmount * 120
                    "green" -> balance + betAmount * 100
                    "blue" -> balance + betAmount * 80
                    "pink" -> balance + betAmount * 40
                    "blueSeven" -> balance + betAmount * 40
                    "pinkSeven" -> balance + betAmount * 30
                    "greenSeven" -> balance + betAmount * 20
                    else -> balance
                }
            } else if (firstSlot.type == "yellow" && secondSlot.type == "green" && thirdSlot.type == "blue") {
                balance + betAmount * 300
            } else {
                balance - betAmount
            }

        balance = newAmount
        return ResponseSlotMachineWinner(
            balance = balance,
            username = username,
            firstSlot = firstSlot,
            secondSlot = secondSlot,
            thirdSlot = thirdSlot
        )
    }

}