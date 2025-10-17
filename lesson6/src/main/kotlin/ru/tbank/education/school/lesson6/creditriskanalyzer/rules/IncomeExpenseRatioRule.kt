package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository

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
 * - Если расходы примерно равны доходам (±20%) → MEDIUM
 * - Если доходы значительно больше расходов → LOW
 *
 */
class IncomeExpenseRatioRule(
    private val transactionRepo: TransactionRepository
) : ScoringRule {

    override val ruleName: String = "Loan Count"

    override fun evaluate(client: Client): ScoringResult {
        val transactions = transactionRepo.getTransactions(client.id)

        val now = LocalDate.now()
        val threeMonthsAgo = now.minusMonths(3)

        var incomeTotal = 0.0
        var expenseTotal = 0.0

        var i = 0
        while (i < transactions.size) {
            val transaction = transactions[i]

            // Проверяем, попадает ли транзакция в последние 3 месяца
            val date = transaction.date
            val isRecent = !date.isBefore(threeMonthsAgo) && !date.isAfter(now)

            if (isRecent) {
                if (transaction.category == "SALARY") {
                    incomeTotal = incomeTotal + transaction.amount
                } else {
                    expenseTotal = expenseTotal + transaction.amount
                }
            }

            i = i + 1
        }

        val risk: PaymentRisk

        if (expenseTotal > incomeTotal) {
            risk = PaymentRisk.HIGH
        } else {
            val ratio = if (incomeTotal > 0) expenseTotal / incomeTotal else 1.0

            risk = if (ratio >= 0.8) {
                PaymentRisk.MEDIUM
            } else {
                PaymentRisk.LOW
            }
        }

        return ScoringResult(ruleName, risk)
    }
}