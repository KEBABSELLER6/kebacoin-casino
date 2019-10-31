package casino

import configuration.CasinoConfiguration
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootApplication(scanBasePackages=["roulette","casino","configuration","slotmachine","blackjack"])
class Application

fun main(args: Array<String>) {

    SpringApplication.run(Application::class.java, *args)

    val context= AnnotationConfigApplicationContext(CasinoConfiguration::class.java)
}