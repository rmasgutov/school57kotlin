package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.AccountRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.LoanRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
/**
 * Проверяет соотношение между суммарной задолженностью и балансом на счетах.
 *
 * Идея:
 * - Получить все активные кредиты клиента (isClosed == false)
 * - Посчитать общий долг (sumOf(debt))
 * - Посчитать общий баланс на всех счетах
 * 
 * Как считать score:
 * - Если долг > 3 * баланс → HIGH
 * - Если долг > баланс, но < 3 * баланс → MEDIUM
 * - Если долг <= баланс → LOW
 *
 */
class LoanDebtRatioRule(
    private val loanRepo: LoanRepository,
    private val accountRepo: AccountRepository,
) : ScoringRule {

    override val ruleName: String = "Loan Debt Ratio"
    var totalDebt = 0.0
    var totalBalance = 0.0
    override fun evaluate(client: Client): ScoringResult {
        val allLoans = loanRepo.getLoans(client.id)
        val allAccounts = accountRepo.getAccounts(client.id)
        for (loan in allLoans) {
            if (loan.isClosed == false) {
                totalDebt = totalDebt + loan.debt
            }
        }
        for (account in allAccounts) {
            totalBalance = totalBalance + account.balance
        }
        val risk = when {
            totalDebt > 3 * totalBalance -> PaymentRisk.HIGH
            totalDebt > totalBalance && totalDebt < 3 * totalBalance -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(
            ruleName,
            risk
        )
    }
}