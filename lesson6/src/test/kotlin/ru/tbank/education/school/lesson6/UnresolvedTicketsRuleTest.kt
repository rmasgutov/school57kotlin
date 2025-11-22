package ru.tbank.education.school.lesson6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Region
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Ticket
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TicketTopic
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryTicketRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.UnresolvedTicketsRule
import java.util.*

class UnresolvedTicketsRuleTest {

    private val clientId = UUID.randomUUID()

    @Test
    fun `должен вернуть LOW если у клиента нет обращений`() {
        val rule = UnresolvedTicketsRule(
            ticketRepo = InMemoryTicketRepository(mapOf(clientId to emptyList()))
        )

        val result = rule.evaluate(Client(clientId, "Анна Иванова", 30, Region.SPB))
        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть LOW если доля нерешённых меньше 20%`() {
        val tickets = listOf(
            Ticket(clientId, TicketTopic.CARD_BLOCKED, true),
            Ticket(clientId, TicketTopic.PAYMENT_ERROR, true),
            Ticket(clientId, TicketTopic.OTHER, true),
            Ticket(clientId, TicketTopic.OTHER, true),
            Ticket(clientId, TicketTopic.OTHER, false)
        )

        val rule = UnresolvedTicketsRule(
            ticketRepo = InMemoryTicketRepository(mapOf(clientId to tickets))
        )

        val result = rule.evaluate(Client(clientId, "Павел Смирнов", 40, Region.MOSCOW))
        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если доля нерешённых от 20 до 50 процентов`() {
        val tickets = listOf(
            Ticket(clientId, TicketTopic.PAYMENT_ERROR, false),
            Ticket(clientId, TicketTopic.CARD_BLOCKED, true),
            Ticket(clientId, TicketTopic.FRAUD_ALERT, false),
            Ticket(clientId, TicketTopic.OTHER, true),
            Ticket(clientId, TicketTopic.LOAN_REQUEST, true)
        )

        val rule = UnresolvedTicketsRule(
            ticketRepo = InMemoryTicketRepository(mapOf(clientId to tickets))
        )

        val result = rule.evaluate(Client(clientId, "Мария Петрова", 28, Region.KAZAN))
        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть HIGH если более 50 процентов обращений нерешены`() {
        val tickets = listOf(
            Ticket(clientId, TicketTopic.FRAUD_ALERT, false),
            Ticket(clientId, TicketTopic.PAYMENT_ERROR, false),
            Ticket(clientId, TicketTopic.CARD_BLOCKED, false),
            Ticket(clientId, TicketTopic.OTHER, true)
        )

        val rule = UnresolvedTicketsRule(
            ticketRepo = InMemoryTicketRepository(mapOf(clientId to tickets))
        )

        val result = rule.evaluate(Client(clientId, "Олег Кузнецов", 37, Region.NOVOSIBIRSK))
        assertEquals(PaymentRisk.HIGH, result.score)
    }
}
