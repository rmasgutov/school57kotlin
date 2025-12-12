package demo.application.dto

import java.time.LocalDateTime

data class Loan(
    val creteAt: LocalDateTime,
    val isClose: Boolean,
    val monthlyPayment: Long,
)