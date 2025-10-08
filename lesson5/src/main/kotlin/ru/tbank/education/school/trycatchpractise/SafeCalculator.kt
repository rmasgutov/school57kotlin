package ru.tbank.education.school.trycatchpractise

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


class Calculator : SafeCalculator {
    override fun divide(a: Int, b: Int): Int? {
        try {
            return a / b
        }
        catch (e: ArithmeticException) {
            return null
        }
    }
}