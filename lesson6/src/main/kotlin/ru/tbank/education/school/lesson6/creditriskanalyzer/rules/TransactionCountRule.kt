package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
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
    var counter=0
    override fun evaluate(client: Client): ScoringResult {
        val oneMonthsAgo = LocalDateTime.now().minusMonths(1)
        val allTransactions = transactionRepository.getTransactions(client.id)
        for (transaction in allTransactions) {
            if (transaction.date.isAfter(oneMonthsAgo)) {
                counter=counter+1
            }
        }
        val risk = when {
            counter < 500 -> PaymentRisk.HIGH
            counter >=500 && counter <= 1000-> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }

        return ScoringResult(
            ruleName,
            risk
        )
    }
}