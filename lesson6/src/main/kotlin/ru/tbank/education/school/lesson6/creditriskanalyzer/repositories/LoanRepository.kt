package ru.tbank.education.school.lesson6.creditriskanalyzer.repositories

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Loan
import java.util.UUID

interface LoanRepository {
    fun getLoans(clientId: UUID): List<Loan>
}

class InMemoryLoanRepository(private val data: Map<UUID, List<Loan>>) : LoanRepository {
    override fun getLoans(clientId: UUID): List<Loan> = data[clientId].orEmpty()
}