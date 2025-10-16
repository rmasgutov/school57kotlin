package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.AccountType
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
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
        val account = accountRepo.getAccounts(client.id)
        val setCurrency : MutableSet<Currency> = mutableSetOf()
        val setType : MutableSet<AccountType> = mutableSetOf()
        for (i in account) {
            setCurrency.add(i.currency)
            setType.add(i.type)
        }
        val unique = setType.size + setCurrency.size
        val risk = when {
            unique <= 2 -> PaymentRisk.HIGH
            unique <= 4 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(
            ruleName,
            risk
        )
    }
}
