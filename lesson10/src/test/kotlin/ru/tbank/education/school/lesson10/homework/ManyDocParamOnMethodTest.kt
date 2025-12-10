package ru.tbank.education.school.lesson10.homework

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class ManyDocParamOnMethodTest {
    @DocClass(description = "Калькулятор с аннотированными методами")
    class Calculator {

        @DocMethod(
            description = "Складывает два числа",
            returns = "Сумма a и b"
        )
        fun add(
            @DocParam(description = "Первое слагаемое") a: Int,
            @DocParam(description = "Второе слагаемое") b: Int
        ): Int = a + b

        @DocMethod(
            description = "Делит a на b",
            returns = "Результат деления или 0 при делении на ноль"
        )
        fun divide(
            @DocParam(description = "Делимое") a: Double,
            @DocParam(description = "Делитель (не должен быть 0)") b: Double
        ): Double {
            return if (b == 0.0) 0.0 else a / b
        }
    }

    @Test
    fun `должен корректно документировать методы с несколькими параметрами`() {
        val calc = Calculator()
        val doc = DocumentationGenerator.generateDoc(calc)

        assertTrue(doc.contains("Складывает два числа"))
        assertTrue(doc.contains("Первое слагаемое"))
        assertTrue(doc.contains("Второе слагаемое"))
        assertTrue(doc.contains("Сумма a и b"))

        assertTrue(doc.contains("Делит a на b"))
        assertTrue(doc.contains("Делимое"))
        assertTrue(doc.contains("Делитель (не должен быть 0)"))
        assertTrue(doc.contains("Результат деления или 0 при делении на ноль"))
    }
}