package ru.tbank.education.school

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
        val inputFile = FileIO(source)
        val outputFile = FileIO(target)
        outputFile.clear()

        val data = inputFile.readFile()
        outputFile.appendFile("Общее количество строк: ${data.size}")

        val words = mutableListOf<String>()
        val uniqueWords = mutableSetOf<String>()

        for (line in data) {
            for (word in line) {
                words.add(word)
                uniqueWords.add(word)
            }
        }

        outputFile.appendFile("Общее количество слов: ${words.size}")
        outputFile.appendFile("Уникальные слова: ${uniqueWords.size}")
        outputFile.appendFile("Средняя длина слов: ${words.map { it.length }.average()}")
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java NIO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileNIO(source: String, target: String) {
        val inputFile = FileNIO(source)
        val outputFile = FileNIO(target)
        outputFile.clear()

        val data = inputFile.readFile()
        outputFile.appendFile("Общее количество строк: ${data.size}")

        val words = mutableListOf<String>()
        val uniqueWords = mutableSetOf<String>()

        for (line in data) {
            for (word in line) {
                words.add(word)
                uniqueWords.add(word)
            }
        }

        outputFile.appendFile("Общее количество слов: ${words.size}")
        outputFile.appendFile("Уникальные слова: ${uniqueWords.size}")
        outputFile.appendFile("Средняя длина слов: ${words.map { it.length }.average()}")
    }
}
