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
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.TransactionCountRule

class TransactionCountRuleTest {

    private val clientId = UUID.randomUUID()
    private val now = LocalDateTime.now()

    @Test
    fun `должен вернуть HIGH если транзакций меньше 100`() {
        val transactions = (1..50).map {
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                100,
                TransactionCategory.FOOD,
                now.minusDays(5),
                Currency.RUB
            )
        }

        val repo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        val rule = TransactionCountRule(repo)

        val result = rule.evaluate(Client(clientId, "Иван Иванов", 35, Region.MOSCOW))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если транзакций от 501 до 999`() {
        val transactions = (1..700).map {
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                200,
                TransactionCategory.SHOPPING,
                now.minusDays(10),
                Currency.RUB
            )
        }

        val repo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        val rule = TransactionCountRule(repo)

        val result = rule.evaluate(Client(clientId, "Мария Петрова", 28, Region.SPB))

        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть LOW если транзакций больше 1000`() {
        val transactions = (1..1500).map {
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                50,
                TransactionCategory.TRANSFER,
                now.minusDays(20),
                Currency.RUB
            )
        }

        val repo = InMemoryTransactionRepository(mapOf(clientId to transactions))
        val rule = TransactionCountRule(repo)

        val result = rule.evaluate(Client(clientId, "Олег Сидоров", 42, Region.KAZAN))

        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть HIGH если нет транзакций`() {
        val repo = InMemoryTransactionRepository(emptyMap())
        val rule = TransactionCountRule(repo)

        val result = rule.evaluate(Client(clientId, "Без Тратов", 31, Region.OTHER))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен учитывать только транзакции за последний месяц`() {
        val recent = (1..50).map {
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                100,
                TransactionCategory.FOOD,
                now.minusDays(10),
                Currency.RUB
            )
        }
        val old = (1..1000).map {
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                200,
                TransactionCategory.SHOPPING,
                now.minusMonths(3),
                Currency.RUB
            )
        }

        val repo = InMemoryTransactionRepository(mapOf(clientId to (recent + old)))
        val rule = TransactionCountRule(repo)

        val result = rule.evaluate(Client(clientId, "Иван Старый", 39, Region.MOSCOW))

        // Должен вернуть HIGH, потому что учитываются только последние (50) транзакций
        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `граничные значения - ровно 100 и ровно 1000`() {
        val t100 = (1..100).map {
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                100,
                TransactionCategory.RENT,
                now.minusDays(3),
                Currency.RUB
            )
        }
        val rule100 = TransactionCountRule(InMemoryTransactionRepository(mapOf(clientId to t100)))
        val result100 = rule100.evaluate(Client(clientId, "Сто Транзакций", 30, Region.SPB))
        assertEquals(PaymentRisk.HIGH, result100.score)

        val t1000 = (1..1000).map {
            Transaction(
                UUID.randomUUID(),
                clientId,
                UUID.randomUUID(),
                100,
                TransactionCategory.RENT,
                now.minusDays(3),
                Currency.RUB
            )
        }
        val rule1000 = TransactionCountRule(InMemoryTransactionRepository(mapOf(clientId to t1000)))
        val result1000 = rule1000.evaluate(Client(clientId, "Тысяча Транзакций", 30, Region.KAZAN))
        assertEquals(PaymentRisk.MEDIUM, result1000.score)
    }
}
