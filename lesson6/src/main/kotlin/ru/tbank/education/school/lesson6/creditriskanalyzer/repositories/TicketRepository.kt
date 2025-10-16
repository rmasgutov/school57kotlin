package ru.tbank.education.school.lesson6.creditriskanalyzer.repositories

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Ticket
import java.util.UUID

interface TicketRepository {
    fun getTickets(clientId: UUID): List<Ticket>
}

class InMemoryTicketRepository(private val data: Map<UUID, List<Ticket>>) : TicketRepository {
    override fun getTickets(clientId: UUID): List<Ticket> = data[clientId].orEmpty()
}