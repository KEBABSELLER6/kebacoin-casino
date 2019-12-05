package kebacoinCasino.blackjack.manager

import kebacoinCasino.blackjack.table.BlackjackTable

interface BlackjackManager {

    fun addTable(table: BlackjackTable)

    fun removeTable(table: BlackjackTable)

    fun getTable(index: Int): BlackjackTable

    fun getNextFreeTable():Int
}