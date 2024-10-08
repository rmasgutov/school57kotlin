package ru.tbank.education.school

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
        val inputFile = FileIO(source)
        val data = inputFile.readFile()
        val outputFile = FileIO(target)
        outputFile.clear()

        for (test in data) {
            outputFile.appendFile((test.map { it.toInt() }.average()).toString())
        }
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java NIO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileNIO(source: String, target: String) {
        TODO()
    }
}
