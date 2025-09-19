package ru.tbank.education.school.lesson3.seminar.bank

abstract class Account(
    val id: String,
    val owner: Customer,
    val currency: Currency
) {
    protected var balance: Int = 0
    protected val transactions = mutableListOf<Transaction>()

    abstract fun deposit(amount: Int, description: String): Boolean
    abstract fun withdraw(amount: Int, description: String): Boolean

    fun transfer(to: Account, amount: Int, description: String): Boolean {
        if (!withdraw(amount, description)) return false
        if (!to.deposit(amount, description)) return false
        return true
    }

    protected fun record(tx: Transaction) {
        transactions += tx
    }

    fun printReport() {
        println("📊 Отчёт по счёту $id (владелец: ${owner.fullName})")
        println("Баланс: $balance $currency")
        println("Операции:")

        if (transactions.isEmpty()) {
            println("  (нет операций)")
        } else {
            transactions.forEach {
                when (it) {
                    is DepositTransaction ->
                        println("[${it.id}] Пополнение: +${it.amount}, описание: ${it.description}")
                    is WithdrawalTransaction ->
                        println("[${it.id}] Списание: -${it.amount}, описание: ${it.description}")
                    is TransferTransaction ->
                        println("[${it.id}] Перевод: ${it.from.id} -> ${it.to.id}, сумма: ${it.amount}, описание: ${it.description}")
                }
            }
        }
    }

}