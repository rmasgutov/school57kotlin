package demo.application.service

import demo.application.client.UserGetter
import demo.application.dto.User
import org.springframework.stereotype.Service

@Service
class CalculationService(
    val userGetter: UserGetter,
    val pdnService: PdnService,
) {
    fun calculate(userId: String, monthlyPayment: Long): Long {
        val user: User = userGetter.getUserById(userId)


        return pdnService.getAdditionPdn(
            loans = user.loans,
            income = user.income
        )
    }
}