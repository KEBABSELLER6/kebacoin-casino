package kebacoinCasino.roulette.manager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kebacoinCasino.roulette.table.RouletteTable

@Service
class RouletteManagerImpl(
    @Autowired
    private val tables: MutableList<RouletteTable>
): RouletteManager {
    override fun removeTable(index: Int) {
        tables.removeAt(index)
    }

    override fun getNextFreeTable(): Int = tables.size

    override fun addTable(rouletteTable: RouletteTable) {
        tables.add(rouletteTable)
    }

    override fun getTable(id: Int) = tables[id]

}