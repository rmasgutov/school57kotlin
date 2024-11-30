package demo.application

import demo.application.controller.CreditApplication
import demo.application.controller.ScoreController
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	lateinit var scoreController: ScoreController

	@Test
	fun someTest() {
		val request = CreditApplication()
		val result = scoreController.simpleScore(request)
		Assertions.assertEquals(true, result)
	}

	@Test
	fun otherTest() {
		val request = CreditApplication()
		val result = scoreController.simpleScore(request)
		Assertions.assertEquals(false, result)
	}

}
