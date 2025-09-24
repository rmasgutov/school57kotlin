package ru.tbank.education.school.lesson3.homework.email

import ru.tbank.education.school.lesson3.isValidEmail
import java.util.*

data class EmailMessage(
    val from: String,
    val to: List<String>,
    val subject: String,
    val body: String = "",
    val attachments: List<Attachment> = emptyList(),
) {
    val id: String = UUID.randomUUID().toString()
    private val _flags: MutableList<EmailMessageFlag> = mutableListOf()
    val flags: List<EmailMessageFlag> get() = _flags.toList()

    init {
        require(from.isValidEmail()) { "Email message must have a valid sender" }
        require(to.isNotEmpty()) { "Email message must have a non-empty list of recipients" }
        require(to.all { it.isValidEmail() }) { "Email message must recipients must have a valid email" }
    }

    val preview: String
        get() = this.body.take(100)
}
