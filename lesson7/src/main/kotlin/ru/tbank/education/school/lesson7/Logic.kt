package ru.tbank.education.school.lesson7

import java.time.Duration
import java.time.LocalDate
import kotlin.math.pow

/**
 * количество дней в году
 */

const val YEAR_DAYS = 365

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */
fun currentBalance(deposit: Deposit) = deposit.initialDeposit + (
    1 + (deposit.complexPercent.let { if (deposit.isVip == 1) { it + 1 } else { it } }) / YEAR_DAYS
    ).pow(Duration.between(deposit.createAt, LocalDate.now()).toDays().toDouble())
