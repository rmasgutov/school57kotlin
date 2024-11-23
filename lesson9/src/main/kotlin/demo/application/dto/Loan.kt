package demo.application.dto

import java.time.LocalDateTime

data class Loan(
        val createdAt: LocalDateTime,
        val amount: Long,
        val isClosed: Boolean,
        val monthlyPayment: Long,
)
