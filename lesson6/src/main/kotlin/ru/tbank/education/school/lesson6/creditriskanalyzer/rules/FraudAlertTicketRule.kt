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
        val tick = ticketRepo.getTickets(client.id)
        val themes : MutableSet<TicketTopic> = mutableSetOf()
        val solve : MutableSet<Boolean> = mutableSetOf()
        var risk : PaymentRisk = PaymentRisk.LOW
        for (i in tick) {
            themes.add(i.topic)
            solve.add(i.resolved)
            val currRisk = when {
                i.topic == TicketTopic.FRAUD_ALERT && !i.resolved -> PaymentRisk.HIGH
                i.topic == TicketTopic.FRAUD_ALERT && i.resolved -> PaymentRisk.MEDIUM
                else -> PaymentRisk.LOW
            }
            if (currRisk > risk) {
                risk = currRisk
            }
        }
        return ScoringResult(
            ruleName,
            risk
        )
    }
}