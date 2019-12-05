package kebacoinCasino.configuration

import kebacoinCasino.blackjack.table.BlackjackTable
import kebacoinCasino.blackjack.table.BlackjackTableImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import kebacoinCasino.roulette.table.RouletteTable
import kebacoinCasino.roulette.table.RouletteTableImpl
import kebacoinCasino.slotmachine.machine.SlotMachine
import kebacoinCasino.slotmachine.machine.SlotMachineImpl

@Configuration
class CasinoConfiguration {

    @Bean
    fun initRouletteTables(): List<RouletteTable> = mutableListOf<RouletteTableImpl>()

    @Bean
    fun initSlotMachines(): List<SlotMachine> = mutableListOf<SlotMachineImpl>()

    @Bean
    fun initBlackjackTables(): List<BlackjackTable> = mutableListOf<BlackjackTableImpl>()

}