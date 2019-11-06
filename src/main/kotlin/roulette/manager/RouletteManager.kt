package roulette.manager

import roulette.table.RouletteTable

interface RouletteManager{

    fun addTable(rouletteTable: RouletteTable)

    fun getTable(id:Int): RouletteTable

    fun removeTable(rouletteTable: RouletteTable)

    fun getNextFreeTable():Int
}