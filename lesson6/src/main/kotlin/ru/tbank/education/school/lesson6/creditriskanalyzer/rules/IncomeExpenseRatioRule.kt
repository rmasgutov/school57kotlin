package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TransactionCategory
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import java.time.LocalDateTime

/**
 * Анализирует соотношение доходов и расходов клиента за последние 3 месяца.
 *
 * Идея:
 * - Получить все транзакции клиента за последние 3 месяца.
 * - Разделить их на доходы (категория SALARY) и расходы (все остальные).
 * - Посчитать общую сумму доходов и расходов.
 * - Определить финансовое равновесие клиента.
 *
 * Как считать score:
 * - Если расходы > доходов → HIGH (клиент тратит больше, чем зарабатывает)
 * - Если расходы примерно равны доходам (±20% включительно) → MEDIUM
 * - Если доходы значительно больше расходов → LOW
 *
 */
class IncomeExpenseRatioRule(
    private val transactionRepo: TransactionRepository
) : ScoringRule {

    override val ruleName: String = "Loan Count"

    override fun evaluate(client: Client): ScoringResult {
        val transaction = transactionRepo.getTransactions(client.id)
        var sumSALARY = 0L
        var sumCONSUMPTION = 0L
        val time3Mount = LocalDateTime.now().minusMonths(3)
        for (i in transaction) {
            if (i.date < time3Mount) {continue}
            if (i.category == TransactionCategory.SALARY) {sumSALARY += i.amount}
            else {sumCONSUMPTION += i.amount}
        }
        val risk = when {
            sumSALARY + sumCONSUMPTION == 0L -> PaymentRisk.HIGH
            5L * sumCONSUMPTION <= 6L * sumSALARY && 4L * sumSALARY <= 5L * sumCONSUMPTION -> PaymentRisk.MEDIUM
            sumCONSUMPTION > sumSALARY -> PaymentRisk.HIGH
            else -> PaymentRisk.LOW
        }
        return ScoringResult(
            ruleName,
            risk
        )
    }
}