package ru.tbank.education.school.lesson6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Transaction
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TransactionCategory
import java.time.LocalDateTime
import java.util.*
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Region
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryTransactionRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.SpendingCategoryDiversityRule

class SpendingCategoryDiversityRuleTest {

    private val clientId = UUID.randomUUID()
    private val now = LocalDateTime.now()

    @Test
    fun `должен вернуть HIGH если менее 3 категорий трат`() {
        val transactions = listOf(
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                500,
                TransactionCategory.FOOD,
                now.minusDays(10),
                Currency.RUB
            ),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 700, TransactionCategory.FOOD, now.minusDays(20), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 300, TransactionCategory.RENT, now.minusDays(15), Currency.RUB)
        )

        val rule = SpendingCategoryDiversityRule(
            transactionRepo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        )

        val result = rule.evaluate(Client(clientId, "Анна Смирнова", 27, Region.SPB))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если от 3 до 6 категорий трат`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 300, TransactionCategory.FOOD, now.minusDays(15), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 700, TransactionCategory.RENT, now.minusDays(25), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 100, TransactionCategory.SHOPPING, now.minusDays(5), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 200, TransactionCategory.TRANSFER, now.minusDays(3), Currency.RUB)
        )

        val rule = SpendingCategoryDiversityRule(
            transactionRepo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        )

        val result = rule.evaluate(Client(clientId, "Пётр Васильев", 33, Region.KAZAN))

        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть LOW если более 6 категорий трат`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 200, TransactionCategory.FOOD, now.minusDays(5), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 150, TransactionCategory.RENT, now.minusDays(15), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 300, TransactionCategory.SHOPPING, now.minusDays(10), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 400, TransactionCategory.CRYPTO, now.minusDays(20), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 250, TransactionCategory.GAMBLING, now.minusDays(25), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 150, TransactionCategory.TRANSFER, now.minusDays(2), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 500, TransactionCategory.OTHER, now.minusDays(1), Currency.RUB)
        )

        val rule = SpendingCategoryDiversityRule(
            transactionRepo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        )

        val result = rule.evaluate(Client(clientId, "Ольга Сидорова", 41, Region.MOSCOW))

        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть HIGH если нет транзакций`() {
        val rule = SpendingCategoryDiversityRule(
            transactionRepo = InMemoryTransactionRepository(emptyMap())
        )

        val result = rule.evaluate(Client(clientId, "Без Тратов", 30, Region.OTHER))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `игнорирует транзакции старше 3 месяцев`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 500, TransactionCategory.FOOD, now.minusMonths(4), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 300, TransactionCategory.RENT, now.minusMonths(5), Currency.RUB)
        )

        val rule = SpendingCategoryDiversityRule(
            transactionRepo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        )

        val result = rule.evaluate(Client(clientId, "Иван Старый", 39, Region.SPB))

        assertEquals(PaymentRisk.HIGH, result.score) // нет трат за последние 3 месяца
    }
}
