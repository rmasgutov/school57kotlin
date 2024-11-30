package demo.application.controller

import java.time.LocalDateTime

data class User(
    val age: Int,
    val name: String,
    val sex: Int,
    val creditScore: Int,
    val income: Long,
    val loans: List<Loan>,
) {
}