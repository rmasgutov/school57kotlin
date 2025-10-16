package ru.tbank.education.school.trycatchpractise

import java.io.File

/**
 * Интерфейс для подсчёта строк в нескольких файлах.
 */
interface SaveMultiFileCounter {

    /**
     * Возвращает количество строк в каждом из указанных файлов.
     *
     * @param paths список путей к файлам
     * @return Map<String, Int>, где ключ — путь к файлу, значение — количество строк
     */
    fun countLinesInFiles(paths: List<String>): Map<String, Int>
}


class MultiFileCounter() : SaveMultiFileCounter {
    override fun countLinesInFiles(paths: List<String>): Map<String, Int> {
        var result = mutableMapOf<String, Int>()
        try {
            for (path in paths) {
                val file = File(path)
                result[path] = file.length().toInt()
            }
        } catch (e: Exception) {
            println("Error: ${e.message}")
        }
        return result
    }
}

fun main(args: Array<String>) {
    MultiFileCounter()
}