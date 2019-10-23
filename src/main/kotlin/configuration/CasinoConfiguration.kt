package configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import roulette.RouletteTable

@Configuration
class CasinoConfiguration {

    @Bean
    fun initRouletteTables():List<RouletteTable>{
        return mutableListOf()
    }

}