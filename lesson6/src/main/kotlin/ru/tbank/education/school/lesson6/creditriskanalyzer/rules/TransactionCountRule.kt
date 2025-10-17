package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import java.time.LocalDateTime

/**
 * Необходимо определить активность клиента.
 * Для этого нужно посчитать количество транзакций выполненных клиентом за последний месяц
 *
 * Как считать score:
 * - Если таких транзакций меньше 500 → HIGH
 * - Если таких транзакций больше или равно 500, но меньше или равно 1000 → MEDIUM
 * - Если таких транзакций больше 1000 → LOW
 */
class TransactionCountRule(
    private val transactionRepository: TransactionRepository,
) : ScoringRule {

    override val ruleName: String = "Transaction Count"

    override fun evaluate(client: Client): ScoringResult {
        val oneMonthAgo = LocalDateTime.now().minusMonths(1)
        val transactions = transactionRepository.getTransactions(client.id)

        var recentTransactionCount = 0

        for (transaction in transactions) {
            if (transaction.date.isBefore(oneMonthAgo)) recentTransactionCount++
        }

        val risk = when {
            recentTransactionCount < 500 -> PaymentRisk.HIGH
            recentTransactionCount >= 500 && recentTransactionCount <= 1000 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(ruleName, risk)
    }
}