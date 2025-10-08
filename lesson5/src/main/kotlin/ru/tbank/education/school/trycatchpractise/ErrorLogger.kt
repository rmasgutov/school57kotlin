package ru.tbank.education.school.trycatchpractise

import java.io.File

/**
 * Интерфейс для логирования ошибок в файл.
 */
interface SaveErrorLogger {

    /**
     * Логирует сообщение об ошибке в файл.
     *
     * @param message сообщение об ошибке
     * @param throwable исключение (если есть)
     * @return true, если запись успешна, иначе false
     */
    fun logError(message: String, throwable: Throwable?): Boolean
}


class ErrorLogger() : SaveErrorLogger {
    override fun logError(message: String, throwable: Throwable?): Boolean {
        try {
            val file = File.createTempFile("ErrorLogger", ".txt")
            file.writeText("$message ${throwable.toString()}")
            return true
        }
        catch (e: Exception) {
            return false
        }
    }
}