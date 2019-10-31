package blackjack

interface BlackjackManager {

    var tables:MutableList<BlackjackTable>

    fun addTable(table: BlackjackTable)

    fun removeTable(table: BlackjackTable)

    fun getTable(index: Int):BlackjackTable
}