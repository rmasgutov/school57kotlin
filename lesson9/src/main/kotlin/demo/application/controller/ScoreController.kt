package demo.application.controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
@RestController
class ScoreController(
    val logicService: LogicService,
) {
    @GetMapping("score")
    fun simpleScore(@RequestParam creditApplication: CreditApplication) =
        logicService.simpleScore(creditApplication)
}//