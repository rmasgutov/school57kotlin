package ru.tbank.education.school.lesson10.basics.model

import ru.tbank.education.school.lesson10.basics.annotations.MyRuntimeAnnotation

/**
 * Data class Person с пользовательской аннотацией.
 * В Kotlin data class аналогичен record в Java - автоматически генерирует
 * equals, hashCode, toString и copy методы.
 */
@MyRuntimeAnnotation(metainfo = "Person metainformation")
data class Person(
    val name: String,
    val surname: String,
    val age: Int
) {

    /**
     * Приватный метод — чтобы показать вызов закрытых методов.
     */
    private fun sayHelloTo(target: String): String {
        return "Hello, $target! I'm $name $surname."
    }
}
