package kebacoinCasino.slotmachine.manager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kebacoinCasino.slotmachine.machine.SlotMachine

@Service
class SlotMachineManagerImpl(
    @Autowired
    private val slotMachines: MutableList<SlotMachine>
) : SlotMachineManager {


    override fun getNextFreeMachine(): Int = slotMachines.size

    override fun addSlotMachine(slotMachine: SlotMachine) {
        slotMachines.add(slotMachine)
    }

    override fun getSlotMachine(id: Int) = slotMachines[id]
}