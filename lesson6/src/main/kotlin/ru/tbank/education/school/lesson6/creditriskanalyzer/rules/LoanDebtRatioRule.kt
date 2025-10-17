package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.AccountRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.LoanRepository

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

    override fun evaluate(client: Client): ScoringResult {
        val loans = loanRepo.getLoans(client.id)
        val accounts = accountRepo.getAccounts(client.id)

        var totalDebt = 0.0
        var totalBalance = 0.0

        for (loan in loans) {
            if (!loan.isClosed) totalDebt += loan.debt
        }
        for (account in accounts) totalBalance += account.balance

        val risk = when {
            totalDebt > 3 * totalBalance -> PaymentRisk.HIGH
            totalDebt > totalBalance -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(ruleName, risk)
    }
}