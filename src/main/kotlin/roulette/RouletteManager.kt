package roulette

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class RouletteManager{

    @Autowired
    lateinit var tables:MutableList<RouletteTable>
}