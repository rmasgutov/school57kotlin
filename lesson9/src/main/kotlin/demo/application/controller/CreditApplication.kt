package demo.application.controller

import java.time.LocalDateTime

data class CreditApplication(
    val createdAt: LocalDateTime,
    val totalAmount: Long,
    val monthlyPayment: Long,
    val user: User,
)//