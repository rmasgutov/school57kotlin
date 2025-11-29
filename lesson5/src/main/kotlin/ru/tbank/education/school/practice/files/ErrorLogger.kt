package ru.tbank.education.school.practice.files


import java.io.FileWriter
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

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

class IOErrorLogger(
    private val path: String
) : ErrorLogger {
    override fun logError(message: String, throwable: Throwable?): Boolean {
        try {
            val writer = FileWriter(/* fileName = */ path, /* append = */ true)
            writer.use {
                val message = "$message ${throwable?.message}\n"
                writer.append(message)
            }
            return true
        } catch (ex: IOException) {
            return false
        }
    }

}

class NIOErrorLogger(
    private val path: String
) : ErrorLogger {
    override fun logError(message: String, throwable: Throwable?): Boolean {
        try {
            val message = "$message ${throwable?.message}\n"
            Files.writeString(Paths.get(path), message, StandardOpenOption.APPEND)
            return true
        } catch (ex: IOException) {
            return false
        }
    }
}

//fun main() {
//    val path = "./lesson5/log.txt"
//    val ioErrorLogger = IOErrorLogger(path)
//    val nioErrorLogger = NIOErrorLogger(path)
//    try {
//        10 / 0
//    } catch (ex: Exception) {
//        ioErrorLogger.logError("Получили ошибку", ex)
//        nioErrorLogger.logError("Получили ошибку", ex)
//    }
//
//    try {
//        "STRING".toInt()
//    } catch (ex: Exception) {
//        ioErrorLogger.logError("Получили ошибку", ex)
//        nioErrorLogger.logError("Получили ошибку", ex)
//    }
//}