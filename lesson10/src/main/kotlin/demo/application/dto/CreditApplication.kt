package demo.application.dto

import java.time.LocalDateTime

data class CreditApplication(
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val totalAmount: Long,
    val monthlyPayment: Long,
    val user: User
)