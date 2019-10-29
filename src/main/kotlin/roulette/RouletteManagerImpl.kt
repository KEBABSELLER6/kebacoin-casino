package roulette

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RouletteManagerImpl(
    @Autowired
    override var tables: MutableList<RouletteTable>
):RouletteManager{

    override fun addTable(rouletteTable: RouletteTable) {
        tables.add(rouletteTable)
    }

    override fun getTable(id: Int) = tables[id]

}