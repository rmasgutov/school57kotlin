package seminar.tasks

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
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
        val coroutineNames = listOf("Coroutine-A", "Coroutine-B", "Coroutine-C")
        val jobs = coroutineNames.map { name ->
            launch(CoroutineName(name)) {
                println(name)
            }
        }
        jobs.joinAll()
        coroutineNames
    }
}
