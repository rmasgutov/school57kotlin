package ru.tbank.education.school.trycatchpractise

import java.io.File
import java.io.FileInputStream
import java.io.IOException

/**
 * Интерфейс для копирования содержимого файлов.
 */
interface FileCopier {

    /**
     * Копирует содержимое из входного файла в выходной.
     *
     * @param sourcePath путь к исходному файлу
     * @param destPath путь к целевому файлу
     * @return true, если копирование прошло успешно, иначе false
     */
    fun copyFile(sourcePath: String, destPath: String): Boolean
}


class FileCopierImpl() : FileCopier {
    override fun copyFile(sourcePath: String, destPath: String): Boolean {
        try {
            val file = File(sourcePath)
            val output = File(destPath)
            output.writeText(file.readText())
            return true
        }
        catch (e: Exception) {}
        return false
    }
}