import ru.tbank.education.school.lesson7.Deposit
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import kotlin.math.pow

// Константы для числовых значений
private const val VIP_BONUS = 1
private const val DAYS_IN_YEAR = 365.0
private const val PERCENT_DIVISOR = 100

/**
 * Текущий баланс по вкладу, отображающийся в личном кабинете с учетом всех начислений.
 */
fun currentBalance(deposit: Deposit): Double {
    val rate = deposit.compoundInterestRate.let {
        if (deposit.isVip) {
            it + VIP_BONUS
        } else {
            it
        } / PERCENT_DIVISOR
    }

    return deposit.initialDeposit * (1 + rate / DAYS_IN_YEAR).pow(
        ChronoUnit.DAYS.between(deposit.createAt, LocalDate.now()).toDouble()
    )
}
