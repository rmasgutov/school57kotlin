package ru.tbank.education.school.lesson6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency.*
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Loan
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Overdue
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Region
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryLoanRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryOverdueRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.LoanCountRule
import java.time.LocalDateTime
import java.util.*

class LoanCountRuleTest {

    private val clientId = UUID.randomUUID()
    private val now = LocalDateTime.now()

    @Test
    fun `должен вернуть LOW если нет кредитов с просрочкой более 30 дней`() {
        val loans = listOf(
            Loan(UUID.randomUUID(), clientId, 10_000, false, now.minusMonths(3), null, RUB),
        )

        val overdues = setOf(
            Overdue(loans[0].id, clientId, daysOverdue = 10)
        )

        val rule = LoanCountRule(
            loanRepository = InMemoryLoanRepository(mapOf(clientId to loans)),
            overdueRepository = InMemoryOverdueRepository(mapOf(clientId to overdues))
        )

        val result = rule.evaluate(Client(clientId, "Иван Иванов", 30, Region.MOSCOW))

        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если один кредит с просрочкой больше 30 дней`() {
        val loan = Loan(UUID.randomUUID(), clientId, 10_000, false, now.minusMonths(5), null, RUB)
        val overdues = setOf(
            Overdue(loan.id, clientId, daysOverdue = 45)
        )

        val rule = LoanCountRule(
            loanRepository = InMemoryLoanRepository(mapOf(clientId to listOf(loan))),
            overdueRepository = InMemoryOverdueRepository(mapOf(clientId to overdues))
        )

        val result = rule.evaluate(Client(clientId, "Мария Петрова", 25, Region.SPB))

        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть HIGH если более трёх кредитов с просрочкой больше 30 дней`() {
        val loans = (1..4).map {
            Loan(UUID.randomUUID(), clientId, 50_000, false, now.minusMonths(10), null, RUB)
        }

        val overdues = loans.map {
            Overdue(it.id, clientId, daysOverdue = 60)
        }.toSet()

        val rule = LoanCountRule(
            loanRepository = InMemoryLoanRepository(mapOf(clientId to loans)),
            overdueRepository = InMemoryOverdueRepository(mapOf(clientId to overdues))
        )

        val result = rule.evaluate(Client(clientId, "Олег Сидоров", 40, Region.KAZAN))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен вернуть LOW если у клиента нет кредитов`() {
        val rule = LoanCountRule(
            loanRepository = InMemoryLoanRepository(emptyMap()),
            overdueRepository = InMemoryOverdueRepository(emptyMap())
        )

        val result = rule.evaluate(Client(clientId, "Алексей Смирнов", 32, Region.OTHER))

        assertEquals(PaymentRisk.LOW, result.score)
    }
}
