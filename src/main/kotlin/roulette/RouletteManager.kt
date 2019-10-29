package roulette

interface RouletteManager{
    var tables:MutableList<RouletteTable>

    fun addTable(rouletteTable:RouletteTable)

    fun getTable(id:Int):RouletteTable
}