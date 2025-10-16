package ru.tbank.education.school.lesson6.creditriskanalyzer.rules

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
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
        val ticket = ticketRepo.getTickets(client.id)
        var cntFalse: Long = 0
        for (i in ticket) { cntFalse += if (!i.resolved) 1L else 0L }
        val allTicket : Long = ticket.size.toLong()
        val risk = when {
            2L * cntFalse > allTicket -> PaymentRisk.HIGH
            5L * cntFalse > allTicket -> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        }
        return ScoringResult(
            ruleName,
            risk
        )
    }
}
