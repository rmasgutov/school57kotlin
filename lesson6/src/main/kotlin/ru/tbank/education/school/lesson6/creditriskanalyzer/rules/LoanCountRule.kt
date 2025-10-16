package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.LoanRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.OverdueRepository

/**
 * Необходимо посчитать количество открытых кредитов с просрочкой больше 30 дней
 *
 * Как считать score:
 * - Если таких кредитов больше 3 → HIGH (слишком свежие)
 * - Если таких кредитов от одного до 3 включительно → MEDIUM
 * - Если таких кредитов нет → LOW
 */
class LoanCountRule(
    private val loanRepo: LoanRepository,
    private val overdueRepo: OverdueRepository
) : ScoringRule {


    override val ruleName: String = "Loan Count"

    override fun evaluate(client: Client): ScoringResult {
        var OpenOverdueLoanCount = 0
        for(it1 in loanRepo.getLoans(client.id)) {
            if(it1.isClosed == false){
                for(it2 in overdueRepo.getOverdues(client.id)){
                    if(it2.loanId == it1.id && it2.daysOverdue > 30){
                        OpenOverdueLoanCount++
                    }
                }
            }
        }
        val risk = when{
            OpenOverdueLoanCount > 3 -> PaymentRisk.HIGH
            OpenOverdueLoanCount >= 1 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(
            ruleName,
            risk
        )
    }
}