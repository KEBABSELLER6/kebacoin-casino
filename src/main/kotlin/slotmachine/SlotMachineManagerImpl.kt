package slotmachine

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SlotMachineManagerImpl(

    @Autowired
    override var slotMachines: MutableList<SlotMachine>)
    :SlotMachineManager {

    override fun addSlotMachine(slotMachine: SlotMachine) {
        slotMachines.add(slotMachine)
    }

    override fun getSlotMachine(id: Int)=slotMachines[id]
}