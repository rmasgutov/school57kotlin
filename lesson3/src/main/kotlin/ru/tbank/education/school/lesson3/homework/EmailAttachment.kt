package ru.tbank.education.school.lesson3.homework

import java.util.*
import kotlin.random.Random

data class EmailAttachment(
    val filename: String,
    val sizeBytes: Long,
) {
    val id: String = UUID.randomUUID().toString()

    init {
        require(filename.isNotBlank())
        require(sizeBytes > 0)
    }

    constructor() : this(UUID.randomUUID().toString(), Random.nextLong(100, 10_000))
}
