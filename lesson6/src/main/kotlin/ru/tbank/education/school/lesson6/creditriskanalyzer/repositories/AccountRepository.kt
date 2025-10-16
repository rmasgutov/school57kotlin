package ru.tbank.education.school.lesson6.creditriskanalyzer.repositories

import ru.tbank.education.school.lesson6.creditriskanalyzer.models.Account
import java.util.UUID

interface AccountRepository {
    fun getAccounts(clientId: UUID): List<Account>
}

class InMemoryAccountRepository(private val data: Map<UUID, List<Account>>) : AccountRepository {
    override fun getAccounts(clientId: UUID): List<Account> = data[clientId].orEmpty()
}