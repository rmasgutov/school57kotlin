import ru.tbank.education.school.lesson7.Deposit
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.pow

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */
fun currentBalance(deposit: Deposit) = deposit.initialDeposit * (
    1 + (
        deposit.compoundInterestRate.let {
            if (deposit.isVip) {
                it + 1
            } else {
                it
            } / 100 // корректное количество дней
        }
    ) / 365.0
).pow(ChronoUnit.DAYS.between(deposit.createAt, LocalDate.now()).toDouble())
