package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TicketTopic
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TicketRepository
import kotlin.math.min

/**
 * Проверяет, были ли у клиента обращения, связанные с мошенничеством.
 *
 * Идея:
 * - Получить все тикеты клиента.
 * - Проверить, есть ли обращения с темой FRAUD_ALERT.
 * - Оценить, решены ли такие тикеты.
 *
 * Как считать score:
 * - Если есть FRAUD_ALERT и он не решён → HIGH
 * - Если FRAUD_ALERT есть, но решён → MEDIUM
 * - Если FRAUD_ALERT нет вообще → LOW
 *
 */
class FraudAlertTicketRule(
    private val ticketRepo: TicketRepository
) : ScoringRule {

    override val ruleName: String = "Fraud Alert Ticket"

    override fun evaluate(client: Client): ScoringResult {
        var FraudAlertAvaibility = 3
        for(it in ticketRepo.getTickets(client.id)) {
            if (it.topic == TicketTopic.FRAUD_ALERT && it.resolved == false) {
                FraudAlertAvaibility = 1
            } else if (it.topic == TicketTopic.FRAUD_ALERT && it.resolved == true) {
                if (FraudAlertAvaibility != 1) {
                    FraudAlertAvaibility = 2
                }
            }
        }
        val risk = when{
            FraudAlertAvaibility == 1 -> PaymentRisk.HIGH
            FraudAlertAvaibility == 2 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(
            ruleName,
            risk)
    }
}