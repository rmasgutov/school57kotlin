package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.LoanRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.OverdueRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
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
            // Получаем все кредиты клиента
            val allLoans = loanRepository.getLoans(client.id)
            val allOverdues = overdueRepository.getOverdues(client.id)

            var counter = 0

            for (loan in allLoans) {
                // Проверяем, что кредит открыт
                if (loan.isClosed == false) {
                    // Ищем просрочку для этого кредита
                    var flag = false
                    for (overdue in allOverdues) {
                        if (overdue.loanId == loan.id && overdue.daysOverdue > 30) {
                            flag = true
                            break
                        }
                    }
                    if (flag) {
                        counter++
                    }
                }
            }

            val risk = when {
                counter > 3 -> PaymentRisk.HIGH
                counter >1 && counter<=1-> PaymentRisk.MEDIUM
                else -> PaymentRisk.LOW
            }

            return ScoringResult(
                ruleName,
                risk
            )
        }
    }