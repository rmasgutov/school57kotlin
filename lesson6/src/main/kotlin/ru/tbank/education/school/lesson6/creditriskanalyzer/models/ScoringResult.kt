package ru.tbank.education.school.lesson6.creditriskanalyzer.models

data class ScoringResult(
    val ruleName: String,
    val score: PaymentRisk
)

enum class PaymentRisk(val value: Int) {
    /** Клиент надёжен, признаков риска нет */
    LOW(1),

    /** Нейтральный клиент: нет явных рисков, но и сильных позитивных факторов */
    MEDIUM(0),

    /** Клиент рискованный, высокий шанс неплатежеспособности */
    HIGH(-1);

}