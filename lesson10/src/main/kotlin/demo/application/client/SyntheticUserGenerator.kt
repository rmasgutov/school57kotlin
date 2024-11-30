package demo.application.client

import demo.application.dto.Loan
import demo.application.dto.User
import kotlin.random.Random
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

// Доступ до CRM возможен только с продового сервера. На тесте используем сгенеренных пользователей
@Profile("dev")
@Component
class SyntheticUserGenerator : UserGetter {
    override fun getUserById(userId: String) =
            User(
                    name = "test",
                    age = 0,
                    sex =
                            if (Random.nextBoolean()) {
                                User.Sex.MALE
                            } else {
                                User.Sex.FEMALE
                            },
                    income = Random.nextLong(),
                    loans = listOf<Loan>()
            )
}
