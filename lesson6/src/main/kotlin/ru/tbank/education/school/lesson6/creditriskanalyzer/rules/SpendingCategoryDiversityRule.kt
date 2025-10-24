package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TransactionCategory
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import java.time.LocalDateTime

/**
 * Проверяет разнообразие категорий трат клиента.
 *
 * Идея:
 * - Получить все транзакции клиента за последние 3 месяца.
 * - Посчитать, в скольких уникальных категориях клиент тратит деньги.
 *
 * Как считать score:
 * - Если меньше 3 категорий → HIGH (тратит только на одно и то же)
 * - Если от 3 до 6 категорий → MEDIUM
 * - Если больше 6 категорий → LOW (разнообразное поведение, меньше риск)
 *
 */
class SpendingCategoryDiversityRule(
    private val transactionRepo: TransactionRepository
) : ScoringRule {

    override val ruleName: String = "Spending Category Diversity"

    override fun evaluate(client: Client): ScoringResult {
        val allTransactions = transactionRepo.getTransactions(client.id)
        val threeMonthsAgo = LocalDateTime.now().minusMonths(3)

        val categories = mutableListOf<TransactionCategory>()

        for (transaction in allTransactions) {
            if (transaction.date >= threeMonthsAgo) {
                var found = false
                for (existingCategory in categories) {
                    if (existingCategory == transaction.category) {
                        found = true
                        break
                    }
                }
                if (!found) {
                    categories.add(transaction.category)
                }
            }
        }
        val categoryCount = categories.size

        val risk = if (categoryCount < 3) {
            PaymentRisk.HIGH
        } else if (categoryCount > 6) {
            PaymentRisk.LOW
        } else {
            PaymentRisk.MEDIUM
        }

        return ScoringResult(ruleName, risk)
    }
}