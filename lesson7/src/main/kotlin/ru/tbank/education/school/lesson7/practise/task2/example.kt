package ru.tbank.education.school.lesson7.practise.task2

/**
 * Функциональный декоратор `retry`
 *
 * Выполняет функцию повторно при выбрасывании исключения.
 *
 * @param times — количество попыток (включая первую)
 * @param delayMs — задержка между попытками (по умолчанию 0)
 * @param f — исходная функция, которую нужно обернуть
 *
 * @return новая функция, повторяющая вызов при ошибке
 */
fun <A, R> retry(
    times: Int,
    delayMs: Long = 0,
    f: (A) -> R
): (A) -> R = { a ->
    (1..times).asSequence()
        .map { attempt ->
            runCatching { f(a) }
                .onFailure {
                    println("Ошибка (${it.message}), попытка $attempt/$times")
                    if (delayMs > 0) Thread.sleep(delayMs)
                }
        }
        .firstOrNull { it.isSuccess }
        ?.getOrThrow()
        ?: throw IllegalStateException("Не удалось выполнить после $times попыток")
}

fun unstableOperation(id: Int): String {
    if (Math.random() < 0.9)
        error("Случайный сбой")
    return "Результат для $id"
}

fun main() {
    val safeOperation = retry(times = 2, delayMs = 500, ::unstableOperation)
    try {
        val result = safeOperation(42)
        println("Успех: $result")
    } catch (e: Exception) {
        println("Не удалось выполнить после всех попыток: ${e.message}")
    }
}