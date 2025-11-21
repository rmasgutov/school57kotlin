package ru.tbank.education.school.lesson8.practise.smartcast

class TestClass(var value: Int?)

fun demoVarProperty() {
    val t = TestClass(10)

    if (t.value != null) {
        val x: Int = t.value?.let { it + 5 } ?: 0
        println(x)
    }
}
