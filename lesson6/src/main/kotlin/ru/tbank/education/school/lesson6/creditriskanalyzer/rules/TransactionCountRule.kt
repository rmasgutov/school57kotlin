package ru.tbank.education.school.lesson6.creditriskanalyzer.rules
import java.time.LocalDateTime
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
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
        val transactionList = transactionRepository.getTransactions(client.id)
        val timeMonth = LocalDateTime.now().minusMonths(1)
        var cntMonthActivity = 0L
        for (transaction in transactionList) {
            if (transaction.date >= timeMonth) {
                cntMonthActivity += 1L
            }
        }
        val score = when {
            cntMonthActivity < 500 -> PaymentRisk.HIGH
            cntMonthActivity > 1000 -> PaymentRisk.LOW
            else -> PaymentRisk.MEDIUM
        }
        return ScoringResult(ruleName, score)


    }
}