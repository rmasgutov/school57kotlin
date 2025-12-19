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
        var all = 0
        var not_resolved = 0
        for (i in ticketRepo.getTickets(client.id)) {
            all++
            if (!i.resolved) {
                not_resolved++
            }
        }
        if (all == 0) {
            return ScoringResult(ruleName, PaymentRisk.LOW)
        }
        val res = not_resolved.toDouble() / all
        return ScoringResult(ruleName, when {
            res > 0.5 -> PaymentRisk.HIGH
            res > 0.2-> PaymentRisk.MEDIUM
            else -> PaymentRisk.LOW
        })
    }
}
