package ru.tbank.education.school.lesson3.homework.email

import java.util.*

data class Attachment(
    val filename: String,
    val sizeBytes: Long,
) {
    val id: String = UUID.randomUUID().toString()

    init {
        require(filename.isNotBlank())
        require(sizeBytes > 0)
    }

    /*
    Mock
     */
    constructor() : this(UUID.randomUUID().toString(), Random().nextLong(100, 10_000))
}
