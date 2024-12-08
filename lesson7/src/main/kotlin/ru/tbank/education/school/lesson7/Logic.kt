package ru.tbank.education.school.lesson7

import java.time.Duration
import java.time.LocalDate
import kotlin.math.pow

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */
fun currentBalance(deposit: Deposit) = deposit.initialDeposit + (
    1 + (
        deposit.compound_interest_rate.let {
            if (deposit.isVip) {
                it + 1
            } else {
                it
            }
        }
        ) / 365 //неправильно был указан год
    ).pow(Duration.between(deposit.createAt, LocalDate.now()).toDays().toDouble())
