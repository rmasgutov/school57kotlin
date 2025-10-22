package ru.tbank.education.school

fun main() {
    val input = "numbers.txt"
    val outputIO = "result_io.txt"
    val outputNIO = "result_nio.txt"

    Average.calculateIO(input, outputIO)
    Average.calculateNIO(input, outputNIO)

    println("Готово! Результаты записаны в $outputIO и $outputNIO")

    val input1 = "sentences.txt"
    val outputIO1 = "analysis_io.txt"
    val outputNIO1 = "analysis_nio.txt"

    Analyzer.analyzeIO(input1, outputIO1)
    Analyzer.analyzeNIO(input1, outputNIO1)

    println("Анализ завершён! Результаты в $outputIO1 и $outputNIO1")
}