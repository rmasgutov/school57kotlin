package ru.tbank.education.school.lesson7

import java.time.LocalDate
import kotlin.math.pow

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */
fun currentBalance(deposit: Deposit): Double {
    val rate = deposit.`сложная процентная ставка`.toDouble() / 100
    val countDay = LocalDate.now().toEpochDay() - deposit.createAt.toEpochDay()
    val totalByRate = deposit.initialDeposit * (1 + countDay / 365).toDouble().pow(rate)
    val countMonth = countDay / 30
    val bonus = deposit.initialDeposit * countMonth
    return totalByRate + bonus
}
