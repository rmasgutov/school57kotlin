package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.LoanRepository
import java.time.LocalDateTime

/**
 * Проверяет, сколько кредитов клиент открыл за последние 6 месяцев.
 *
 * Идея:
 * - Получить все кредиты клиента.
 * - Отфильтровать только активные (isClosed == false).
 * - Проверить дату открытия (startDate после now.minusMonths(6)).
 *
 * Как считать risk:
 * - Если активных кредитов за 6 месяцев > 3 → HIGH
 * - Если от 1 до 3 → MEDIUM
 * - Если нет → LOW
 */
class RecentLoanActivityRule(
    private val loanRepo: LoanRepository
) : ScoringRule {

    override val ruleName: String = "Recent Loan Activity"

    override fun evaluate(client: Client): ScoringResult {
        val loans = loanRepo.getLoans(client.id)
        val timeMonth = LocalDateTime.now().minusMonths(6)
        var cntLoan = 0
        for (loan in loans) if ((loan.startDate >= timeMonth) && !loan.isClosed) {
            cntLoan++
        }
        val risk = when {
            cntLoan == 0 -> PaymentRisk.LOW
            cntLoan > 3 -> PaymentRisk.HIGH
            else -> PaymentRisk.MEDIUM
        }
        return ScoringResult(
            ruleName,
            risk
        )

    }
}