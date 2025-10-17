package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.AccountRepository

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

        val uniqueTypes = mitavleListOf<String>()
        val uniqueCurrencies = mutableSetOf<String>()

        var i = 0
        while (i < accounts.size) {
            val acc = accounts[i]
            uniqueTypes.add(acc.type)
            uniqueCurrencies.add(acc.currency)
            i++
        }

        val total = uniqueTypes.size + uniqueCurrencies.size

        val risk = if (total <= 2) {
            PaymentRisk.HIGH
        } else if (total <= 4) {
            PaymentRisk.MEDIUM
        } else {
            PaymentRisk.LOW
        }


        return ScoringResult(ruleName, risk)
    }
}


