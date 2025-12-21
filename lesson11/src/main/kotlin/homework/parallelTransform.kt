package homework
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

/**
 * Задание: Параллельное преобразование элементов списка с использованием async.
 *
 * Преобразуйте каждый элемент списка в отдельной корутине с помощью async.
 *
 * @param items список элементов для преобразования
 * @param transform функция преобразования
 * @return список преобразованных элементов в исходном порядке
 */
suspend fun <T, R> parallelTransform(
    items: List<T>,
    transform: suspend (T) -> R
): List<R> {
    val results = mutableListOf<R>()
    for (item in items) {
        results.add(transform(item))
    }
    return results
}