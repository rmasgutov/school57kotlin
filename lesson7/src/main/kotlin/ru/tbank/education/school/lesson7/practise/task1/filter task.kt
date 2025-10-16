package ru.tbank.education.school.lesson7.practise.task1// 4) FILTER — выявление «подозрительных» транзакций
/**
 * Задание: Найди потенциально мошеннические операции.
 *
 * Дано: список Transaction(amount, category, timestamp, country).
 * Нужно: отфильтровать транзакции, которые удовлетворяют ЛЮБОМУ из условий:
 *  - сумма > threshold
 *  - категория ∈ {CRYPTO, GAMBLING}
 *  - страна не равна "RU", при этом сумма > foreignThreshold
 *
 * Верни список подозрительных операций.
 */
enum class TxCategory { FOOD, SHOPPING, CRYPTO, GAMBLING, TRANSFER, OTHER }
data class Tx(val amount: Double, val category: TxCategory, val timestamp: Long, val country: String)

fun suspiciousTransactions(
    txs: List<Tx>,
    threshold: Double,
    foreignThreshold: Double
): List<Tx> {
    TODO("filter с несколькими условиями")
}