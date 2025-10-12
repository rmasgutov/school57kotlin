package ru.tbank.education.school.lesson6

/**
 * Интерфейс для простого логгера, который хранит и управляет записями логов.
 */
interface Logger {

    /**
     * Добавляет новую запись в лог.
     * @param level Уровень логирования (например, INFO, WARN, ERROR)
     * @param message Текст сообщения
     * @param source Источник лога (например, имя класса или модуля)
     */
    fun log(level: LogLevel, message: String, source: String = "Unknown")

    /**
     * Возвращает все сохранённые логи.
     */
    fun getAllLogs(): List<LogEntry>

    /**
     * Возвращает логи определённого уровня (например, только ошибки).
     */
    fun getLogsByLevel(level: LogLevel): List<LogEntry>

    /**
     * Возвращает логи, записанные после указанного времени.
     */
    fun getLogsAfter(timestamp: Long): List<LogEntry>

    /**
     * Очищает все записи логов.
     */
    fun clear()
}

/**
 * Уровни логирования.
 */
enum class LogLevel {
    DEBUG, INFO, WARN, ERROR
}

/**
 * Модель одной записи лога.
 */
data class LogEntry(
    val timestamp: Long,
    val level: LogLevel,
    val message: String,
    val source: String
)