package demo.application.services

import demo.application.dto.CreditApplication
import org.springframework.stereotype.Component


@Component
class ServiceScore (
    val PDN : ServicePDN
) {
    fun getScore(creditApplication: CreditApplication): Boolean {
        if (creditApplication.user.age <= 18) {
            return false
        }
        return PDN.Calc(creditApplication.user.name, creditApplication.monthlyPayment) >= 0
    }
}