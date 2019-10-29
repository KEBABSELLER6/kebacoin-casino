package configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import roulette.RouletteTable
import roulette.RouletteTableImpl
import slotmachine.SlotMachine
import slotmachine.SlotMachineImpl

@Configuration
class CasinoConfiguration {

    @Bean
    fun initRouletteTables():List<RouletteTable>{
        return mutableListOf<RouletteTableImpl>()
    }

    @Bean
    fun initSlotMachines():List<SlotMachine>{
        return mutableListOf<SlotMachineImpl>()
    }

}