package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
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

        // Суммируем задолженность по активным кредитам
        var i = 0
        while (i < loans.size) {
            val loan = loans[i]
            if (!loan.isClosed) {
                totalDebt = totalDebt + loan.debt
            }
            i = i + 1
        }

        // Суммируем общий баланс по всем счетам
        var j = 0
        while (j < accounts.size) {
            val account = accounts[j]
            totalBalance = totalBalance + account.balance
            j = j + 1
        }

        val risk: PaymentRisk

        if (totalBalance <= 0) {
            // Если нет денег на счетах, считаем ситуацию критической
            risk = PaymentRisk.HIGH
        } else if (totalDebt > 3 * totalBalance) {
            risk = PaymentRisk.HIGH
        } else if (totalDebt > totalBalance) {
            risk = PaymentRisk.MEDIUM
        } else {
            risk = PaymentRisk.LOW
        }

        return ScoringResult(ruleName, risk)
    }

