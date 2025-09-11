package ru.tbank.education.school.lesson1


fun sumEvenNumbers(numbers: Array<Int>): Int {
    var summ = 0
    for (n in numbers) {
        if (n % 2 == 0) {
            summ += n
        }
    }
    return summ
}

fun main() {
    val numbers = arrayOf(1, 2, 3, 4)
    print(sumEvenNumbers(numbers))

}