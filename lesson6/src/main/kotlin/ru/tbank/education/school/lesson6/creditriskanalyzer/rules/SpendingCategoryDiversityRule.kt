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
        val transaction = transactionRepo.getTransactions(client.id)
        var month3now = LocalDateTime.now().minusMonths(3)
        val setCategory : MutableSet<TransactionCategory> = mutableSetOf()
        for (i in transaction) {
            if (i.date >= month3now) {
                setCategory.add(i.category)
            }
        }
        val res = setCategory.size
        val risk = when {
            res < 3 -> PaymentRisk.HIGH
            res <= 6 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult (
            ruleName,
            risk
        )

    }
}