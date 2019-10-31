package blackjack

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BlackjackManagerImpl(
    @Autowired
    override var tables: MutableList<BlackjackTable>
) : BlackjackManager {
    override fun addTable(table: BlackjackTable) {
        tables.add(table)
    }

    override fun removeTable(table: BlackjackTable) {
        tables.remove(table)
    }

    override fun getTable(index: Int) = tables[index]
}