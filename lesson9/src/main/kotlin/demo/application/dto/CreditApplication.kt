package demo.application.dto

data class CreditApplication(
        val user: User,
        val correspondingLoan: Loan,
)
