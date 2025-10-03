package ru.tbank.education.school.lesson3.homework.models

import ru.tbank.education.school.lesson3.homework.dataclasses.EmailMessage

class AdminAccount(email: String, displayName: String) : UserAccount(email, displayName) {
    override fun deliver(emailMessage: EmailMessage) {
        val scamKeywords = listOf("scam", "бесплатные деньги")
        val newsletterKeywords = listOf("new", "breaking")

        val bodyLower = emailMessage.bodyPlain.lowercase()
        val subjectLower = emailMessage.subject.lowercase()

        if (scamKeywords.any { it in bodyLower || it in subjectLower }) markAsSpam(emailMessage)
        if (newsletterKeywords.any { it in bodyLower || it in subjectLower }) markAsNewsletter(emailMessage)

        super.deliver(emailMessage)
    }

    fun markAsSpam(emailMessage: EmailMessage) {
        emailMessage.addFlag(EmailMessageFlag.SPAM)
    }

    fun markAsNewsletter(emailMessage: EmailMessage) {
        emailMessage.addFlag(EmailMessageFlag.NEWSLETTER)
    }
}
