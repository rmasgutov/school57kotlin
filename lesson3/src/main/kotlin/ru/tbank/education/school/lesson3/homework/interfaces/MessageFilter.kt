package ru.tbank.education.school.lesson3.homework.interfaces

import ru.tbank.education.school.lesson3.homework.dataclasses.EmailMessage

fun interface MessageFilter {
    fun accept(message: EmailMessage): Boolean
}
