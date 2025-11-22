package ru.tbank.education.school.lesson8.lection.calculator

class Calculator {
    fun add(a: Int, b: Int) = a + b
    fun subtract(a: Int, b: Int) = a - b
    fun multiply(a: Int, b: Int) = a * b
    fun divide(a: Int, b: Int): Int {
        require(b != 0) { "Division by zero" }
        return a / b
    }
}
