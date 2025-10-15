package ru.tbank.education.school.lesson6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Loan
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Region
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryLoanRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.RecentLoanActivityRule
import java.time.LocalDateTime
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency
import java.util.*

class RecentLoanActivityRuleTest {

    private val clientId = UUID.randomUUID()
    private val now = LocalDateTime.now()

    @Test
    fun `должен вернуть LOW если нет новых кредитов`() {
        val loans = listOf(
            Loan(UUID.randomUUID(), clientId, 100_000, false, now.minusYears(1), null, Currency.RUB)
        )

        val rule = RecentLoanActivityRule(
            loanRepo = InMemoryLoanRepository(mapOf(clientId to loans))
        )

        val result = rule.evaluate(Client(clientId, "Андрей Иванов", 34, Region.MOSCOW))

        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если есть 1-3 новых кредита`() {
        val loans = (1..3).map {
            Loan(UUID.randomUUID(), clientId, 150_000, false, now.minusMonths(3), null, Currency.RUB)
        }

        val rule = RecentLoanActivityRule(
            loanRepo = InMemoryLoanRepository(mapOf(clientId to loans))
        )

        val result = rule.evaluate(Client(clientId, "Мария Петрова", 29, Region.SPB))

        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть HIGH если более 3 кредитов за последние 6 месяцев`() {
        val loans = (1..5).map {
            Loan(UUID.randomUUID(), clientId, 200_000, false, now.minusMonths(2), null, Currency.RUB)
        }

        val rule = RecentLoanActivityRule(
            loanRepo = InMemoryLoanRepository(mapOf(clientId to loans))
        )

        val result = rule.evaluate(Client(clientId, "Олег Смирнов", 40, Region.KAZAN))

        assertEquals(PaymentRisk.HIGH, result.score)
    }
}
