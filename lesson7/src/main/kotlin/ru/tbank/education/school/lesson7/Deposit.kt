package ru.tbank.education.school.lesson7

import java.time.LocalDate

/**
 * Моделька данных вклада. У одного пользователя может быть много вклад.
 * Доступны только рублевые вклад.
 */
data class Deposit(
    /**
     * Уникальный идентификатор вклада. Указан в договоре, поэтому не может меняться.
     */
    val id: Long,
    /**
     Начальный депозит в рублях. Последующее пополнение или частичное изъятие невозможно.
     */
    val initialDeposit: Int,
    /**
     * Дата открытия вклада.
     */
    var createAt: LocalDate,
    /**
     * Длительность вклада в месяцах.
     */
    val durationMonth: Int,

    val `complexPercent`: Double,

    val isVip: Int,
)
