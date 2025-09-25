import ru.tbank.education.school.lesson3.club.accounts.Account
import ru.tbank.education.school.lesson3.fitness.club.models.Available
import ru.tbank.education.school.lesson3.fitness.club.models.Customer
import ru.tbank.education.school.lesson3.fitness.club.models.Trainer
import java.time.LocalDate

class Club(val name: String) {
    private val customers = mutableListOf<Customer>()
    private val accounts = mutableListOf<Account>()

    private var customerSeq = 1
    private var accountSeq = 1

    fun registerCustomer(fullName: String): Customer {
        val c = Customer("C-${customerSeq++}", fullName)
        customers += c
        return c
    }
    }
}
