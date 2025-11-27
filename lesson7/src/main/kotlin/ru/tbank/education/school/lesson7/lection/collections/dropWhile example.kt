package ru.tbank.education.school.lesson7.lection.collections

fun main() {
    val logs = listOf(
        "INFO: Server started",
        "INFO: Connected to DB",
        "INFO: Request received",
        "ERROR: Connection timeout",
        "WARN: Retrying connection",
        "INFO: Success"
    )

    // Отбрасываем все строки до первой ошибки
    val fromError = logs.dropWhile { !it.startsWith("ERROR") }

    println("Анализ логов с момента ошибки:")
    fromError.forEach(::println)
}
