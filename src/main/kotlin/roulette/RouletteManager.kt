package roulette

import org.springframework.stereotype.Component

@Component
class RouletteManager{

    val tables:MutableList<RouletteTable> = mutableListOf()
}