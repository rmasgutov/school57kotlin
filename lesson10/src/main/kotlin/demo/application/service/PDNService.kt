package demo.application.service

import demo.application.dto.User
import org.springframework.stereotype.Service

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
@Service
class PDNService {

    fun calculateMaxMonthlyPayment(user: User) = user.income / 3

}