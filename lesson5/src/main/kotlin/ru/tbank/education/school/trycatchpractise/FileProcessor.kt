package ru.tbank.education.school.trycatchpractise

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

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


class FileProcessorImpl() : FileProcessor {
    override fun readFile(path: String): List<String>? {
        try {
            val file = File(path)
        }
        catch (e: FileNotFoundException) {}
        catch (e: IOException) {}
        return File(path).readLines()
    }
}