import ru.tbank.education.school.lesson3.club.accounts.Account
import ru.tbank.education.school.lesson3.club.models.Available
import ru.tbank.education.school.lesson3.club.models.Customer
import ru.tbank.education.school.lesson3.club.models.Trainer

class Avail(id: String, owner: Customer, available: Available) :
    Account(id, owner, available) {

    override fun ava(availab: Boolean, description: String): Boolean {
        work = false
        return true
    }
}