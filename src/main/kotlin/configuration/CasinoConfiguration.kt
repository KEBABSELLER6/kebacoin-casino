package configuration

import blackjack.BlackjackTable
import blackjack.BlackjackTableImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import roulette.RouletteTable
import roulette.RouletteTableImpl
import slotmachine.SlotMachine
import slotmachine.SlotMachineImpl

@Configuration
class CasinoConfiguration {

    @Bean
    fun initRouletteTables(): List<RouletteTable> = mutableListOf<RouletteTableImpl>()

    @Bean
    fun initSlotMachines(): List<SlotMachine> = mutableListOf<SlotMachineImpl>()

    @Bean
    fun initBlackjackTables(): List<BlackjackTable> = mutableListOf<BlackjackTableImpl>()

}