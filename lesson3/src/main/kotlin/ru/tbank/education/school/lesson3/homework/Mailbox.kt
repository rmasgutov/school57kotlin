package ru.tbank.education.school.lesson3.homework

internal class Mailbox {
    private val emails: MutableList<EmailMessage> = mutableListOf()
    private val readEmails: MutableSet<EmailMessage> = mutableSetOf()

    fun add(message: EmailMessage) {
        emails.add(message)
    }

    fun read(email: EmailMessage): EmailMessage {
        readEmails.add(email)
        return email
    }

    fun read(emailId: String): EmailMessage? {
        return emails.find { it.id == emailId }?.also { read(it) }
    }

    fun delete(email: EmailMessage) {
        emails.remove(email)
        readEmails.remove(email)
    }

    fun delete(emailId: String) {
        emails.find { it.id == emailId }?.let { delete(it) }
    }

    fun list(): List<EmailMessage> = emails.toList()

    fun listUnread(): List<EmailMessage> = emails.toSet().minus(readEmails).toList()
}
