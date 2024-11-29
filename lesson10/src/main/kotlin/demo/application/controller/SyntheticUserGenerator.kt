package demo.application.controller

import demo.application.dto.User
import kotlin.random.Random

// Доступ до CRM возможен только с продового сервера. На тесте используем сгенеренных пользователей
class SyntheticUserGenerator {

    fun generateUser() = User(
        name = "test",
        age = 0,
        sex = Random.nextInt(),
        income = Random.nextLong(),
        loans = listOf()
    )
}