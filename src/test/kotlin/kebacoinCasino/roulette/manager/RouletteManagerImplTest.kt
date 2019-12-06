package kebacoinCasino.roulette.manager

import kebacoinCasino.roulette.table.RouletteTableImpl
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.springframework.boot.test.context.SpringBootTest

@RunWith(JUnit4::class)
@SpringBootTest
class RouletteManagerImplTest{

    val rouletteManager=RouletteManagerImpl(mutableListOf())

    @Test
    fun tableTests() {
        assertEquals(0,rouletteManager.getNextFreeTable())

        rouletteManager.addTable(RouletteTableImpl(amount = 10,username = "a"))
        assertEquals(1,rouletteManager.getNextFreeTable())
        assertEquals(10,rouletteManager.getTable(0).amount)

        rouletteManager.addTable(RouletteTableImpl(amount = 0,username = "b"))
        assertEquals(2,rouletteManager.getNextFreeTable())

        rouletteManager.removeTable(0)
        assertEquals(1,rouletteManager.getNextFreeTable())
        assertEquals("b",rouletteManager.getTable(0).username)
        assertEquals(0,rouletteManager.getTable(0).amount)
    }
}