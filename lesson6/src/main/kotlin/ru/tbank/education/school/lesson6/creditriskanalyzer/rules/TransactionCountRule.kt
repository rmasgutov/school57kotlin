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
        var transactions = 0
        for (i in transactionRepository.getTransactions(client.id)) {
            if (i.date >= LocalDateTime.now().minusMonths(1)) {
                transactions++
            }
            if (transactions > 1000) {
                return ScoringResult(ruleName, PaymentRisk.LOW)
            }
        }
        return ScoringResult(ruleName, if (transactions < 500) PaymentRisk.HIGH else PaymentRisk.MEDIUM)
    }
}