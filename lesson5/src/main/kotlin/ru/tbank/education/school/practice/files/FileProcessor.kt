package ru.tbank.education.school.practice.files

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.NoSuchFileException
import java.nio.file.Paths

/**
 * Интерфейс для безопасной работы с текстовыми файлами.
 */
interface FileProcessor {

    /**
     * Читает все строки из файла и возвращает их список.
     *
     * Если файл не существует — метод должен поймать `FileNotFoundException` и вернуть пустой список.
     * Если возникает ошибка при чтении — метод должен поймать `IOException` и вернуть null.
     *
     * @param path путь к файлу
     * @return список строк файла, пустой список, если файла нет, или null при другой ошибке
     */
    fun readFile(path: String): List<String>?
}

class IOFileProcessor : FileProcessor {
    override fun readFile(path: String): List<String>? {
        val lines = mutableListOf<String>()
        var line: String?
        try {
            val file = File(path)
            file.bufferedReader().use { reader ->
                while (reader.readLine().also { line = it } != null) {
                    lines.add(line!!)
                }
            }
        } catch (ex: FileNotFoundException) {
            return lines
        } catch (ex: IOException) {
            return null
        }
        return lines
    }
}

class NIOFileProcessor : FileProcessor {
    override fun readFile(path: String): List<String>? {
        val path = Paths.get(path)
        try {
            return Files.readAllLines(path)
        } catch (ex: NoSuchFileException) {
            return emptyList()
        } catch (ex: IOException) {
            return null
        }
    }
}

//fun main() {
//    val path = "./lesson5/example.txt"
//    val ioProcessor = IOFileProcessor()
//    val nioProcessor = NIOFileProcessor()
//
//    val linesWithIo = ioProcessor.readFile(path)
//    val linesWithNio = nioProcessor.readFile(path)
//
//    println("Строки, прочитанные с помощью IO: $linesWithIo")
//    println("Строки, прочитанные с помощью NIO: $linesWithNio")
//}