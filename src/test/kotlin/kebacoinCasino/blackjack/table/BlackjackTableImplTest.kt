package kebacoinCasino.blackjack.table

import junit.framework.Assert.assertEquals
import kebacoinCasino.blackjack.table.card.Card
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.springframework.boot.test.context.SpringBootTest

@RunWith(JUnit4::class)
@SpringBootTest
class BlackjackTableImplTest {

    val blackjackTable = BlackjackTableImpl(amount = 10, username = "a")

    @Test
    fun tableInitTest() {
        assertEquals(10, blackjackTable.amount)
        assertEquals(52, blackjackTable.deck.size)
        assertEquals(false, blackjackTable.state.over)
        assertEquals(false, blackjackTable.state.playerOut)
        assertEquals(false, blackjackTable.state.houseOut)
        assertEquals(0, blackjackTable.state.houseCards.size)
        assertEquals(0, blackjackTable.state.houseValue)
        assertEquals(emptyList<Card>(), blackjackTable.state.playerCards)
        assertEquals(0, blackjackTable.state.playerCards.size)
        assertEquals(0, blackjackTable.state.playerValue)
        assertEquals(emptyList<Card>(), blackjackTable.state.houseCards)
        assertEquals(11, blackjackTable.deck.maxBy { it.value }?.value)
        assertEquals(2, blackjackTable.deck.minBy { it.value }?.value)
        assertEquals("ace", blackjackTable.deck.maxBy { it.value }?.type)
    }

    @Test
    fun betTest() {
        blackjackTable.takeBet(10)
        if (blackjackTable.state.houseValue != 21 && blackjackTable.state.playerValue != 21) {
            assertEquals(false, blackjackTable.state.over)
        }else assertEquals(true,blackjackTable.state.over)

        if(blackjackTable.state.houseValue>15){
            assertEquals(true,blackjackTable.state.houseOut)
        } else assertEquals(false,blackjackTable.state.houseOut)

        blackjackTable.takeAction("out")
        if(blackjackTable.state.houseValue>15){
            assertEquals(true,blackjackTable.state.houseOut)
        } else assertEquals(false,blackjackTable.state.houseOut)
        assertEquals(true,blackjackTable.state.playerOut)
        if(blackjackTable.state.over && blackjackTable.state.houseValue>blackjackTable.state.playerValue){
            assertEquals("house",blackjackTable.state.won)
        }
    }
}