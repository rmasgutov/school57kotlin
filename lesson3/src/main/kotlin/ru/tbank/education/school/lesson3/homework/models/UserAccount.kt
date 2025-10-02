package ru.tbank.education.school.lesson3.homework.models

open class UserAccount(email: String, displayName: String? = "") : Account(email, displayName) {
    constructor(config: Map<String, String>) : this(
        config.getValue("email"),
        config["displayName"]
    )
}
