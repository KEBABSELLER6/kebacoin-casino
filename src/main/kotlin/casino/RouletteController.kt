package casino

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import roulette.NewTable
import roulette.RouletteManager

@RestController
class RouletteController :GameController{

    val rouletteManager=RouletteManager()

    @GetMapping("/getTable")
    fun getTable() : NewTable {
        return NewTable("asd",1)
    }
}