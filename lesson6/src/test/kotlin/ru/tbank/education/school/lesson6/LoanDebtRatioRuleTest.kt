package ru.tbank.education.school.lesson6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Account
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.AccountType
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency.*
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Loan
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Region
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryAccountRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryLoanRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.LoanDebtRatioRule
import java.time.LocalDateTime
import java.util.*

class LoanDebtRatioRuleTest {

    private val clientId = UUID.randomUUID()
    private val now = LocalDateTime.now()

    @Test
    fun `должен вернуть LOW если долг меньше или равен балансу`() {
        val loans = listOf(
            Loan(UUID.randomUUID(), clientId, 50_000, false, now.minusMonths(5), null, RUB),
            Loan(UUID.randomUUID(), clientId, 20_000, false, now.minusMonths(3), null, RUB)
        )

        val accounts = listOf(
            Account(UUID.randomUUID(), clientId, 100_000, AccountType.DEBIT, RUB
            )
        )

        val rule = LoanDebtRatioRule(
            loanRepo = InMemoryLoanRepository(mapOf(clientId to loans)),
            accountRepo = InMemoryAccountRepository(mapOf(clientId to accounts))
        )

        val result = rule.evaluate(Client(clientId, "Иван Иванов", 35, Region.MOSCOW))

        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если долг больше баланса но меньше чем в 3 раза`() {
        val loans = listOf(
            Loan(UUID.randomUUID(), clientId, 200_000, false, now.minusMonths(8), null, RUB)
        )

        val accounts = listOf(
            Account(UUID.randomUUID(), clientId, 100_000, AccountType.SAVINGS, RUB)
        )

        val rule = LoanDebtRatioRule(
            loanRepo = InMemoryLoanRepository(mapOf(clientId to loans)),
            accountRepo = InMemoryAccountRepository(mapOf(clientId to accounts))
        )

        val result = rule.evaluate(Client(clientId, "Мария Петрова", 29, Region.SPB))

        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть HIGH если долг больше чем в 3 раза превышает баланс`() {
        val loans = listOf(
            Loan(UUID.randomUUID(), clientId, 400_000, false, now.minusMonths(6), null, RUB),
            Loan(UUID.randomUUID(), clientId, 250_000, false, now.minusMonths(4), null, RUB)
        )

        val accounts = listOf(
            Account(UUID.randomUUID(), clientId, 100_000, AccountType.CREDIT, RUB)
        )

        val rule = LoanDebtRatioRule(
            loanRepo = InMemoryLoanRepository(mapOf(clientId to loans)),
            accountRepo = InMemoryAccountRepository(mapOf(clientId to accounts))
        )

        val result = rule.evaluate(Client(clientId, "Олег Сидоров", 40, Region.KAZAN))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен вернуть LOW если нет кредитов`() {
        val accounts = listOf(
            Account(UUID.randomUUID(), clientId, 50_000, AccountType.DEBIT, RUB)
        )

        val rule = LoanDebtRatioRule(
            loanRepo = InMemoryLoanRepository(emptyMap()),
            accountRepo = InMemoryAccountRepository(mapOf(clientId to accounts))
        )

        val result = rule.evaluate(Client(clientId, "Алексей Смирнов", 28, Region.OTHER))

        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть HIGH если баланс нулевой и есть долги`() {
        val loans = listOf(
            Loan(UUID.randomUUID(), clientId, 150_000, false, now.minusMonths(5), null, RUB)
        )

        val accounts = listOf(
            Account(UUID.randomUUID(), clientId, 0, AccountType.DEBIT, RUB)
        )

        val rule = LoanDebtRatioRule(
            loanRepo = InMemoryLoanRepository(mapOf(clientId to loans)),
            accountRepo = InMemoryAccountRepository(mapOf(clientId to accounts))
        )

        val result = rule.evaluate(Client(clientId, "Иван Петров", 33, Region.KAZAN))

        assertEquals(PaymentRisk.HIGH, result.score)
    }
}
