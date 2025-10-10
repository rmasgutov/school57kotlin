package ru.tbank.education.school.homework

import java.io.*
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.InvalidPathException
import java.nio.file.Path
import java.nio.file.Paths

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

class IOFileAnalyzer : FileAnalyzer {
    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        try {
            val inputFile = File(inputFilePath)

            if (!inputFile.exists()) {
                throw FileNotFoundException("Файл не найден: $inputFilePath")
            }
            if (!inputFile.canRead()) {
                throw SecurityException("Нет доступа для чтения файла: $inputFilePath")
            }

            var lineCount = 0
            var wordCount = 0

            BufferedReader(FileReader(inputFile)).use { reader ->
                var line = reader.readLine()
                while (line != null) {
                    lineCount++
                    val words = line.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }
                    wordCount += words.size
                    line = reader.readLine()
                }
            }

            val outputFile = File(outputFilePath)
            outputFile.parentFile?.let {
                if (!it.exists() && !it.mkdirs()) {
                    throw IOException("Не удалось создать директорию: ${it.path}")
                }
            }

            BufferedWriter(FileWriter(outputFile)).use { writer ->
                writer.write("Общее количество строк: $lineCount")
                writer.newLine()
                writer.write("Общее количество слов: $wordCount")
            }

            return true
        } catch (e: FileNotFoundException) {
            println("Ошибка: ${e.message}")
        } catch (e: SecurityException) {
            println("Ошибка доступа: ${e.message}")
        } catch (e: InvalidPathException) {
            println("Ошибка пути: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка ввода-вывода: ${e.message}")
        } catch (e: Exception) {
            println("Неожиданная ошибка: ${e.message}")
        }
        return false
    }
}

class NIOFileAnalyzer : FileAnalyzer {
    override fun countLinesAndWordsInFile(inputFilePath: String, outputFilePath: String): Boolean {
        try {
            val inputPath: Path = try {
                Paths.get(inputFilePath)
            } catch (e: InvalidPathException) {
                throw InvalidPathException(inputFilePath, "Неверный путь к входному файлу: ${e.message}")
            }

            if (!Files.exists(inputPath)) {
                throw FileNotFoundException("Файл не найден: $inputFilePath")
            }
            if (!Files.isReadable(inputPath)) {
                throw SecurityException("Нет доступа для чтения файла: $inputFilePath")
            }

            var lineCount = 0
            var wordCount = 0

            Files.newBufferedReader(inputPath, StandardCharsets.UTF_8).use { reader ->
                var line = reader.readLine()
                while (line != null) {
                    lineCount++
                    val words = line.trim().split(Regex("\\s+")).filter { it.isNotEmpty() }
                    wordCount += words.size
                    line = reader.readLine()
                }
            }

            val outputPath: Path = try {
                Paths.get(outputFilePath)
            } catch (e: InvalidPathException) {
                throw InvalidPathException(outputFilePath, "Неверный путь к выходному файлу: ${e.message}")
            }

            // Создаём директорию, если её нет
            outputPath.parent?.let { parent ->
                if (!Files.exists(parent)) {
                    Files.createDirectories(parent)
                }
            }

            Files.newBufferedWriter(outputPath, StandardCharsets.UTF_8).use { writer ->
                writer.write("Общее количество строк: $lineCount")
                writer.newLine()
                writer.write("Общее количество слов: $wordCount")
            }

            return true
        } catch (e: FileNotFoundException) {
            println("Ошибка: ${e.message}")
        } catch (e: InvalidPathException) {
            println("Ошибка пути: ${e.message}")
        } catch (e: SecurityException) {
            println("Ошибка доступа: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка ввода-вывода: ${e.message}")
        } catch (e: Exception) {
            println("Неожиданная ошибка: ${e.message}")
        }

        return false
    }
}
