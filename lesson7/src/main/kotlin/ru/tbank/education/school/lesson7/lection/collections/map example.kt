package ru.tbank.education.school.lesson7.lection.collections

data class WeatherRecord(
    val date: String,
    val temperature: Double
)

fun main() {
    val rawData = listOf(
        "2025-09-28:18.5",
        "2025-09-29:21.3",
        "2025-09-30:19.0",
        "2025-10-01:23.4",
        "2025-10-02:17.8"
    )

    val records = rawData.map { line ->
        val (date, temp) = line.split(":")
        WeatherRecord(date, temp.toDouble())
    }

    val hotDays = records
        .filter { it.temperature >= 20.0 }
        .map { "${it.date} (${it.temperature}°C)" }

    println(hotDays)

}