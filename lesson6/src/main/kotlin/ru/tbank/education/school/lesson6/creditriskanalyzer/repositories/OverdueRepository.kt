package ru.tbank.education.school.lesson6.creditriskanalyzer.repositories

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Overdue
import java.util.UUID

interface OverdueRepository {
    fun getOverdues(clientId: UUID): Set<Overdue>
}

class InMemoryOverdueRepository(private val data: Map<UUID, Set<Overdue>>) : OverdueRepository {
    override fun getOverdues(clientId: UUID): Set<Overdue> = data[clientId].orEmpty()
}