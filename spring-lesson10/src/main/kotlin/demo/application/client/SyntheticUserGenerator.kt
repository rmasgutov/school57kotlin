package demo.application.client

import demo.application.dto.User
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
@Profile("dev")
// Доступ до CRM возможен только с продового сервера. На тесте используем сгенеренных пользователей
class SyntheticUserGenerator: UserGetter {


    override fun getUserById(userId: String): User = User(
        name = "test",
        age = 0,
        sex = Random.nextInt(),
        income = Random.nextLong(),
        loans = listOf()
    )
}