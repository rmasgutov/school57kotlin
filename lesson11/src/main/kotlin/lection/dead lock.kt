package lection

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import java.math.BigDecimal

data class Account(val name: String, var money: BigDecimal)

class BankAccount(val account: Account) {
    private val mutex = Mutex()

    suspend fun transferTo(
        target: BankAccount,
        amount: BigDecimal,
        transactionName: String
    ) {
        println("[$transactionName] Начинаю перевод $amount")

        // Определяем порядок блокировки по имени аккаунта
        val firstLock = if (account.name < target.account.name) this else target
        val secondLock = if (account.name < target.account.name) target else this

        firstLock.mutex.lock()
        try {
            println("[$transactionName] Заблокировал ${firstLock.account.name}")
            delay(1000) // Имитация работы

            secondLock.mutex.lock()
            try {
                println("[$transactionName] Заблокировал ${secondLock.account.name}")

                if (account.money >= amount) {
                    println("[$transactionName] Выполняю перевод...")
                    account.money = account.money.subtract(amount)
                    target.account.money = target.account.money.add(amount)
                    println("[$transactionName] Перевод завершен")
                } else {
                    println("[$transactionName] Недостаточно средств")
                }
            } finally {
                secondLock.mutex.unlock()
            }
        } finally {
            firstLock.mutex.unlock()
        }
    }
}

fun main() = runBlocking {
    val account1 = BankAccount(Account("Алиса", BigDecimal.valueOf(4000)))
    val account2 = BankAccount(Account("Боб", BigDecimal.valueOf(3000)))

    println("Начальные балансы:")
    println("  ${account1.account.name}: ${account1.account.money}")
    println("  ${account2.account.name}: ${account2.account.money}")
    println()

    val job1 = launch {
        account1.transferTo(account2, BigDecimal.valueOf(1000), "Транзакция 1")
    }

    val job2 = launch {
        account2.transferTo(account1, BigDecimal.valueOf(500), "Транзакция 2")
    }

    // Ждём завершения обеих транзакций
    job1.join()
    job2.join()

    println("\nФинальные балансы:")
    println("  ${account1.account.name}: ${account1.account.money}")
    println("  ${account2.account.name}: ${account2.account.money}")
}