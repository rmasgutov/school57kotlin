package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TicketRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
/**
 * Проверяет, насколько часто клиент имеет нерешённые обращения в поддержку.
 *
 * Идея:
 * - Получить все тикеты клиента.
 * - Определить количество нерешённых обращений.
 * - Рассчитать долю нерешённых тикетов.
 *
 * Как считать risk:
 * - Если доля > 0.5 → HIGH
 * - Если доля > 0.2 → MEDIUM
 * - Иначе → LOW
 */
class UnresolvedTicketsRule(
    private val ticketRepo: TicketRepository
) : ScoringRule {

    override val ruleName: String = "Unresolved Tickets"

    override fun evaluate(client: Client): ScoringResult {
        val tickets = ticketRepo.getTickets(client.id)
        var flag=0
        if (tickets.isEmpty()) {
            flag=1
        }

        var unresolvedCount = 0
        var totalCount = 0

        for (ticket in tickets) {
            totalCount++
            if (!ticket.resolved) {
                unresolvedCount++
            }
        }

        val unresolvedRatio = unresolvedCount.toDouble() / totalCount

        val risk = when {
            flag==1 -> PaymentRisk.LOW
            unresolvedRatio > 0.5 -> PaymentRisk.HIGH
            unresolvedRatio > 0.2 -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }

        return ScoringResult(
            ruleName,
            risk,
            )
    }
}
