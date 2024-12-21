package demo.application.controller

import java.time.LocalDateTime

data class Loan(
    val createAt: LocalDateTime,
    val loanTerm: LocalDateTime,
    val isClose: Boolean,
    val monthlyPayment: Long,
)