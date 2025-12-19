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
        var some: Long = 0
        val interesting = arrayOf(TransactionCategory.GAMBLING, TransactionCategory.CRYPTO, TransactionCategory.TRANSFER)
        var all: Long = 0
        for (i in transactionRepo.getTransactions(client.id)) {
            if (i.category != TransactionCategory.SALARY) {
                all += i.amount
                if (i.category in interesting) {
                    some += i.amount
                }
            }
        }
        if (all == 0L) {
            return ScoringResult(ruleName, PaymentRisk.LOW)
        }
        val res = some.toDouble() / all
        return ScoringResult(ruleName, when {
            res > 0.6 -> PaymentRisk.HIGH
            res > 0.3 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        })
    }
}
