package demo.application.util

import demo.application.dto.User
import org.springframework.stereotype.Component

// Доступ до CRM возможен только с продового сервера. На тесте используем сгенеренных пользователей
@Component
class SyntheticUserGenerator {

    fun generateUser() = User(
        name = "Test User",
        age = (18..65).random(),
        sex = User.Sex.entries.toTypedArray().random(),
        income = (1..(1e6.toLong())).random(),
        loans = listOf()
    )

}