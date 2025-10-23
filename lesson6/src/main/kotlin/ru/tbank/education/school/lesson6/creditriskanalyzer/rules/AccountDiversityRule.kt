package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

//import ru.tbank.education.school.lesson6.creditriskanalyzer.models.AccountType
//import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.AccountRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.*
import java.util.Currency
import kotlin.io.path.Path

/**
 * Проверяет разнообразие счетов клиента по типам и валютам.
 *
 * Идея:
 * - Получить все счета клиента.
 * - Посчитать количество уникальных типов счетов.
 * - Посчитать количество уникальных валют.
 * - Суммировать эти показатели для определения диверсификации.
 *
 * Как считать risk:
 * - Если итоговое значение <= 2 → HIGH
 * - Если итоговое значение <= 4 → MEDIUM
 * - Если > 4 → LOW
 */
class AccountDiversityRule(
    private val accountRepo: AccountRepository
) : ScoringRule {

    override val ruleName: String = "Account Diversity"

    override fun evaluate(client: Client): ScoringResult {
        val accounts = accountRepo.getAccounts(client.id)

        val uniqueTypes = mutableSetOf<AccountType>();
        val uniqueCurrencies = mutableSetOf<ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency>();

        for (account in accounts) {
            uniqueTypes.add(account.type)
            uniqueCurrencies.add(account.currency)
        }

        val sumScore = uniqueCurrencies.size + uniqueTypes.size
        val risk = when {
            sumScore <= 2 -> PaymentRisk.HIGH
            sumScore <= 4 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }

        return ScoringResult(ruleName, risk)
    }
}
