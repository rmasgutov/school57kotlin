package demo.application.dto

data class User(
    val age: Int,
    val name: String,
    val sex: Int,
    val income: Long,
    val loans: List<Loan>,
) {

}