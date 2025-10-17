package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import java.time.LocalDateTime
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
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
    var totalIncome = 0.0
    var totalExpenses = 0.0
    override fun evaluate(client: Client): ScoringResult {
        val threeMonthsAgo = LocalDateTime.now().minusMonths(3)
        val allTransactions = transactionRepo.getTransactions(client.id)
        val categories = mutableListOf<String>()
        // Перебираем все транзакции
        for (transaction in allTransactions) {
            if (transaction.date.isAfter(threeMonthsAgo)) {
                if (transaction.category.toString() == "SALARY") {
                    totalIncome += transaction.amount
                } else {
                    totalExpenses += transaction.amount
                }
            }
        }
    val risk = when {
        totalIncome == 0.0 && totalExpenses > 0 -> PaymentRisk.HIGH
        totalExpenses > totalIncome -> PaymentRisk.HIGH
        totalExpenses >= totalIncome * 0.8 && totalExpenses <= totalIncome * 1.2 -> PaymentRisk.MEDIUM
        else -> PaymentRisk.LOW
    }

     return ScoringResult(
    ruleName,
    risk
    )
  }
}