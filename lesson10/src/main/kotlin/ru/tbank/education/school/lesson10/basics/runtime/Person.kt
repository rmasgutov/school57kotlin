package ru.tbank.education.school.lesson10.basics.runtime

import kotlin.reflect.full.memberFunctions
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible

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

fun main() {
    val person = Person("John", "Doe", 20)
    val personClass = Person::class

    val property = personClass.memberProperties.first { it.name == "name" }
    val function = personClass.memberFunctions.first { it.name == "sayHelloTo" }

    function.isAccessible = true

    println(property.get(person))
    println(function.call(person, "Jane"))
}