package ru.tbank.education.school.lesson7.practise.task2

/**
 * Реализуй декоратор limitRate(intervalMs: Long, f: (A) -> R): (A) -> R?
 *
 * Возвращает новую функцию, которая:
 *  - Выполняет f() не чаще, чем один раз в intervalMs миллисекунд.
 *  - Если вызов происходит раньше, чем истёк интервал, возвращает null (или сообщение об отказе).
 *
 * Пример:
 * val printMessage = limitRate(1000L) { msg: String ->
 *     println("[$msg] выполнено в ${System.currentTimeMillis()}")
 *     msg
 * }
 *
 * printMessage("A") // выполняется
 * Thread.sleep(500)
 * printMessage("B") // пропускается (слишком рано)
 * Thread.sleep(600)
 * printMessage("C") // выполняется
 */
fun <A, R> limitRate(intervalMs: Long, f: (A) -> R): (A) -> R? {
    TODO()
}


/**
 * Реализуй декоратор safeCall(f: (A) -> R): (A) -> Result<R>
 *
 * Возвращает новую функцию, которая при вызове:
 *  - Выполняет исходную функцию f(a)
 *  - Если выполнение успешно, возвращает Result.success(result)
 *  - Если выбрасывается исключение, перехватывает его и возвращает Result.failure(e)
 *
 * Пример:
 * val riskyDivide = { n: Int -> 10 / n }
 * val safeDivide = safeCall(riskyDivide)
 *
 * println(safeDivide(2))  // Success(5)
 * println(safeDivide(0))  // Failure(java.lang.ArithmeticException: / by zero)
 */
fun <A, R> safeCall(f: (A) -> R): (A) -> Result<R> {
    TODO()
}

/**
 * Реализуй декоратор logCalls(name: String, f: (A) -> R): (A) -> R
 *
 * Возвращает новую функцию, которая:
 *  - Перед вызовом f(a) печатает сообщение: "[name] вызвана с аргументом: a"
 *  - После вызова печатает: "[name] вернула результат: r"
 *  - Возвращает результат работы исходной функции.
 *
 * Пример:
 * val loggedAdd = logCalls("add") { n: Int -> n + 10 }
 *
 * println(loggedAdd(5))
 *
 * Вывод:
 * [add] вызвана с аргументом: 5
 * [add] вернула результат: 15
 * 15
 */
fun <A, R> logCalls(name: String, f: (A) -> R): (A) -> R {
    TODO()
}


/**
 * Ретрай-обёртка.
 *
 * Реализуй функцию retry(times: Int, f: () -> T): () -> T
 * Возвращает новую функцию, которая при вызове выполняет f().
 * Если f() выбрасывает исключение, повторяет попытку до times раз.
 * После исчерпания попыток — пробрасывает исключение дальше.
 *
 * Пример:
 * var attempts = 0
 * val unstable = { if (++attempts < 3) error("fail") else "ok" }
 * val safe = retry(5, unstable)
 * println(safe()) // ok
 */
fun <T> retry(times: Int, f: () -> T): () -> T {
    TODO()
}

/**
 * Таймер-декоратор.
 *
 * Реализуй функцию timed(name: String, f: (A) -> R): (A) -> R
 * Возвращает новую функцию, которая:
 * 1) Засекает время выполнения f().
 * 2) Выводит в консоль "[name] выполнено за X мс".
 * 3) Возвращает результат работы f().
 *
 * Пример:
 * val slowFn = timed("Slow sum") { n: Int ->
 *     Thread.sleep(200)
 *     n * 2
 * }
 * println(slowFn(10))
 */
fun <A, R> timed(name: String, f: (A) -> R): (A) -> R {
    TODO()
}

/**
 * Мемоизация с ограниченным размером кэша.
 *
 * Реализуй функцию memoizeWith
 * Возвращает декоратор, который кеширует результаты вызова функции f.
 * Если количество элементов в кеше превышает capacity,
 * нужно удалить самый старый элемент (FIFO).
 *
 * Подсказка:
 * Для кеша используйте такую конструкцию
 * ```
 * val cache = object : LinkedHashMap<A, R>(capacity, 0.75f, true) {
 *         override fun removeEldestEntry(eldest: MutableMap.MutableEntry<A, R>?): Boolean {
 *             return size > capacity
 *         }
 *     }
 * ```
 *
 */
fun <A, R> memoizeWith(capacity: Int, f: (A) -> R): (A) -> R {
    TODO()
}