package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TransactionCategory
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository

/**
 * Анализирует долю расходов клиента в рисковых категориях.
 *
 * Идея:
 * - Получить все транзакции клиента.
 * - Исключить доходы (SALARY).
 * - Посчитать общие сумму переводов в категориях GAMBLING, CRYPTO, TRANSFER.
 * - Посчитать общие сумму всех переводов (без SALARY).
 * - Рассчитать долю переводов в категориях GAMBLING, CRYPTO, TRANSFER.
 *
 * Как считать risk:
 * - Если доля > 0.6 → HIGH
 * - Если доля > 0.3 → MEDIUM
 * - Иначе → LOW
 */
class HighRiskCategorySpendingRule(
    private val transactionRepo: TransactionRepository
) : ScoringRule {

    override val ruleName: String = "High-Risk Category Spending"

    override fun evaluate(client: Client): ScoringResult {
        val transactions = transactionRepo.getTransactions(client.id)

        var totalSpending = 0.0
        var riskySpending = 0.0

        for (transaction in transactions) {
            if (transaction.category != TransactionCategory.SALARY) {
                totalSpending += transaction.amount

                if (transaction.category == TransactionCategory.GAMBLING ||
                    transaction.category == TransactionCategory.CRYPTO ||
                    transaction.category == TransactionCategory.TRANSFER
                ) {
                    riskySpending += transaction.amount
                }
            }
        }
        val riskyRatio = if (totalSpending > 0) riskySpending / totalSpending else 0.0
        val risk = when {
            riskyRatio > 0.6 -> PaymentRisk.HIGH
            riskyRatio > 0.3 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(ruleName, risk)
    }
}
