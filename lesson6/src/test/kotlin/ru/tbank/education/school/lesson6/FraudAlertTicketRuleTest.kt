package ru.tbank.education.school.lesson6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Client
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.PaymentRisk
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Region
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Ticket
import ru.tbank.education.school.lesson6.creditriskanalyzer.models.TicketTopic
import ru.tbank.education.school.lesson6.creditriskanalyzer.repositories.InMemoryTicketRepository
import ru.tbank.education.school.lesson6.creditriskanalyzer.rules.FraudAlertTicketRule
import java.util.*

class FraudAlertTicketRuleTest {

    private val clientId = UUID.randomUUID()

    @Test
    fun `должен вернуть HIGH если есть FRAUD_ALERT и он не решен`() {
        val tickets = listOf(
            Ticket(clientId, TicketTopic.FRAUD_ALERT, resolved = false),
            Ticket(clientId, TicketTopic.CARD_BLOCKED, resolved = true)
        )

        val repo = InMemoryTicketRepository(mapOf(clientId to tickets))
        val rule = FraudAlertTicketRule(repo)

        val result = rule.evaluate(Client(clientId, "Иван Иванов", 35, Region.MOSCOW))

        assertEquals(PaymentRisk.HIGH, result.score)
    }

    @Test
    fun `должен вернуть MEDIUM если FRAUD_ALERT есть но решен`() {
        val tickets = listOf(
            Ticket(clientId, TicketTopic.FRAUD_ALERT, resolved = true),
            Ticket(clientId, TicketTopic.PAYMENT_ERROR, resolved = true)
        )

        val repo = InMemoryTicketRepository(mapOf(clientId to tickets))
        val rule = FraudAlertTicketRule(repo)

        val result = rule.evaluate(Client(clientId, "Мария Петрова", 28, Region.SPB))

        assertEquals(PaymentRisk.MEDIUM, result.score)
    }

    @Test
    fun `должен вернуть LOW если FRAUD_ALERT нет вообще`() {
        val tickets = listOf(
            Ticket(clientId, TicketTopic.CARD_BLOCKED, resolved = true),
            Ticket(clientId, TicketTopic.PAYMENT_ERROR, resolved = false)
        )

        val repo = InMemoryTicketRepository(mapOf(clientId to tickets))
        val rule = FraudAlertTicketRule(repo)

        val result = rule.evaluate(Client(clientId, "Олег Сидоров", 40, Region.KAZAN))

        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `должен вернуть LOW если тикетов вообще нет`() {
        val repo = InMemoryTicketRepository(emptyMap())
        val rule = FraudAlertTicketRule(repo)

        val result = rule.evaluate(Client(clientId, "Без Обращений", 30, Region.OTHER))

        assertEquals(PaymentRisk.LOW, result.score)
    }

    @Test
    fun `если есть несколько FRAUD_ALERT и хотя бы один не решен — HIGH`() {
        val tickets = listOf(
            Ticket(clientId, TicketTopic.FRAUD_ALERT, resolved = true),
            Ticket(clientId, TicketTopic.FRAUD_ALERT, resolved = false)
        )

        val repo = InMemoryTicketRepository(mapOf(clientId to tickets))
        val rule = FraudAlertTicketRule(repo)

        val result = rule.evaluate(Client(clientId, "Дмитрий Двойной", 29, Region.SPB))

        assertEquals(PaymentRisk.HIGH, result.score)
    }
}
