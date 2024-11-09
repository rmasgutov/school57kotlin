package ru.tbank.education.school.lesson7

import java.time.Duration
import java.time.LocalDate
import kotlin.math.pow

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */
fun currentBalance(deposit: Deposit) = deposit.initialDeposit * (
    1 + (
        deposit.`сложная процентная ставка`.let {
            if (deposit.isVip) {
                deposit.it + 1
            } else {
                deposit.it
            }
        }
        ) / 100
    ).pow(min(Duration.between(deposit.createAt, LocalDate.now()).toDays().toDouble(), deposit.durationMonth * 30))
