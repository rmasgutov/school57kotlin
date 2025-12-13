package seminar.tasks

import kotlinx.coroutines.runBlocking

/**
 * Задание 11. withContext
 *
 * Используя withContext(Dispatchers.IO), прочитайте содержимое файлов параллельно
 * и объедините результаты.
 */
object WithContextIO {

    /**
     * @param filePaths список путей к файлам
     * @return Map<String, String> где ключ - путь к файлу, значение - его содержимое
     */
    fun run(filePaths: List<String>): Map<String, String> = runBlocking {
        TODO("Реализуйте параллельное чтение файлов с withContext")
    }
}
