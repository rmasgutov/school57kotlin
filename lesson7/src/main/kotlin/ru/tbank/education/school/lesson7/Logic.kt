package ru.tbank.education.school.lesson7

import java.time.Duration
import java.time.LocalDate
import kotlin.math.pow

/**
 * Константа, обозначающая количество дней в году.
 */
const val DAYS_IN_A_YEAR = 365

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */
fun currentBalance(deposit: Deposit) = deposit.initialDeposit + (
    1 + (deposit.complexPercent.let { if (deposit.isVip == 1) { it + 1 } else { it } }) / DAYS_IN_A_YEAR
    ).pow(Duration.between(deposit.createAt, LocalDate.now()).toDays().toDouble())
