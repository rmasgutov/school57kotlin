package demo.application.service

import demo.application.client.UserGetter
import demo.application.dto.User
import org.springframework.stereotype.Service

@Service
class CalculatorService(val userGetter: UserGetter, val pdnService: PdnService) {
    fun run(userId: String, monthlyPayment: Long): Long {
        val user: User = userGetter.getUserById(userId)

        return pdnService.run(user, monthlyPayment)
    }
}
