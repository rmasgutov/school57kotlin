package ru.tbank.education.school

import java.io.*

/**
 * Класс для вычисления среднего арифметического.
 */
object Average {
    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java IO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */

    fun processFileIO(source: String, target: String) {
        val InputFile = File(source)
        val OutputFile = File(target)
        if (!InputFile.exists()) {
            throw FileNotFoundException("Входной файл не найден: $source")
        }
        if (!OutputFile.exists()) {
            throw FileNotFoundException("Выходной файл не найден: $source")
        }
        var result = mutableListOf<Double>()
        try {
            val lines = File(source).readLines()
            for (line in lines) {
                val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                val average = numbers.average()
                result.add(average)
            }
        } catch (e: Exception) {
            println("Ошибка при чтении файла: ${e.message}")
        }
        try {
            File(target).printWriter().use { writer ->
                for (line in result) {
                    writer.println(line) // Записываем каждую строку в файл
                }
            }
        } catch (e: IOException) { println("Ошибка при записи в файл: ${e.message}") }
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java NIO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileNIO(source: String, target: String) {
        var reader: BufferedReader? = null
        var writer: BufferedWriter? = null
        try {
            reader = BufferedReader(FileReader(source))
            writer = BufferedWriter(FileWriter(target))
            var line: String
            while (reader.readLine().also { line = it } != null) {
                val numbers = line.split(" ").mapNotNull { it.toIntOrNull() }
                writer.write(numbers.average().toString())
                writer.write(" ")
            }
        } finally {
            try {
                reader?.close()
                writer?.close()
            } catch (e: IOException) {
                println("Ошибка при закрытии файла: ${e.message}")
            }
        }
    }
}
