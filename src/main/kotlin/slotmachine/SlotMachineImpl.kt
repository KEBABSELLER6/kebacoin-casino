package slotmachine

import entity.Player
import slotmachine.responses.ResponseWinner

class SlotMachineImpl(
    override val player: Player,
    override var slots: Array<MutableList<Slot>> = Array(3) { i -> initializeSlot().toMutableList() }
) : SlotMachine {

    companion object : SlotInitializer {
        override fun initializeSlot(): List<Slot> {
            return mutableListOf(
                SlotImpl("orange"), SlotImpl("yellow"), SlotImpl("green"),
                SlotImpl("blue"), SlotImpl("pink"), SlotImpl("blueSeven"),
                SlotImpl("pinkSeven"), SlotImpl("greenSeven")
            )
        }

    }

    override fun takeBet(betAmount: Int): ResponseWinner {
        val firstSlot = slots[0].apply { shuffle() }[0]
        val secondSlot = slots[1].apply { shuffle() }[0]
        val thirdSlot = slots[2].apply { shuffle() }[0]

        val newAmount: Int =
            if (firstSlot.type == secondSlot.type && firstSlot.type == thirdSlot.type && secondSlot.type == thirdSlot.type) {
                when (firstSlot.type) {
                    "orange" -> player.amount + betAmount * 600
                    "yellow" -> player.amount + betAmount * 120
                    "green" -> player.amount + betAmount * 100
                    "blue" -> player.amount + betAmount * 80
                    "pink" -> player.amount + betAmount * 40
                    "blueSeven" -> player.amount + betAmount * 40
                    "pinkSeven" -> player.amount + betAmount * 30
                    "greenSeven" -> player.amount + betAmount * 20
                    else -> player.amount
                }
            } else if (firstSlot.type == "yellow" && secondSlot.type == "green" && thirdSlot.type == "blue") {
                player.amount + betAmount * 300
            } else {
                player.amount - betAmount
            }

        player.amount = newAmount
        return ResponseWinner(player = player, firstSlot = firstSlot, secondSlot = secondSlot, thirdSlot = thirdSlot)
    }

}