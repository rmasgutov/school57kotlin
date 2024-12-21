package demo.application.controller

data class User(
    val age: Int,
    val name: String,
    val sex: Int,
    val creditScore: Int,
    val income: Long,
    val loans: List<demo.application.controller.Loan>,
)
