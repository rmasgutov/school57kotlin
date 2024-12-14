package demo.application.service

import demo.application.dto.CreditApplication
import org.springframework.stereotype.Service

@Service
class ScoreService(
    val pdnService: PdnService,
) {
    fun simpleScore(creditApplication: CreditApplication): Boolean {
        if (creditApplication.user.age <= 18) {
            return false
        }

        val additionPdn =  pdnService.getAdditionPdn(
            loans = creditApplication.user.loans,
            income = creditApplication.user.income
        )

        return (additionPdn > creditApplication.monthlyPayment)
    }

}