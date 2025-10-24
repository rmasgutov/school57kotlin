package ru.tbank.education.school.lesson6.creditriskanalyzer.models

import java.util.UUID

enum class Region {
    MOSCOW, SPB, KAZAN, NOVOSIBIRSK, OTHER
}

data class Client(
    val id: UUID,
    val fullName: String,
    val age: Int,
    val region: Region
)