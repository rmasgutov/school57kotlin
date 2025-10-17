package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TransactionCategory
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import java.time.LocalDateTime
import kotlin.math.abs

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
        var income: Long = 0
        var expenses: Long = 0
        for (i in transactionRepo.getTransactions(client.id)) {
            if (i.date >= LocalDateTime.now().minusMonths(3)) {
                if (i.category == TransactionCategory.SALARY) {
                    income += i.amount
                } else {
                    expenses += i.amount
                }
            }
        }
        if (income == 0L) {
//            if (expenses > 0L) {
                return ScoringResult(ruleName, PaymentRisk.HIGH)
//            }
//            else {
//                return ScoringResult(ruleName, PaymentRisk.LOW)
//            }
        }

        val res = (income - expenses).toDouble()/income
        return ScoringResult(ruleName, when {
             res < 0 -> PaymentRisk.HIGH
            abs(res) <= 0.2 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        })
    }
}