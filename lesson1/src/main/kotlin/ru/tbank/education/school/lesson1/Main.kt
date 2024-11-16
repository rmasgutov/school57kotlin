package ru.tbank.education.school.lesson1
fun main() {
    var i: Int = 0
    i = i + 1
    println(i)
    var n: Int? = null
    println(n)
    for (i in 1..16 step 2) {
        println(i)
    }
    val listOfName = arrayOf("Глеб","Тим","Даша","Тема", "Клавдия Ивановна", "Бородин Киррил Сергеевич")
    for (name in listOfName) {
        if (name.contains(" ")) {
            val fio = name.split(" ")
            val reversfio = fio.reversed()
            println(reversfio.joinToString(" "))
        }
    }
    val listOfNums = (1..100)
    for (num in listOfNums) {
        if (num % 13 == 0) {
            throw RuntimeException()
        }
    }
}
