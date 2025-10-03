package ru.tbank.education.school.lesson3.homework.interfaces

import ru.tbank.education.school.lesson3.homework.dataclasses.EmailMessage
import ru.tbank.education.school.lesson3.homework.models.Account
import ru.tbank.education.school.lesson3.homework.models.DeliveryResult

interface MailServerPort {
    fun register(vararg accounts: Account)
    fun find(email: String): Account?
    fun send(message: EmailMessage): Map<String, DeliveryResult>
}
