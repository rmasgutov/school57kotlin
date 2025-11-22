package ru.tbank.education.school.practice.exceptions

/**
 * Интерфейс безопасного калькулятора.
 */
interface SafeCalculator {

    /**
     * Делит число `a` на `b`.
     *
     * Если `b == 0`, метод должен поймать исключение
     * и вернуть `null` вместо выбрасывания ошибки.
     *
     * @param a делимое
     * @param b делитель
     * @return результат деления или null, если деление на ноль
     */
    fun divide(a: Int, b: Int): Int?
}

class SafeCalculatorImpl : SafeCalculator {
    override fun divide(a: Int, b: Int): Int? {
        try {
            return a / b
        } catch (e: ArithmeticException) {
            println("Попытка деления на ноль")
            return null
        } catch (e: Exception) {
            println("Найдена ошибка: ${e.message}")
            throw e
        }
    }
}
//
//fun main() {
//    val calculator = SafeCalculatorImpl()
//    println(calculator.divide(10, 0))
//}