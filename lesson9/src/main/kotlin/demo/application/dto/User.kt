package demo.application.dto

import java.time.LocalDateTime

data class User(
    val age: Int,
    val name: String,
    val sex: Int,
    val income: Long,
    val loans: List<Loan>,
) {

    data class Loan(
        val creteAt: LocalDateTime,
        val isClose: Boolean,
        val monthlyPayment: Long,
    )
}
