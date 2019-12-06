package kebacoinCasino.blackjack.manager

import kebacoinCasino.blackjack.table.BlackjackTable
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BlackjackManagerImpl(
    @Autowired
    private val tables: MutableList<BlackjackTable>
) : BlackjackManager {
    override fun removeTable(index: Int) {
        tables.removeAt(index)
    }

    override fun getNextFreeTable(): Int = tables.size

    override fun addTable(table: BlackjackTable) {
        tables.add(table)
    }

    override fun getTable(index: Int) = tables[index]
}