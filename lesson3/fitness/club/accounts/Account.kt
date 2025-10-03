import ru.tbank.education.school.lesson3.seminar.bank.models.Trainer
import ru.tbank.education.school.lesson3.seminar.bank.models.Customer
import ru.tbank.education.school.lesson3.seminar.bank.models.Available
import ru.tbank.education.school.lesson3.seminar.bank.models.Transaction
abstract class Account(
    val id: String,
    val owner: Customer,
    val available: Available
) {
    protected var balance: Int = 0

    val balanceView: Int
        get() = balance

    protected val transactions = mutableListOf<Transaction>()

    abstract fun ava(work: Boolean, description: String): Boolean

    fun transfer(to: Account, amount: Int, description: String): Boolean {
        if (!available(work, description)) return false
        return true
    }

    fun printReport() {
        println("📊 Отчёт по счёту $id (владелец: ${owner.fullName})")
        println("Сосотойние Карты $work ")
        println("Баланс: $balance $")
        }
    }

}