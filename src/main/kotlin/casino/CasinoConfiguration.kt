package casino

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import roulette.RouletteManager

@Configuration
class CasinoConfiguration {

    @Bean
    fun rouletteManagerInitializer():RouletteManager{
        return RouletteManager()
    }
}