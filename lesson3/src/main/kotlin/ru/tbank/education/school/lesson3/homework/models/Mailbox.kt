package ru.tbank.education.school.lesson3.homework.models

import ru.tbank.education.school.lesson3.homework.dataclasses.EmailMessage
import ru.tbank.education.school.lesson3.homework.interfaces.MailboxPort

internal class InMemoryMailbox : MailboxPort {
    private val emails: MutableList<EmailMessage> = mutableListOf()
    private val readEmails: MutableSet<EmailMessage> = mutableSetOf()

    override fun add(message: EmailMessage) {
        emails.add(message)
    }

    override fun read(email: EmailMessage): EmailMessage {
        readEmails.add(email)
        return email
    }

    override fun read(emailId: String): EmailMessage? {
        return emails.find { it.id == emailId }?.also { read(it) }
    }

    override fun delete(email: EmailMessage) {
        emails.remove(email)
        readEmails.remove(email)
    }

    override fun delete(emailId: String) {
        emails.find { it.id == emailId }?.let { delete(it) }
    }

    override fun list(): List<EmailMessage> = emails.toList()

    override fun listUnread(): List<EmailMessage> = emails.toSet().minus(readEmails).toList()
}
