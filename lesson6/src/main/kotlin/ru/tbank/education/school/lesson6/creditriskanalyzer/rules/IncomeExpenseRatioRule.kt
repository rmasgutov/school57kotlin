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
        val threeMonthsAgo = LocalDateTime.now().minusMonths(3)
        val transactions = transactionRepo.getTransactions(client.id)

        var totalIncome = 0.0
        var totalExpenses = 0.0

        for (transaction in transactions) {
            if (transaction.date.isAfter(threeMonthsAgo)) {
                if (transaction.category == TransactionCategory.SALARY) {
                    totalIncome += transaction.amount
                } else {
                    if (transaction.amount < 0) totalExpenses -= transaction.amount
                    else totalExpenses += transaction.amount
                }
            }
        }
        val ratio = if (totalIncome > 0) totalExpenses / totalIncome else 1.0

        val risk = when {
            totalExpenses > totalIncome -> PaymentRisk.HIGH
            ratio >= 0.8 && ratio <= 1.2 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(ruleName, risk)
    }
}