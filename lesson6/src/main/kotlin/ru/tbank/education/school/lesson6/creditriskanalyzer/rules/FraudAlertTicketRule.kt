package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TicketTopic
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TicketRepository

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
        val tickets = ticketRepo.getTickets(client.id)
        var hasUnresolvedFraudAlert = false
        var hasResolvedFraudAlert = false

        for (ticket in tickets) {
            if (ticket.topic == TicketTopic.FRAUD_ALERT) {
                if (!ticket.resolved) {
                    hasUnresolvedFraudAlert = true
                } else {
                    hasResolvedFraudAlert = true
                }
            }
        }
        val risk = when {
            hasUnresolvedFraudAlert -> PaymentRisk.HIGH
            hasResolvedFraudAlert -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(ruleName, risk)
    }
}