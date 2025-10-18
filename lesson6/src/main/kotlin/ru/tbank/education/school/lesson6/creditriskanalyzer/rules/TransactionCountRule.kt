package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository

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
        val transactions = transactionRepo.getTransactions(client.id)

        val now = LocalDate.now()
        val oneMonthAgo = now.minusMonths(1)
        var recentTransactionCount = 0

        var i = 0
        while (i < transactions.size) {
            val transaction = transactions[i]

            val date = transaction.date
            val isRecent = !date.isBefore(oneMonthAgo) && !date.isAfter(now)

            if (isRecent) {
                recentTransactionCount = recentTransactionCount + 1
            }

            i = i + 1
        }

        val risk = if (recentTransactionCount < 500) {
            PaymentRisk.HIGH
        } else if (recentTransactionCount < 1000) {
            PaymentRisk.MEDIUM
        } else {
            PaymentRisk.LOW
        }

        return ScoringResult(ruleName, risk)

    }
}