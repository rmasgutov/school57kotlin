package ru.tbank.education.school.lesson3

import ru.tbank.education.school.lesson3.homework.dataclasses.EmailAttachment
import ru.tbank.education.school.lesson3.homework.dataclasses.EmailMessage
import ru.tbank.education.school.lesson3.homework.interfaces.MailboxFactory
import ru.tbank.education.school.lesson3.homework.interfaces.MessageFilter
import ru.tbank.education.school.lesson3.homework.models.AdminAccount
import ru.tbank.education.school.lesson3.homework.models.InMemoryMailbox
import ru.tbank.education.school.lesson3.homework.models.MailServer
import ru.tbank.education.school.lesson3.homework.models.UserAccount

fun main() {
    val mailboxFactory = MailboxFactory { InMemoryMailbox() }
    val promotionalFilter = MessageFilter { msg ->
        val keywords = listOf("promo", "discount", "free", "бесплатно", "scam")
        val content = (msg.subject + " " + msg.bodyPlain).lowercase()
        keywords.any { it in content }
    }
    val server = MailServer("itqdev.xyz", mailboxFactory)

    val user1 = UserAccount("itq.dev@ya.ru", "ITQ")
    val user2 = UserAccount(
        mapOf<String, String>(
            "email" to "kit@yandex-team.ru",
            "displayName" to "Yandex KIT"
        )
    )
    val admin = AdminAccount("contanct@itqdev.xyz", "Root Admin")

    server.register(user1, user2, admin)

    val msg1 = EmailMessage(
        from = user1.email,
        cc = listOf(user2.email),
        bcc = listOf("unknown@nowhere.test", admin.email),
        subject = "scam",
        attachments = listOf(EmailAttachment("readme.txt", 1024)),
    )
    val results1 = server.send(msg1)
    println("msg1 delivery results:")
    results1.forEach { (rcpt, res) -> println("$rcpt -> $res") }

    println()

    val msg2 = EmailMessage(
        from = admin.email,
        bcc = listOf(user1.email, user2.email),
        subject = "Big news",
        bodyPlain = "News about internship at Yandex Team",
        attachments = listOf(EmailAttachment("readme.txt", 1024))
    )
    val results2 = server.send(msg2)
    println("msg2 delivery results:")
    results2.forEach { (rcpt, res) -> println("$rcpt -> $res") }

    user1.read(msg1, msg2)
    user2.read(msg1)

    println()
    println("${user1.displayName} inbox:\n${user1.inbox()}")
    println("${user1.displayName} unread inbox:\n${user1.inboxUnread()}")
    println("${user2.displayName} inbox:\n${user2.inbox()}")
    println("${user2.displayName} unread inbox:\n${user2.inbox()}")
    println(
        "${user2.displayName} promotes in inbox:\n${
            user2.inbox().filter { promotionalFilter.accept(it) }.map { it }
        }"
    )
    println("${admin.displayName} inbox:\n${admin.inbox()}")
    println("${admin.displayName} unread inbox:\n${admin.inbox()}")
}
