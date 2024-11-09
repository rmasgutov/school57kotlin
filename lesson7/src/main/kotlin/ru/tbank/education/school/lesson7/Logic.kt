package ru.tbank.education.school.lesson7

import java.time.Duration
import java.time.LocalDate
import kotlin.math.pow

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */
fun currentBalance(deposit: Deposit) = deposit.initialDeposit + (
    1 + (
        deposit.compound_interaste_rate.let {//переменную поменять
            if (deposit.isVip == true) {//true надр
                it + 1
            } else {
                it
            }
        }
        ) / 365//неправильные дни
    ).pow(Duration.between(deposit.createAt, LocalDate.now()).toDays().toDouble())
