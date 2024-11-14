import ru.tbank.education.deposits.Deposit
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
                    }/100
                }
                ) / 365.0//не правильное колчество дней
        ).pow(ChronoUnit.DAYS.between(deposit.createAt, LocalDate.now()).toDouble())