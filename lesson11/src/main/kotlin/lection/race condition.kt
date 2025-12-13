import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RaceConditionCoroutine {
    private var value = 0

    fun increment() {
        value++
    }

    fun decrement() {
        value--
    }

    fun getValue(): Int = value
}

fun main() = runBlocking {
    val raceCondition = RaceConditionCoroutine()

    // Запускаем 100 корутин, которые инкрементируют
    val incrementJobs = List(100) {
        launch(Dispatchers.Default) {
            repeat(1_000) {
                raceCondition.increment()
            }
        }
    }

    // Запускаем 100 корутин, которые декрементируют
    val decrementJobs = List(100) {
        launch(Dispatchers.Default) {
            repeat(1_000) {
                raceCondition.decrement()
            }
        }
    }

    // Ждем завершения всех корутин
    incrementJobs.forEach { it.join() }
    decrementJobs.forEach { it.join() }

    println("📊 Ожидаемое значение: 0")
    println("🎯 Фактическое значение: ${raceCondition.getValue()}")
}