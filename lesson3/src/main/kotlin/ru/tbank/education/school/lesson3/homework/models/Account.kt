package ru.tbank.education.school.lesson3.homework.models

import ru.tbank.education.school.lesson3.homework.dataclasses.EmailMessage
import java.util.*

abstract class Account(val email: String, displayName: String? = null) {
    val id: String = UUID.randomUUID().toString()
    var displayName: String? = displayName
        set(value) {
            field = value?.trim()?.takeIf { it.isNotBlank() }
        }

    private var mailbox: Mailbox? = null
    private var server: MailServer? = null

    internal fun attach(server: MailServer, box: Mailbox) {
        require(mailbox == null) { "mailbox already assigned" }
        mailbox = box
        this.server = server
    }

    internal open fun deliver(emailMessage: EmailMessage) {
        mailbox?.add(emailMessage) ?: throw IllegalStateException("account is not registered")
    }

    open fun canSend(): Boolean = true

    fun register(server: MailServer) {
        server.register(this)
    }

    fun inbox(): List<EmailMessage> {
        return this.mailbox?.list() ?: throw IllegalStateException("account is not registered")
    }

    fun inboxUnread(): List<EmailMessage> {
        return this.mailbox?.listUnread() ?: throw IllegalStateException("account is not registered")
    }

    fun read(vararg emailMessages: EmailMessage) {
        for (emailMessage in emailMessages) {
            this.mailbox?.read(emailMessage) ?: throw IllegalStateException("account is not registered")
        }
    }
}
