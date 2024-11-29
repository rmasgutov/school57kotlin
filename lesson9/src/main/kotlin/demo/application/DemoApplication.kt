package demo.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
//a
@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
