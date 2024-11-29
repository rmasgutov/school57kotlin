package demo.application.controller

import demo.application.dto.User
import org.springframework.stereotype.Component
import kotlin.random.Random

// Доступ до CRM возможен только с продового сервера. На тесте используем сгенеренных пользователей
@Component
class SyntheticUserGenerator {

    fun generateUser() = User(
        name = "test",
        age = 0,
        sex = Random.nextInt(),
        income = Random.nextLong(),
        loans = listOf()
    )
}