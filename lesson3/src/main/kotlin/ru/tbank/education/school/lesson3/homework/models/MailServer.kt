package ru.tbank.education.school.lesson3.homework.models

import ru.tbank.education.school.lesson3.homework.dataclasses.EmailMessage
import ru.tbank.education.school.lesson3.homework.interfaces.MailServerPort
import ru.tbank.education.school.lesson3.homework.interfaces.MailboxFactory

class MailServer(
    val name: String,
    private val mailboxFactory: MailboxFactory,
    private val maxTotalAttachmentPerMessage: Long = 100_000L
) : MailServerPort {
    private val _accounts: MutableMap<String, Account> = mutableMapOf()

    override fun register(vararg accounts: Account) {
        for (account in accounts) {
            val key = account.email.lowercase()
            require(!_accounts.containsKey(key)) { "account already registered: ${account.email}" }
            _accounts[key] = account
            account.attach(this, mailboxFactory.create())
        }
    }

    override fun find(email: String): Account? = _accounts[email.lowercase()]

    override fun send(message: EmailMessage): Map<String, DeliveryResult> {
        val results = mutableMapOf<String, DeliveryResult>()
        val sender = find(message.from)
            ?: return message.recipients.associateWith { DeliveryResult.PermanentFailure("sender not found") }

        if (!sender.canSend()) {
            return message.recipients.associateWith { DeliveryResult.PermanentFailure("sender not allowed to send") }
        }

        val totalAttachmentSize = message.attachments.sumOf { it.sizeBytes }
        if (totalAttachmentSize > maxTotalAttachmentPerMessage) {
            message.recipients.forEach { results[it] = DeliveryResult.TransientFailure("attachments too large") }
            return results
        }

        for (recipient in message.recipients) {
            val receiver = find(recipient)
            if (receiver == null) {
                results[recipient] = DeliveryResult.PermanentFailure("recipient not found")
                continue
            }
            receiver.deliver(message)
            results[recipient] = DeliveryResult.Success(message.id)
        }

        return results
    }
}
