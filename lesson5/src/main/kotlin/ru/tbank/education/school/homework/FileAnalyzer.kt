package ru.tbank.education.school.homework

import java.io.*
import java.nio.charset.Charset
import java.nio.file.*

interface FileAnalyzer {

    fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean
}

class IOFileAnalyzer : FileAnalyzer {

    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        var lineCount = 0L
        var wordCount = 0L

        return try {
            val input = File(inputFilePath)
            val output = File(outputFilePath)

            if (!input.exists() || input.isDirectory) {
                println("Файл не найден или является директорией: $inputFilePath")
                return false
            }

            if (output.exists() && output.isDirectory) {
                println("Путь для вывода указывает на директорию: $outputFilePath")
                return false
            }

            output.parentFile?.let { parent ->
                if (!parent.exists() && !parent.mkdirs()) {
                    println("Не удалось создать директорию для вывода: ${parent.absolutePath}")
                    return false
                }
            }

            BufferedReader(FileReader(input)).use { reader ->
                var line: String?
                while (true) {
                    line = reader.readLine() ?: break
                    lineCount++
                    if (line!!.isNotEmpty()) {
                        wordCount += line.trim().split("\\s+".toRegex()).count { it.isNotEmpty() }
                    }
                }
            }

            BufferedWriter(FileWriter(output, false)).use { writer ->
                writer.write("Общее количество строк: $lineCount\n")
                writer.write("Общее количество слов: $wordCount\n")
            }

            true
        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
            false
        }
    }
}

class NIOFileAnalyzer(
    private val charset: Charset = Charsets.UTF_8
) : FileAnalyzer {

    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        val inputPath = Path.of(inputFilePath)
        val outputPath = Path.of(outputFilePath)
        var lineCount = 0L
        var wordCount = 0L

        return try {
            if (!Files.exists(inputPath) || Files.isDirectory(inputPath)) {
                println("Файл не найден или является директорией: $inputFilePath")
                return false
            }

            if (Files.exists(outputPath) && Files.isDirectory(outputPath)) {
                println("Путь для вывода указывает на директорию: $outputFilePath")
                return false
            }

            outputPath.parent?.let { parent ->
                if (!Files.exists(parent)) Files.createDirectories(parent)
            }

            Files.newBufferedReader(inputPath, charset).use { reader ->
                var line: String?
                while (true) {
                    line = reader.readLine() ?: break
                    lineCount++
                    if (!line.isNullOrEmpty()) {
                        wordCount += line.trim().split("\\s+".toRegex()).count { it.isNotEmpty() }
                    }
                }
            }

            val resultText = "Общее количество строк: $lineCount\nОбщее количество слов: $wordCount\n"
            Files.writeString(
                outputPath,
                resultText,
                charset,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING
            )

            true
        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
            false
        }
    }
}