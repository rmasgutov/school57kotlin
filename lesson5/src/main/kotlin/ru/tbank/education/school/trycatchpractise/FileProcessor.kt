package ru.tbank.education.school.trycatchpractise

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