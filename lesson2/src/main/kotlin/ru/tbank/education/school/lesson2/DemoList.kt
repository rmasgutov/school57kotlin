package ru.tbank.education.school.lesson2
import kotlin.random.Random
object DemoList {
    fun createList(): List<Int> {
        val arr = List(5){Random.nextInt(1, 101)}
        return arr
    }

    fun sumList(arr: List<Int>): Int {
        return arr.sum()
    }

    fun sumEvenList(arr: List<Int>): Int {
        return arr.filter {it % 2 == 0}.sum()
    }

    fun multiplyList(arr: List<Int>): List<Int> {
        return arr.map{it * 2}
    }

    fun maxElement(arr: List<Int>): Int {
        return arr.max()
    }

    fun sortDesc(arr: List<Int>): List<Int> {
        return arr.sortedDescending()
    }
}
fun main() {
    val demoList = DemoList.createList()
    println("Сумма всех элементов списка: ${DemoList.sumList(demoList)}")
    println("Сумма всех чётных элементов: ${DemoList.sumEvenList(demoList)}")
    val multiList = DemoList.multiplyList(demoList)
    println("Весь список, умноженный на 2:")
    multiList.forEach { number -> println("$number") }
    println("Максимальный элемент списка: ${DemoList.maxElement(demoList)}")
    val sortedList = DemoList.sortDesc(demoList)
    println("Сортировка по убыванию: ")
    sortedList.forEach { number -> println("$number") }
}
