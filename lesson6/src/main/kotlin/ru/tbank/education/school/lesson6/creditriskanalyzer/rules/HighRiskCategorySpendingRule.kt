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
        var summTransactionRiskCategory = 0L
        var summTransactionSafeCategory = 0L
        for(it in transactionRepo.getTransactions(client.id)){
            if(it.category == TransactionCategory.GAMBLING || it.category == TransactionCategory.CRYPTO || it.category == TransactionCategory.TRANSFER){
                summTransactionRiskCategory += it.amount
            }
            else{
                summTransactionSafeCategory += it.amount
            }
        }
        val PartOfRiskTransaction = summTransactionRiskCategory / (summTransactionSafeCategory + summTransactionRiskCategory)
        val risk = when{
            PartOfRiskTransaction > 0.6 -> PaymentRisk.HIGH
            PartOfRiskTransaction > 0.3 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(
            ruleName,
            risk
        )
    }
}
