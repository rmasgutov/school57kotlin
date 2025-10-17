package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.LoanRepository

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

        val now = LocalDate.now()
        val sixMonthsAgo = now.minusMonths(6)
        var recentActiveLoans = 0

        var i = 0
        while (i < loans.size) {
            val loan = loans[i]

            // Проверяем только активные кредиты
            if (!loan.isClosed) {
                // Проверяем дату открытия
                if (loan.startDate.isAfter(sixMonthsAgo)) {
                    recentActiveLoans = recentActiveLoans + 1
                }
            }

            i = i + 1
        }

        val risk = if (recentActiveLoans > 3) {
            PaymentRisk.HIGH
        } else if (recentActiveLoans >= 1) {
            PaymentRisk.MEDIUM
        } else {
            PaymentRisk.LOW
        }

        return ScoringResult(ruleName, risk)

    }
}