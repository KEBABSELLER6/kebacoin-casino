package kebacoinCasino.roulette.manager

import kebacoinCasino.roulette.table.RouletteTable

interface RouletteManager{

    fun addTable(rouletteTable: RouletteTable)

    fun getTable(id:Int): RouletteTable

    fun removeTable(index:Int)

    fun getNextFreeTable():Int
}