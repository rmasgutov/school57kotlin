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
        var loans = 0
        for (i in loanRepo.getLoans(client.id)) {
            if (i.startDate >= LocalDateTime.now().minusMonths(6)) {
                loans++
            }
        }
        return ScoringResult(ruleName, when {
            loans > 3 -> PaymentRisk.HIGH
            loans > 0 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        })

    }
}