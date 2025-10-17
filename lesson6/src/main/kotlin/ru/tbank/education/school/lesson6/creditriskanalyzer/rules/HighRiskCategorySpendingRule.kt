package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TransactionCategory
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
        if (transactions.isEmpty()) {

        }

        var totalSpending: Long = 0
        var highRiskSpending: Long = 0

        // Проходим по всем транзакциям и подсчитываем расходы
        for (transaction in transactions) {

            if (transaction.category != TransactionCategory.SALARY) {
                // Суммируем общие расходы
                val amount = if (transaction.amount < 0) -transaction.amount else transaction.amount
                totalSpending += amount

                if (transaction.category == TransactionCategory.GAMBLING ||
                    transaction.category == TransactionCategory.CRYPTO ||
                    transaction.category == TransactionCategory.TRANSFER) {
                    highRiskSpending += amount
                }
            }
        }
        var flag=0
        if (totalSpending == 0L) {
            flag=1
        }

        val highRiskRatio = highRiskSpending.toDouble() / totalSpending

        val risk = when {
            highRiskRatio > 0.6 -> PaymentRisk.HIGH
            highRiskRatio > 0.3 || flag==1 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }

        return ScoringResult(
            ruleName,
            risk
        )
    }
}
