package demo.application.controller

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Boolean
import demo.application.dto.User
import kotlin.random.Random

// Доступ до CRM возможен только с продового сервера. На тесте используем сгенеренных пользователей
class SyntheticUserGenerator {

    fun generateUser() = User(
        name = "test",
        age = 0,
        sex = Random.nextInt() % 2,
        income = Random.nextLong(),
        loans = listOf()
    )
}