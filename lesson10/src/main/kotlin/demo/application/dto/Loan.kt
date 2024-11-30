package demo.application.dto

import java.time.LocalDateTime

data class Loan(
        val cretedAt: LocalDateTime = LocalDateTime.now(),
        val amount: Long,
        val isClosed: Boolean = false,
        val monthlyPayment: Long,
)

