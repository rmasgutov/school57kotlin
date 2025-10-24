package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import java.time.LocalDateTime

/**
 * Анализирует частоту крупных транзакций клиента за последние 3 месяца.
 *
 * Идея:
 * - Получить все транзакции за последние 3 месяца.
 * - Отобрать те, где сумма превышает 100 000 ₽.
 * - Подсчитать количество таких транзакций.
 *
 * Как считать score:
 * - Если таких транзакций больше 20 → HIGH
 * - Если от 5 до 20 → MEDIUM
 * - Если меньше 5 → LOW
 */
class LargeTransactionFrequencyRule(
    private val transactionRepo: TransactionRepository
) : ScoringRule {

    override val ruleName: String = "Large Transaction Frequency"

    override fun evaluate(client: Client): ScoringResult {
        val threeMonthsAgo = LocalDateTime.now().minusMonths(3)

        val largeTransactionsCount =
            transactionRepo.getTransactions(client.id).count { it.date.isAfter(threeMonthsAgo) && it.amount > 100_000 }

        val risk = when {
            largeTransactionsCount > 20 -> PaymentRisk.HIGH
            largeTransactionsCount >= 5 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }

        return ScoringResult(
            ruleName,
            risk
        )
    }
}