package ru.tbank.education.school.lesson2

/**
 * @author <a href="https://github.com/Neruxov">Neruxov</a>
 */
fun main() {
    val input = arrayOf(2, 3, 4, 3, 6, 2, 3, 3)
    for (i in input) {
        val count = input.count { it == i }

        if (count > input.size / 2) {
            println("Искомое число: $i")
            break
        }
    }
}