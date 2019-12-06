package kebacoinCasino.slotmachine.manager

import kebacoinCasino.slotmachine.machine.SlotMachineImpl
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.springframework.boot.test.context.SpringBootTest

@RunWith(JUnit4::class)
@SpringBootTest
class SlotMachineManagerImplTest {

    val slotMachineManager=SlotMachineManagerImpl(mutableListOf())

    @Test
    fun tableTests() {
        assertEquals(0,slotMachineManager.getNextFreeMachine())

        slotMachineManager.addSlotMachine(SlotMachineImpl(amount = 10,username = "a"))
        assertEquals(1,slotMachineManager.getNextFreeMachine())
        assertEquals(10,slotMachineManager.getSlotMachine(0).amount)

        slotMachineManager.addSlotMachine(SlotMachineImpl(amount = 0,username = "b"))
        assertEquals(2,slotMachineManager.getNextFreeMachine())

        slotMachineManager.removeSlotMachine(0)
        assertEquals(1,slotMachineManager.getNextFreeMachine())
        assertEquals("b",slotMachineManager.getSlotMachine(0).username)
        assertEquals(0,slotMachineManager.getSlotMachine(0).amount)
    }

}