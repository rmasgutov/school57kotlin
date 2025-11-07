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
        val transaction = transactionRepo.getTransactions(client.id)
        var sumIllegal = 0L
        var sumOther = 0L
        for (i in transaction) {
            val category = i.category
            if (category == TransactionCategory.SALARY) { continue }
            if (
                category == TransactionCategory.TRANSFER ||
                category == TransactionCategory.GAMBLING ||
                category == TransactionCategory.CRYPTO
            ) {sumIllegal += i.amount}
            sumOther += i.amount
        }
        val risk = when {
            sumOther + sumIllegal == 0L -> PaymentRisk.LOW
            5L * sumIllegal > 3L * sumOther -> PaymentRisk.HIGH
            10L * sumIllegal > 3L * sumOther -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(
            ruleName,
            risk
        )
    }
}
