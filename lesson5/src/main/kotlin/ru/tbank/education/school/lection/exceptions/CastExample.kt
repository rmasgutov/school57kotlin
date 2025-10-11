package ru.tbank.education.school.lection.exceptions

import javax.swing.text.BadLocationException

fun main() {
    println("Введите целое число: ")
    val input = readLine()

    val number = input?.toInt()
    println("Вы ввели число: $number")
}