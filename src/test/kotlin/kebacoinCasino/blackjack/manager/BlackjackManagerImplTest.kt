package kebacoinCasino.blackjack.manager

import kebacoinCasino.blackjack.table.BlackjackTableImpl
import org.junit.Test
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.springframework.boot.test.context.SpringBootTest

@RunWith(JUnit4::class)
@SpringBootTest
class BlackjackManagerImplTest {

    val blackjackManager: BlackjackManager=BlackjackManagerImpl(mutableListOf())

    @Test
    fun tableTests() {
        assertEquals(0,blackjackManager.getNextFreeTable())

        blackjackManager.addTable(BlackjackTableImpl(amount = 10,username = "a"))
        assertEquals(1,blackjackManager.getNextFreeTable())
        assertEquals(10,blackjackManager.getTable(0).amount)

        blackjackManager.addTable(BlackjackTableImpl(amount = 0,username = "b"))
        assertEquals(2,blackjackManager.getNextFreeTable())

        blackjackManager.removeTable(0)
        assertEquals(1,blackjackManager.getNextFreeTable())
        assertEquals("b",blackjackManager.getTable(0).username)
        assertEquals(0,blackjackManager.getTable(0).amount)
    }

}