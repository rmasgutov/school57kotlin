package seminar.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File

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
        if (filePaths.isEmpty()) return@runBlocking emptyMap()

        val deferreds = filePaths.map { path ->
            async {
                path to withContext(Dispatchers.IO) {
                    File(path).readText()
                }
            }
        }

        deferreds.awaitAll().toMap()
    }
}
