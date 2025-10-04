package ru.tbank.education.school.trycatchpractise

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