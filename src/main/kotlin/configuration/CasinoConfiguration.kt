package configuration

import blackjack.table.BlackjackTable
import blackjack.table.BlackjackTableImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import roulette.table.RouletteTable
import roulette.table.RouletteTableImpl
import slotmachine.machine.SlotMachine
import slotmachine.machine.SlotMachineImpl

@Configuration
class CasinoConfiguration {

    @Bean
    fun initRouletteTables(): List<RouletteTable> = mutableListOf<RouletteTableImpl>()

    @Bean
    fun initSlotMachines(): List<SlotMachine> = mutableListOf<SlotMachineImpl>()

    @Bean
    fun initBlackjackTables(): List<BlackjackTable> = mutableListOf<BlackjackTableImpl>()

}