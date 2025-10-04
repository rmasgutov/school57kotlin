package ru.tbank.education.school.trycatchpractise

/**
 * Интерфейс для подсчёта строк в нескольких файлах.
 */
interface MultiFileCounter {

    /**
     * Возвращает количество строк в каждом из указанных файлов.
     *
     * @param paths список путей к файлам
     * @return Map<String, Int>, где ключ — путь к файлу, значение — количество строк
     */
    fun countLinesInFiles(paths: List<String>): Map<String, Int>
}