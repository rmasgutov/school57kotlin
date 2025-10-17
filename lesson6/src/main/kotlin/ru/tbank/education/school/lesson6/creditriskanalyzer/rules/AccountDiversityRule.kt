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
        val accounts = accountRepo.getAccounts(client.id)
        var arr = Array(6) { false }
        var counter = 0
        for (i in accounts) {
            if (!arr[0] && i.type == AccountType.CREDIT) {
                arr[0] = true
                counter++
            }
            else if (!arr[1] && i.type == AccountType.DEBIT) {
                arr[1] = true
                counter++
            }
            else if (!arr[2] && i.type == AccountType.SAVINGS) {
                arr[2] = true
                counter++
            }


            if (!arr[3] && i.currency == Currency.RUB) {
                arr[3] = true
                counter++
            }
            else if (!arr[4] && i.currency == Currency.EUR) {
                arr[4] = true
                counter++
            }
            else if (!arr[5] && i.currency == Currency.USD) {
                arr[5] = true
                counter++
            }

            if (false !in arr) {
                break
            }
        }
        return ScoringResult(ruleName, when {
            counter <= 2 -> PaymentRisk.HIGH
            counter <= 4 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        })

    }
}
