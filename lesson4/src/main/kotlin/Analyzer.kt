package ru.tbank.education.school

/**
 * Класс для анализа содержимого файла.
 */
object Analyzer {
    private fun analyzeLines(lines: List<String>): String {
        val words = lines.flatMap { it.split(" ").map(String::lowercase) }
        val uniqueWords = words.toSet()
        val averageWordLength = words.map(String::length).average()

        return """
           Общее количество строк: ${lines.size}
           Общее количество слов: ${words.size}
           Уникальные слова: ${uniqueWords.size}
           Средняя длина слов: $averageWordLength
        """.trimIndent()
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java IO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileIO(source: String, target: String) {
        Utils.operateFileIO(source, target, this::analyzeLines)
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java NIO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileNIO(source: String, target: String) {
        Utils.operateFileNIO(source, target, this::analyzeLines)
    }
}
