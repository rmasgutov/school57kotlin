package ru.tbank.education.school.lesson7.lection.collections

data class Task(val title: String, val priority: String)


fun main() {
    val prices = listOf(499.0, 1299.0, 799.0, 299.0, 999.0)

    val natural = prices.sorted()
    val reversed = prices.sortedDescending()

    println("Обычная сортировка: $natural")
    println("Обратная сортировка: $reversed")

    println("-----------------------")

    val tasks = listOf(
        Task("Проверить отчёт", "LOW"),
        Task("Созвон с клиентом", "HIGH"),
        Task("Отправить письмо", "MEDIUM"),
        Task("Подготовить презентацию", "HIGH"),
        Task("Закрыть задачу", "LOW")
    )

    val priorityOrder = listOf("HIGH", "MEDIUM", "LOW")

    val sorted = tasks.sortedBy { priorityOrder.indexOf(it.priority) }

    println("Задачи по приоритету:")
    sorted.forEach { println("${it.priority}: ${it.title}") }
}