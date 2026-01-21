package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.LoanRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.OverdueRepository
import java.time.LocalDateTime

/**
 * Необходимо посчитать количество открытых кредитов с просрочкой больше 30 дней
 *
 * Как считать score:
 * - Если таких кредитов больше 3 → HIGH (слишком свежие)
 * - Если таких кредитов от одного до 3 включительно → MEDIUM
 * - Если таких кредитов нет → LOW
 */
class LoanCountRule(
    private val loanRepository: LoanRepository,
    private val overdueRepository: OverdueRepository
) : ScoringRule {


    override val ruleName: String = "Loan Count"

    override fun evaluate(client: Client): ScoringResult {
        val overdue = overdueRepository.getOverdues(client.id)
        var summi = 0
        for (i in overdue) {
            if (i.daysOverdue > 30) {
                summi += 1
            }
        }
        val risk = when {
            summi > 3 -> PaymentRisk.HIGH
            summi >= 1 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult (
            ruleName,
            risk
        )
    }
}