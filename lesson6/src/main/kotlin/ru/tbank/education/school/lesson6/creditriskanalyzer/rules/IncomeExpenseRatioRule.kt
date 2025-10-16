package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TransactionCategory
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
        val transactions = transactionRepo.getTransactions(client.id)
        val time3Mount = LocalDateTime.now().minusMonths(3)
        var salary = 0L
        var others = 0L
        for (i in transactions) {
            if (i.date < time3Mount) {
                continue
            }
            if (i.category == TransactionCategory.SALARY) {
                salary += i.amount
            }
            else {
                others += i.amount
            }
        }
        val risk = when {
            salary + others == 0L -> PaymentRisk.HIGH
            5L * others <= 6L * salary && 4L * salary <= 5L * others -> PaymentRisk.MEDIUM
            others > salary -> PaymentRisk.HIGH
            else -> PaymentRisk.LOW
        }
        return ScoringResult (
            ruleName,
            risk
        )
    }
}