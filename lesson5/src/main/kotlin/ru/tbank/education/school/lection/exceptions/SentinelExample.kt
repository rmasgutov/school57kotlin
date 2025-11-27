package ru.tbank.education.school.lection.exceptions

fun findStringInArray(whatToFind: String, strings: Array<String?>): Int {
    for (i in strings.indices) {
        if (whatToFind == strings[i]) {
            return i
        }
    }
    return -1
}

fun main() {
    val idx: Int = findStringInArray("a", arrayOf("aba", "ab", "a"))
    if (idx >= 0) {
        println("Найдена строка в массиве")
    }
}