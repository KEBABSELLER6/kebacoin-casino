package kebacoinCasino.roulette.table

import kebacoinCasino.roulette.request.RequestRouletteBet
import kebacoinCasino.roulette.table.field.RouletteField
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.springframework.boot.test.context.SpringBootTest

@RunWith(JUnit4::class)
@SpringBootTest
class RouletteTableImplTest{
    val rouletteTable=RouletteTableImpl(amount = 100,username = "asd")
    val testBet=RequestRouletteBet(10,"single", listOf(RouletteField(1)),"asd")
    val testBet2=RequestRouletteBet(10,"split", listOf(RouletteField(1), RouletteField(2)),"asd")
    val testBet3=RequestRouletteBet(10,"firstFour", listOf(RouletteField(1), RouletteField(2),RouletteField(3),RouletteField(0)),"asd")

    @Test
    fun testInitTable(){
        assertEquals(37,rouletteTable.fields.size)
        assertEquals(0,rouletteTable.fields.minBy { it.number }?.number)
        assertEquals(36,rouletteTable.fields.maxBy { it.number }?.number)
        assertEquals(100,rouletteTable.amount)
    }

    @Test
    fun testBet(){
        rouletteTable.takeBet(testBet.type,testBet.fields,testBet.rouletteBetAmount).let {
            if(rouletteTable.fields[0]==testBet.fields[0]){
                assertEquals(440,rouletteTable.amount)
                assertEquals(rouletteTable.fields[0].number,testBet.fields[0].number)
            }else{
                assertEquals(90,rouletteTable.amount)
                assertNotEquals(rouletteTable.fields[0].number,testBet.fields[0].number)
            }
        }

        rouletteTable.takeBet(testBet2.type,testBet2.fields,testBet2.rouletteBetAmount).let {
            if(rouletteTable.fields[0] in testBet.fields){
                assertEquals(250,rouletteTable.amount)
            }else{
                assertEquals(80,rouletteTable.amount)
            }
        }

    }

    @Test
    fun testBet2(){
        rouletteTable.takeBet(testBet3.type,testBet3.fields,testBet3.rouletteBetAmount).let {
            if(rouletteTable.fields[0] in testBet.fields){
                assertEquals(170,rouletteTable.amount)
            }else{
                assertEquals(90,rouletteTable.amount)
            }
            testBet3.fields.forEach {
                assertEquals(true, it.number in 0..3)
            }
        }
    }
}