package ru.tbank.education.school.lesson6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Account
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.AccountType
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Region
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryAccountRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.AccountDiversityRule
import java.util.*

class AccountDiversityRuleTest {

    private val clientId = UUID.randomUUID()

    @Test
    fun `должен вернуть HIGH если диверсификация меньше или равна 2`() {
        val accounts = listOf(
            Account(UUID.randomUUID(), clientId, 100_000, AccountType.DEBIT, Currency.RUB)
        )

        val rule = AccountDiversityRule(
            accountRepo = InMemoryAccountRepository(mapOf(clientId to accounts))
        )

        val result = rule.evaluate(Client(clientId, "Иван Иванов", 35, Region.MOSCOW))
        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если диверсификация от 3 до 4`() {
        val accounts = listOf(
            Account(UUID.randomUUID(), clientId, 120_000, AccountType.DEBIT, Currency.RUB),
            Account(UUID.randomUUID(), clientId, 80_000, AccountType.SAVINGS, Currency.RUB),
            Account(UUID.randomUUID(), clientId, 50_000, AccountType.DEBIT, Currency.USD)
        )

        val rule = AccountDiversityRule(
            accountRepo = InMemoryAccountRepository(mapOf(clientId to accounts))
        )

        val result = rule.evaluate(Client(clientId, "Мария Петрова", 28, Region.SPB))
        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть LOW если диверсификация больше 4`() {
        val accounts = listOf(
            Account(UUID.randomUUID(), clientId, 200_000, AccountType.DEBIT, Currency.RUB),
            Account(UUID.randomUUID(), clientId, 150_000, AccountType.SAVINGS, Currency.USD),
            Account(UUID.randomUUID(), clientId, 90_000, AccountType.CREDIT, Currency.EUR),
            Account(UUID.randomUUID(), clientId, 50_000, AccountType.SAVINGS, Currency.RUB)
        )

        val rule = AccountDiversityRule(
            accountRepo = InMemoryAccountRepository(mapOf(clientId to accounts))
        )

        val result = rule.evaluate(Client(clientId, "Олег Смирнов", 40, Region.KAZAN))
        assertEquals(PaymentRisk.LOW, result.score)
    }
}