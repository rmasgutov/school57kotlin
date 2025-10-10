package ru.tbank.education.school.homework

/**
 * Интерфейс для подсчёта строк и слов в файле.
 */
interface FileAnalyzer {

    /**
     * Считает количество строк и слов в указанном входном файле и записывает результат в выходной файл.
     *
     * Словом считается последовательность символов, разделённая пробелами,
     * табуляциями или знаками перевода строки. Пустые части после разделения не считаются словами.
     *
     * @param inputFilePath путь к входному текстовому файлу
     * @param outputFilePath путь к выходному файлу, в который будет записан результат
     * @return true если операция успешна, иначе false
     */
    fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean
}
class NIOFileAnalyzer : FileAnalyzer {
    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        try {
            val inputFile = File(inputFilePath)
            val outputFile = File(outputFilePath)
            val ls = Files.readAllLines(Paths.get(inputFilePath))
            val lc = ls.size
            var wc = 0
            for (l in ls) {
                val ws = l.split(" ")
                for (w in ws) {
                    if (w.isNotBlank()) {
                        wc++
                    }
                }
            }
            val res = "Общее количество строк: $lc \n Общее количество слов: $wc"
            Files.write(Paths.get(outputFilePath), res.toByteArray())
            return true
        } catch (e: Exception) {
            throw e
            return false
        }
    }
}