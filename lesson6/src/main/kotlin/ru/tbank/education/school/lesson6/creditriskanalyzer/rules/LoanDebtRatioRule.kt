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
        val id = client.id
        val loan = loanRepo.getLoans(id)
        val account = accountRepo.getAccounts(id)
        var sumLoan = 0L
        for (i in loan) {sumLoan += i.debt}
        var sumBalance = 0L
        for (i in account) {sumBalance += i.balance}
        val risk = when {
            sumLoan > 3 * sumBalance -> PaymentRisk.HIGH
            sumLoan > sumBalance -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(
            ruleName,
            risk
        )
    }
}