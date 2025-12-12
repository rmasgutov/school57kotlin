package ru.tbank.education.school.lesson6.creditriskanalyzer.repositories

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Transaction
import java.util.UUID

interface TransactionRepository {
    fun getTransactions(clientId: UUID): List<Transaction>
}

class InMemoryTransactionRepository(private val data: Map<UUID, List<Transaction>>) : TransactionRepository {
    override fun getTransactions(clientId: UUID): List<Transaction> = data[clientId].orEmpty()
}