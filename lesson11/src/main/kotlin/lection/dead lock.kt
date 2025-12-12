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

        mutex.lock()
        try {
            println("[$transactionName] Заблокировал ${account.name}")
            delay(1000)

            target.mutex.lock()
            try {
                println("[$transactionName] Заблокировал ${target.account.name}")

                if (account.money >= amount) {
                    println("[$transactionName] Выполняю перевод...")
                    account.money = account.money.subtract(amount)
                    target.account.money = target.account.money.add(amount)
                    println("[$transactionName] Перевод завершен")
                } else {
                    println("[$transactionName] Недостаточно средств")
                }
            } finally {
                target.mutex.unlock()
            }
        } finally {
            mutex.unlock()
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

    launch {
        account1.transferTo(account2, BigDecimal.valueOf(100), "Транзакция 1")
    }

    launch {
        account2.transferTo(account1, BigDecimal.valueOf(100), "Транзакция 2")
    }

    println("Финальные балансы:")
    println("  ${account1.account.name}: ${account1.account.money}")
    println("  ${account2.account.name}: ${account2.account.money}")
}