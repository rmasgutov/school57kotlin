package ru.tbank.education.school.lesson8.homework.payments

/**
 * Результат операции оплаты от "платёжного шлюза".
 *
 * @property success успешность операции
 * @property message сообщение об ошибке (если есть)
 */
data class GatewayResult(
    val success: Boolean,
    val message: String?
)