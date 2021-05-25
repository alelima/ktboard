package nitrox.org.ktboard

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KtboardApplication

fun main(args: Array<String>) {
	runApplication<KtboardApplication>(*args)
}
