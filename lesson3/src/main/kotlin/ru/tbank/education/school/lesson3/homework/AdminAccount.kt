package ru.tbank.education.school.lesson3.homework

class AdminAccount(email: String, displayName: String) : UserAccount(email, displayName) {
    override fun deliver(emailMessage: EmailMessage) {
        val scamKeywords = listOf("scam", "бесплатные деньги")
        val newsletterKeywords = listOf("new", "breaking")

        if (scamKeywords.any { emailMessage.bodyPlain.contains(it) } or scamKeywords.any {
                emailMessage.subject.contains(
                    it
                )
            } or scamKeywords.any {
                emailMessage.subject.contains(
                    it
                )
            }) markAsSpam(emailMessage)
        if (newsletterKeywords.any { emailMessage.bodyPlain.contains(it) } or newsletterKeywords.any {
                emailMessage.subject.contains(
                    it
                )
            } or newsletterKeywords.any {
                emailMessage.subject.contains(
                    it
                )
            }) markAsNewsletter(emailMessage)

        super.deliver(emailMessage)
    }

    fun markAsSpam(emailMessage: EmailMessage) {
        emailMessage.addFlag(EmailMessageFlag.SPAM)
    }

    fun markAsNewsletter(emailMessage: EmailMessage) {
        emailMessage.addFlag(EmailMessageFlag.NEWSLETTER)
    }
}
