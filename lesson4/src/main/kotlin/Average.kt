package ru.tbank.education.school

/** Класс для вычисления среднего арифметического. */
object Average {
    /**
     * Метод рассчитывает среднее арифметическое для каждой строки из списка.
     * @param lines список строк.
     * @return Текст, содержащий в каждой строке подсчитанное среднее арифметическое.
     */
    private fun calcAverages(lines: List<String>): String {
        return lines.map {
            line -> line.split(" ").mapNotNull(String::toIntOrNull).average()
        }.joinToString("\n")
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами. Обработка
     * выполняется через Java IO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileIO(source: String, target: String) {
        Utils.operateFileIO(source, target, this::calcAverages)
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами. Обработка
     * выполняется через Java NIO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileNIO(source: String, target: String) {
        Utils.operateFileNIO(source, target, this::calcAverages)
    }
}
