package ru.tbank.education.school.lesson7.practise

data class Client(
    val name: String,
    val email: String,
    val phone: String
)

infix fun <A, B, C> ((A) -> B).andThen(next: (B) -> C): (A) -> C = { a -> next(this(a)) }

fun main() {
    val rawClients = listOf(
        Client("  Иван  ", "  IVAN@MAIL.RU  ", " +7 (999) 123-45-67 "),
        Client("   мария", "maria@mail.ru", "8-800-555-35-35"),
        Client(" ", "test@", "000"),
    )

    val trim: (String) -> String = { it.trim() }
    val lower: (String) -> String = { it.lowercase() }
    val removeSpaces: (String) -> String = { it.replace(" ", "") }
    val validateEmail: (String) -> String? = { if ("@" in it && "." in it) it else null }

    val normalizeName = trim andThen lower
    val normalizeEmail = trim andThen lower andThen validateEmail
    val normalizePhone = trim andThen removeSpaces

    val normalizeClient: (Client) -> Client? = { client ->
        val name = normalizeName(client.name)
        val email = normalizeEmail(client.email)
        val phone = normalizePhone(client.phone)

        if (email == null) null else Client(name, email, phone)
    }

    val cleanClients = rawClients.mapNotNull(normalizeClient)
}
