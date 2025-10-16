package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.AccountRepository
import java.util.Objects.toString

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
        val TypeAccountsCount = mutableSetOf<String>()
        for (it in accountRepo.getAccounts(client.id)){
            TypeAccountsCount.add(toString(it.type))
        }
        val TypeCurrenciesCount = mutableSetOf<String>()
        for (it in accountRepo.getAccounts(client.id)){
            TypeCurrenciesCount.add(toString(it.currency))
        }
        val Fullsum = TypeAccountsCount.size + TypeCurrenciesCount.size

        val risk = when{
            Fullsum <= 2 -> PaymentRisk.HIGH
            Fullsum <= 4 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult (
            ruleName,
            risk
        )
    }
}
