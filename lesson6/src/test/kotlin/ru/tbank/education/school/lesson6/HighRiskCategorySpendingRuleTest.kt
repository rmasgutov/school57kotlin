package ru.tbank.education.school.lesson6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Region
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Transaction
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TransactionCategory
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryTransactionRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.HighRiskCategorySpendingRule
import java.time.LocalDateTime
import java.util.*

class HighRiskCategorySpendingRuleTest {

    private val clientId = UUID.randomUUID()
    private val now = LocalDateTime.now()

    @Test
    fun `должен вернуть LOW если доля рисковых расходов меньше 30%`() {
        val transactions = listOf(
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                100_000,
                TransactionCategory.SHOPPING,
                now,
                Currency.RUB
            ),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 20_000, TransactionCategory.GAMBLING, now, Currency.RUB)
        )

        val rule = HighRiskCategorySpendingRule(
            transactionRepo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        )

        val result = rule.evaluate(Client(clientId, "Анна Иванова", 30, Region.SPB))
        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если доля рисковых расходов от 30 до 60%`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 50_000, TransactionCategory.GAMBLING, now, Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 50_000, TransactionCategory.SHOPPING, now, Currency.RUB)
        )

        val rule = HighRiskCategorySpendingRule(
            transactionRepo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        )

        val result = rule.evaluate(Client(clientId, "Олег Смирнов", 42, Region.MOSCOW))
        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть HIGH если доля рисковых расходов больше 60%`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 80_000, TransactionCategory.CRYPTO, now, Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 20_000, TransactionCategory.FOOD, now, Currency.RUB)
        )

        val rule = HighRiskCategorySpendingRule(
            transactionRepo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        )

        val result = rule.evaluate(Client(clientId, "Мария Петрова", 28, Region.KAZAN))
        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен вернуть LOW если расходов нет`() {
        val transactions = emptyList<Transaction>()

        val rule = HighRiskCategorySpendingRule(
            transactionRepo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        )

        val result = rule.evaluate(Client(clientId, "Павел Кузнецов", 33, Region.OTHER))
        assertEquals(PaymentRisk.LOW, result.score)
    }
}
