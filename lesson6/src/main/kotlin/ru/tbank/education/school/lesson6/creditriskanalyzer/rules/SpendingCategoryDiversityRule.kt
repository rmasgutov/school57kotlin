package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TransactionRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
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
        val threeMonthsAgo = LocalDateTime.now().minusMonths(3)
        val allTransactions = transactionRepo.getTransactions(client.id)

        // Список для хранения уникальных категорий
        val categories = mutableListOf<String>()

        // Перебираем все транзакции
        for (transaction in allTransactions) {
            if (transaction.date.isAfter(threeMonthsAgo)) {
                var flag = false
                val currentCategory = transaction.category.toString()

                // Проверяем, есть ли уже такая категория в списке
                for (existingCategory in categories) {
                    if (existingCategory == currentCategory) {
                        flag = true
                        break
                    }
                }

                // Если категории нет в списке, добавляем её
                if (!flag) {
                    categories.add(currentCategory)
                }
            }
        }

        // Количество уникальных категорий
        val categoryCount = categories.size

        val risk = when {
            categoryCount < 3 -> PaymentRisk.HIGH
            categoryCount <= 6 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }

        return ScoringResult(
            ruleName,
            risk
        )
    }


}
