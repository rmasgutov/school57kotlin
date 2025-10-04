package ru.tbank.education.school.trycatchpractise

/**
 * Интерфейс для логирования ошибок в файл.
 */
interface ErrorLogger {

    /**
     * Логирует сообщение об ошибке в файл.
     *
     * @param message сообщение об ошибке
     * @param throwable исключение (если есть)
     * @return true, если запись успешна, иначе false
     */
    fun logError(message: String, throwable: Throwable?): Boolean
}