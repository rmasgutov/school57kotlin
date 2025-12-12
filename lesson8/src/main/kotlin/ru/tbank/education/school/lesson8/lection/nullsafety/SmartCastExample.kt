package ru.tbank.education.school.lesson8.lection.nullsafety

fun main() {
    // 1) Локальная val — работает
    val s: String? = "Hello"
    if (s != null) {
        println(s.length) // smart cast to String
    }

    // 2) var — может не работать, если потенциально меняется
    var t: String? = "Hi"
    if (t != null) {
        // В простом случае компилятор разрешит smart cast, но если бы между if и использованием
        // была потенциальная смена значения (например, многопоточность или вызов, меняющий t),
        // компилятор мог бы запретить smart cast.
        println(t.length)
    }

    // 3) Свойство класса: зависимость от геттера
    class Box {
        var value: String? = null
            get() {
                println("getter called")
                return field
            }
    }

    val b = Box()
    b.value = "Kotlin"
    if (b.value != null) {
        // Может не сработать smart cast (геттер вызывается заново и теоретически может вернуть null)
        // Поэтому безопаснее сделать локальную временную переменную:
        val tmp = b.value
        if (tmp != null) println(tmp.length)
    }

    // 4) Smart cast по типу (is)
    val any: Any = listOf(1, 2, 3)
    if (any is List<*>) {
        println(any.size) // smart cast к List<*>
    }

    // 5) when со smart cast:
    fun describe(x: Any?) = when (x) {
        null -> "is null"
        is String -> "String of length ${x.length}"
        is Int -> "Int square ${x * x}"
        else -> "Unknown type"
    }
    println(describe("abc"))
    println(describe(10))
    println(describe(null))
}
