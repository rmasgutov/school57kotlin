package ru.tbank.education.school.lesson3.homework

import ru.tbank.education.school.lesson3.isValidEmail
import java.time.Instant
import java.util.*

data class EmailMessage(
    val from: String,
    val cc: List<String> = emptyList(),
    private val bcc: List<String> = emptyList(),
    val subject: String,
    val bodyPlain: String = "",
    val bodyHtml: String = "",
    val attachments: List<EmailAttachment> = emptyList()
) {
    val id: String = UUID.randomUUID().toString()
    val createdAt: Instant = Instant.now()
    private val _flags: MutableList<EmailMessageFlag> = mutableListOf()
    val flags: List<EmailMessageFlag> get() = _flags.toList()

    init {
        require(from.isValidEmail()) { "email message must have a valid sender" }
        require(cc.isNotEmpty() || bcc.isNotEmpty()) { "message must have at least one recipient" }
        require(cc.all { it.isValidEmail() }) { "CC recipients must have valid emails" }
        require(bcc.all { it.isValidEmail() }) { "BCC recipients must have valid emails" }
    }

    val preview: String
        get() = bodyPlain.take(20)

    val recipients: List<String>
        get() = cc + bcc

    internal fun addFlag(flag: EmailMessageFlag) {
        _flags.add(flag)
    }

    internal fun removeFlag(flag: EmailMessageFlag) {
        _flags.remove(flag)
    }
}
