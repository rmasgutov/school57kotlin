package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.LoanRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
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
        var counter = 0.0
            val allLoans = loanRepo.getLoans(client.id)
            val sixMonthsAgo = LocalDateTime.now().minusMonths(6)
            for (loan in allLoans) {
                if (loan.isClosed == false&&loan.startDate.isAfter(sixMonthsAgo)) {
                    counter=counter+1
                }
                }
        val risk = when {
            counter >3 -> PaymentRisk.HIGH
            counter >1 && counter <= 3-> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }

        return ScoringResult(
            ruleName,
            risk
        )
    }
}