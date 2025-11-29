package ru.tbank.education.school.lesson7.lection.collections

data class ApiResponse(val code: Int, val message: String)
data class Movie(val title: String, val rating: Double)

fun main() {
    val responses = listOf(
        ApiResponse(code = 500, message = "Internal Error"),
        ApiResponse(code = 404, message = "Not Found"),
        ApiResponse(code = 200, message = "OK"),
        ApiResponse(code = 200, message = "Cached OK")
    )

    val firstSuccess = responses.first { it.code == 200 }
    val lastError = responses.last { it.code == 500 }

    println("Первый успешный ответ: $firstSuccess")
    println("Последняя ошибка: $lastError")

    println("------------")

    val movies = listOf(
        Movie(title = "Интерстеллар", rating = 9.0),
        Movie(title = "Начало", rating = 8.8),
        Movie(title = "Дюна", rating = 8.2),
        Movie(title = "Тёмный рыцарь", rating = 9.1),
        Movie(title = "Мементо", rating = 8.5)
    )

    val top3 = movies
        .sortedByDescending { it.rating }
        .take(3)

    println("Топ-3 фильма по рейтингу:")
    top3.forEach { println("${it.title} — ${it.rating}") }
}
