package ru.tbank.education.school.lesson7.lection

//fun divide(x: Int, y: Int): Int {
//    return x / y
//}

fun divide(x: Int, y: Int): Int? {
    return if (y == 0) null else x / y
}

fun divide(x: Int, y: NonZeroInt): Int =
    x / y.value

data class NonZeroInt(val value: Int) {
    init {
        require(value != 0) { "Value must be non-zero" }
    }
}

fun main() {
    val x = 10
    // val y0 = NonZeroInt(0)  IllegalArgumentException: "Value must be non-zero"
    val y = NonZeroInt(2)
    println(divide(x, y)) // 5
}