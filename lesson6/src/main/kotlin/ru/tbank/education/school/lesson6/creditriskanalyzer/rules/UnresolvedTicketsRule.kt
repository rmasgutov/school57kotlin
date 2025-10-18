package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.ScoringResult
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.TicketRepository

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

        var totalTickets = 0
        var unresolvedTickets = 0

        var i = 0
        while (i < tickets.size) {
            val ticket = tickets[i]
            totalTickets = totalTickets + 1

            if (!ticket.resolved) {
                unresolvedTickets = unresolvedTickets + 1
            }

            i = i + 1
        }

        val ratio: Double = if (totalTickets > 0) {
            unresolvedTickets.toDouble() / totalTickets.toDouble()
        } else {
            0.0
        }

        val risk = if (ratio > 0.5) {
            PaymentRisk.HIGH
        } else if (ratio > 0.2) {
            PaymentRisk.MEDIUM
        } else {
            PaymentRisk.LOW
        }

        return ScoringResult(ruleName, risk)

    }
}
