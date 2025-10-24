package ru.tbank.education.school.lesson6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Transaction
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TransactionCategory
import java.time.LocalDateTime
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Region
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryTransactionRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.IncomeExpenseRatioRule
import java.util.*

class IncomeExpenseRatioRuleTest {

    private val clientId = UUID.randomUUID()
    private val now = LocalDateTime.now()

    @Test
    fun `должен вернуть HIGH если расходы больше доходов`() {
        val transactions = listOf(
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                100_000,
                TransactionCategory.SALARY,
                now.minusDays(5),
                Currency.RUB
            ),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 150_000, TransactionCategory.RENT, now.minusDays(10), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 50_000, TransactionCategory.FOOD, now.minusDays(8), Currency.RUB)
        )

        val repo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        val rule = IncomeExpenseRatioRule(repo)

        val result = rule.evaluate(Client(clientId, "Иван Иванов", 35, Region.MOSCOW))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если расходы примерно равны доходам (в пределах 20 процентов)`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 120_000, TransactionCategory.SALARY, now.minusDays(5), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 100_000, TransactionCategory.RENT, now.minusDays(10), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 10_000, TransactionCategory.FOOD, now.minusDays(8), Currency.RUB)
        )

        val repo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        val rule = IncomeExpenseRatioRule(repo)

        val result = rule.evaluate(Client(clientId, "Мария Петрова", 28, Region.SPB))

        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть LOW если доходы значительно больше расходов`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 300_000, TransactionCategory.SALARY, now.minusDays(5), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 50_000, TransactionCategory.FOOD, now.minusDays(8), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 30_000, TransactionCategory.SHOPPING, now.minusDays(15), Currency.RUB)
        )

        val repo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        val rule = IncomeExpenseRatioRule(repo)

        val result = rule.evaluate(Client(clientId, "Олег Сидоров", 42, Region.KAZAN))

        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть HIGH если нет доходов (категория SALARY отсутствует)`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 80_000, TransactionCategory.RENT, now.minusDays(10), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 40_000, TransactionCategory.FOOD, now.minusDays(20), Currency.RUB)
        )

        val repo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        val rule = IncomeExpenseRatioRule(repo)

        val result = rule.evaluate(Client(clientId, "Без Зарплаты", 30, Region.OTHER))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен вернуть HIGH если все транзакции старше 3 месяцев`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 200_000, TransactionCategory.SALARY, now.minusMonths(4), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 150_000, TransactionCategory.FOOD, now.minusMonths(5), Currency.RUB)
        )

        val repo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        val rule = IncomeExpenseRatioRule(repo)

        val result = rule.evaluate(Client(clientId, "Иван Старый", 39, Region.KAZAN))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `граничный случай - расходы ровно на 20 процентов меньше доходов = MEDIUM`() {
        val transactions = listOf(
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 120_000, TransactionCategory.SALARY, now.minusDays(5), Currency.RUB),
            Transaction(UUID.randomUUID(), clientId, UUID.randomUUID(), 96_000, TransactionCategory.RENT, now.minusDays(10), Currency.RUB)
        )

        val repo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        val rule = IncomeExpenseRatioRule(repo)

        val result = rule.evaluate(Client(clientId, "Григорий Граничный", 36, Region.SPB))

        assertEquals(PaymentRisk.MEDIUM, result.score)
    }
}
