package seminar.tasks

import kotlinx.coroutines.runBlocking

/**
 * Задание 8. Первая корутина
 *
 * Используя runBlocking и launch, запустите 3 корутины,
 * каждая из которых выводит своё имя 5 раз с delay(500).
 */
object CoroutineLaunch {

    /**
     * @return список имён корутин в порядке их запуска
     */
    fun run(): List<String> = runBlocking {
        TODO("Реализуйте запуск корутин с launch")
    }
}
