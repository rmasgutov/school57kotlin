package ru.tbank.education.school.ru.tbank.education.school.lesson4.Hospital

abstract class User(
    protected val name: String,
    protected val email: String,
    protected val password: String
) {
    abstract val role: UserRole

    // Вывод имени
    val formattedName: String
        get() = name.split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }

    abstract fun login(): Boolean

    fun getEmailAddress(): String = email
    fun getPasswordValue(): String = password
}