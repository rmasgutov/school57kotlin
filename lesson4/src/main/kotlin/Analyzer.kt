package ru.tbank.education.school

import java.io.*

/**
 * Класс для анализа содержимого файла.
 */
object Analyzer {
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
        var numberOfLines = 0
        var numberOfWords = 0
        var count = mutableSetOf<String>()
        var lengthOfText = 0
        try {
            val lines = File(source).readLines()
            numberOfLines = lines.size
            for (line in lines) {
                val words = line.split(" ").mapNotNull { it.toString() }
                numberOfWords += words.size
                for (word in words) {
                    count.add(word.lowercase())
                    lengthOfText += word.length
                }
            }
        } catch (e: Exception) {
            println("Ошибка при чтении файла: ${e.message}")
        }
        val averageLength = lengthOfText / numberOfWords
        try {
            File(target).printWriter().use { writer ->
                println("Общее количество строк: $numberOfLines")
                println("Общее количество слов: $numberOfWords")
                println("Уникальные слова: ${count.size}")
                println("Средняя длина слов: $averageLength")
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
            var numberOfLines = 0
            var numberOfWords = 0
            var count = mutableSetOf<String>()
            var lengthOfText = 0
            var line: String
            while (reader.readLine().also { line = it } != null) {
                numberOfLines++
                val words = line.split(" ").mapNotNull { it.toString() }
                numberOfWords += words.size
                for (word in words) {
                    count.add(word.lowercase())
                    lengthOfText += word.length
                }
            }
            val averageLength = lengthOfText / numberOfWords
            writer.write("Общее количество строк: $numberOfLines")
            writer.newLine()
            writer.write("Общее количество слов: $numberOfWords")
            writer.newLine()
            writer.write("Уникальные слова: ${count.size}")
            writer.newLine()
            writer.write("Средняя длина слов: $averageLength")
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
