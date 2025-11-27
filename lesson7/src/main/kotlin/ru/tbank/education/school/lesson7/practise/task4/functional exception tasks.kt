package ru.tbank.education.school.lesson7.practise.task4

/**
 * Задание 1. Безопасный парсинг Int
 *
 * Реализуй функцию `parseIntSafe(value: String): Result<Int>`, которая:
 *  - Преобразует строку в число.
 *  - Возвращает `Result.success`, если удалось.
 *  - Возвращает `Result.failure`, если возникло исключение (`NumberFormatException`).
 *
 * Подсказка:
 * - Используй `runCatching { ... }`
 *
 * Пример:
 * ```
 * val r1 = parseIntSafe("123")
 * println(r1.isSuccess) // true
 *
 * val r2 = parseIntSafe("abc")
 * println(r2.isFailure) // true
 * ```
 */
fun parseIntSafe(value: String): Result<Int> = TODO()


/**
 * Задание 2. Значение по умолчанию
 *
 * Реализуй функцию `parseWithDefault(input: String, default: Int): Int`, которая:
 *  - Возвращает число, если оно корректное.
 *  - Если произошла ошибка — возвращает `default`.
 *
 * Подсказка:
 * - Используй `runCatching` и `.getOrElse { ... }`.
 *
 * Пример:
 * ```
 * println(parseWithDefault("42", 0))  // 42
 * println(parseWithDefault("abc", 0)) // 0
 * ```
 */
fun parseWithDefault(input: String, default: Int): Int = TODO()


/**
 * Задание 3. Логирование через onFailure
 *
 * Реализуй функцию `safeDivideLogged(a: Int, b: Int): Result<Double>`, которая:
 *  - Делит `a` на `b`.
 *  - Если происходит ошибка, печатает сообщение:
 *    "Ошибка деления: ${e.message}"
 *  - Возвращает `Result` (не бросает исключение).
 *
 * Пример:
 * ```
 * safeDivideLogged(10, 0)
 * // Выводит: Ошибка деления: / by zero
 * ```
 */
fun safeDivideLogged(a: Int, b: Int): Result<Double> = TODO()