package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository

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
        val transactions = transactionRepo.getTransactions(client.id)

        val now = LocalDate.now()
        val threeMonthsAgo = now.minusMonths(3)
        val uniqueCategories = mutableSetOf<String>()

        var i = 0
        while (i < transactions.size) {
            val transaction = transactions[i]

            // Учитываем только расходы за последние 3 месяца
            val date = transaction.date
            val isRecent = !date.isBefore(threeMonthsAgo) && !date.isAfter(now)

            if (isRecent && transaction.category != "SALARY") {
                uniqueCategories.add(transaction.category)
            }

            i = i + 1
        }

        val categoryCount = uniqueCategories.size

        val risk = if (categoryCount < 3) {
            PaymentRisk.HIGH
        } else if (categoryCount <= 6) {
            PaymentRisk.MEDIUM
        } else {
            PaymentRisk.LOW
        }

        return ScoringResult(ruleName, risk)

    }
}