package demo.application

import demo.application.controller.ScoreController
import org.junit.jupiter.api.Assertions
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    lateinit var scoreController: ScoreController

    fun someTest() {
        val request = TODO()
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(true, result)
    }


    fun otherTest() {
        val request = TODO()
        val result = scoreController.simpleScore(request)
        Assertions.assertEquals(false, result)
    }

}
