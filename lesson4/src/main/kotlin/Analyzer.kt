package ru.tbank.education.school
import java.io.FileNotFoundException
import java.io.IOException
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
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
        try {
            var inputfileio = File(source)
            var listav: MutableList<Double> = mutableListOf()
            var lines = inputfileio.readLines()
            var g = 0
            var l: MutableList<String> = mutableListOf()
            var t = 0
            listav.add(lines.size.toDouble())
            for (i in lines) {
                g = g + i.split(" ").size
            }
            listav.add(g.toDouble())
            for (i in lines) {
                for (i2 in i.split(" ")) {
                    l.add(i2)
                }
            }
            listav.add(l.toSet().size.toDouble())
            for (i in l) {
                t = t + i.length
            }
            var u: Double = t / (l.size).toDouble()
            listav.add(u)
            var outputio = File(target)
            outputio.writeText("Общее количество строк: ${listav[0]}\n")
            outputio.writeText("Общее количество слов: ${listav[1]}\n")
            outputio.writeText("Уникальные слова: ${listav[2]}\n")
            outputio.writeText("Средняя длина слов: ${listav[3]}\n")
        } catch (e: FileNotFoundException) {
        println("Файл не обнаружен: ${e.message}")
    } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        } catch (e: Exception) {
        println("Неизвестная ошибка: ${e.message}")
    }
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами.
     * Обработка выполняется через Java NIO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileNIO(source: String, target: String) {
        try {
            var inputfileio = Paths.get(source)
            var listav: MutableList<Double> = mutableListOf()
            var lines = Files.readAllLines(inputfileio)
            var g = 0
            var l: MutableList<String> = mutableListOf()
            var t = 0
            listav.add(lines.size.toDouble())
            for (i in lines) {
                g = g + i.split(" ").size
            }
            listav.add(g.toDouble())
            for (i in lines) {
                for (i2 in i.split(" ")) {
                    l.add(i2)
                }
            }
            listav.add(l.toSet().size.toDouble())
            for (i in l) {
                t = t + i.length
            }
            var u: Double = t / (l.size).toDouble()
            listav.add(u)
            var outputio = File(target)
            outputio.appendText("Общее количество строк: ${listav[0]}\n")
            outputio.appendText("Общее количество слов: ${listav[1]}\n")
            outputio.appendText("Уникальные слова: ${listav[2]}\n")
            outputio.appendText("Средняя длина слов: ${listav[3]}\n")
        } catch (e: FileNotFoundException) {
            println("Файл не обнаружен: ${e.message}")
        } catch (e: IOException) {
            println("Ошибка при работе с файлами: ${e.message}")
        } catch (e: Exception) {
            println("Неизвестная ошибка: ${e.message}")
        }
    }
}
