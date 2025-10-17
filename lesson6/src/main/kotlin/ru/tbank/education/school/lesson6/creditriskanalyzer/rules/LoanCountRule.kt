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
        val overdues = overdueRepository.getOverdues(client.id)
        var counter = 0
        for (i in loanRepository.getLoans(client.id)) {
            if (!i.isClosed) {
                for (j in overdues) {
                    if (j.loanId == i.id && LocalDateTime.now().minusDays(j.daysOverdue.toLong()+30) > i.startDate) {
                        println(j.daysOverdue.toString()+ ", " + LocalDateTime.now().minusDays(j.daysOverdue.toLong()+30).toString() + " ::::: " + i.startDate)
                        counter++
                    }
                }
            }
        }
        println(counter)
        return ScoringResult(ruleName, when {
            counter > 3 -> PaymentRisk.HIGH
            counter > 0 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        })
    }
}