package ru.tbank.education.school.lesson3.homework.interfaces

import ru.tbank.education.school.lesson3.homework.dataclasses.EmailMessage

interface MailboxPort {
    fun add(message: EmailMessage)
    fun read(email: EmailMessage): EmailMessage
    fun read(emailId: String): EmailMessage?
    fun delete(email: EmailMessage)
    fun delete(emailId: String)
    fun list(): List<EmailMessage>
    fun listUnread(): List<EmailMessage>
}
