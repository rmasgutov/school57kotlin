package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.AccountRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.AccountType
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Currency
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk

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
        var flag=0
        if (accounts.isEmpty()) {
            flag=1
        }
        // Флаги для каждого типа счета
        var hasDebit = false
        var hasSavings = false
        var hasCredit = false

        // Флаги для каждой валюты
        var hasRub = false
        var hasEur = false
        var hasUsd = false

        for (account in accounts) {
            when (account.type) {
                AccountType.DEBIT -> hasDebit = true
                AccountType.SAVINGS -> hasSavings = true
                AccountType.CREDIT -> hasCredit = true
            }

            when (account.currency) {
                Currency.RUB -> hasRub = true
                Currency.EUR -> hasEur = true
                Currency.USD -> hasUsd = true
            }
        }

        var uniqueTypesCount = 0
        if (hasDebit) uniqueTypesCount++
        if (hasSavings) uniqueTypesCount++
        if (hasCredit) uniqueTypesCount++

        var uniqueCurrenciesCount = 0
        if (hasRub) uniqueCurrenciesCount++
        if (hasEur) uniqueCurrenciesCount++
        if (hasUsd) uniqueCurrenciesCount++

        val diversityScore = uniqueTypesCount + uniqueCurrenciesCount

        val risk = when {
            diversityScore <= 2 -> PaymentRisk.HIGH
            flag==1 -> PaymentRisk.HIGH
            diversityScore <= 4 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }

        return ScoringResult(
            ruleName,
            risk ,
        )
    }
}
