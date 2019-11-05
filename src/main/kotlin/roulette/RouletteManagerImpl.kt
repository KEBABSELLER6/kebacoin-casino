package roulette

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RouletteManagerImpl(
    @Autowired
    private val tables: MutableList<RouletteTable>
):RouletteManager{
    override fun getNextFreeTable(): Int = tables.size

    override fun addTable(rouletteTable: RouletteTable) {
        tables.add(rouletteTable)
    }

    override fun getTable(id: Int) = tables[id]

    override fun removeTable(rouletteTable: RouletteTable) {
        tables.remove(rouletteTable)
    }
}