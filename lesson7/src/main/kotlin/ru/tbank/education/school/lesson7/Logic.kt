package ru.tbank.education.school.lesson7

import java.time.Duration
import java.time.LocalDate
import kotlin.math.pow

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */
fun currentBalance(deposit: Deposit) = deposit.initialDeposit + (
    1 + (
        deposit.`сложная процентная ставка`.let {
            if (deposit.isVip == 1) {
                it + 1
            } else {
                it
            }
        }
        ) / 356
    ).pow(Duration.between(deposit.createAt, LocalDate.now()).toDays().toDouble())
