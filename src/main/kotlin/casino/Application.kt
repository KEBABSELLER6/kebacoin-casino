package casino

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.AnnotationConfigApplicationContext

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)

    val context= AnnotationConfigApplicationContext { CasinoConfiguration::class }
    context.refresh()
    val rou=context.getBean(RouletteController::class.java)
}