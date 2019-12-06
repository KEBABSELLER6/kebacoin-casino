package kebacoinCasino.slotmachine.machine

import kebacoinCasino.slotmachine.machine.slot.SlotImpl
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.springframework.boot.test.context.SpringBootTest

@RunWith(JUnit4::class)
@SpringBootTest
class SlotMachineImplTest{

    val slotMachine=SlotMachineImpl(amount = 100,username = "asd")

    @Test
    fun testMachineInit(){
        assertEquals(3,slotMachine.slots.size)
        slotMachine.slots.forEach {
            assertEquals(8,it.size)
        }
        slotMachine.slots.forEach {
            assertEquals(true,it.contains(SlotImpl("orange")))
        }
        assertEquals(100,slotMachine.amount)
    }

    @Test
    fun testBet(){
        slotMachine.takeBet(10)
        val firstSlot = slotMachine.slots[0][0]
        val secondSlot = slotMachine.slots[1][0]
        val thirdSlot = slotMachine.slots[2][0]

        if (firstSlot.type == secondSlot.type && firstSlot.type == thirdSlot.type && secondSlot.type == thirdSlot.type) {
            assertNotEquals(90,slotMachine.amount)
        } else if (firstSlot.type == "yellow" && secondSlot.type == "green" && thirdSlot.type == "blue") {
            assertNotEquals(90,slotMachine.amount)
            assertEquals(400,slotMachine.amount)
        }else {
            assertEquals(90,slotMachine.amount)
        }
    }
}