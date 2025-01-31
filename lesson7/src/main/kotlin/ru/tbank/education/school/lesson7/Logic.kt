package ru.tbank.education.school.lesson7

import java.time.LocalDate
import kotlin.math.pow

/**
 * ONE_HUNDRED, DAYS_IN_YEAR, DAYS_IN_MONTHS константы, обозначающие числа 100, 365 и 30
 */

const val ONE_HUNDRED = 100

/**
 * ONE_HUNDRED, DAYS_IN_YEAR, DAYS_IN_MONTHS константы, обозначающие числа 100, 365 и 30
 */

const val DAYS_IN_YEAR = 365

/**
 * ONE_HUNDRED, DAYS_IN_YEAR, DAYS_IN_MONTHS константы, обозначающие числа 100, 365 и 30
 */

const val DAYS_IN_MONTHS = 30

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */

fun currentBalance(deposit: Deposit): Double {
    val rate = deposit.compoundInterestRate.toDouble() / ONE_HUNDRED
    val countDay = LocalDate.now().toEpochDay() - deposit.createAt.toEpochDay()
    val totalByRate = deposit.initialDeposit * (1 + countDay / DAYS_IN_YEAR).toDouble().pow(rate)
    val countMonth = countDay / DAYS_IN_MONTHS
    val bonus = deposit.initialDeposit * countMonth
    return totalByRate + bonus
}
