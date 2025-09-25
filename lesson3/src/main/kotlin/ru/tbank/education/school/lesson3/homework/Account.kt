package ru.tbank.education.school.lesson3.homework

import java.util.*

abstract class Account(val email: String, displayName: String? = null) {
    val id: String = UUID.randomUUID().toString()

    protected var displayName: String? = displayName
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

    internal fun deliver(message: EmailMessage) {
        mailbox?.add(message) ?: throw IllegalStateException("account is not registered")
    }

    open fun canSend(): Boolean = true

    fun register(server: MailServer) {
        server.register(this)
    }
}
