package demo.application.dto

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
data class CreditApplication(
    val requestedLoan: Loan,
    val user: User,
)