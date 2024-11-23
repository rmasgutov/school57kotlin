package demo.application.dto

import java.time.LocalDateTime

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
data class Loan(
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val isClosed: Boolean = false,
    val monthlyPayment: Long,
    val amount: Long
)