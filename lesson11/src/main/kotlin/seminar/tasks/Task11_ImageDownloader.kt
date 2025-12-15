package seminar.tasks

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.File
import java.net.URL
import java.util.concurrent.atomic.AtomicInteger

/**
 * Задание 12. Многопоточный загрузчик изображений
 *
 * Напишите программу, которая параллельно скачивает изображения из интернета.
 *
 * Требования:
 * 1. Использовать корутины с Dispatchers.IO
 * 2. Скачать изображения по указанным URL
 * 3. Сохранить в указанную папку
 * 4. Вывести прогресс: "Downloaded 1/10", "Downloaded 2/10", ...
 * 5. В конце вернуть статистику
 */
object ImageDownloader {

    /**
     * Результат загрузки изображения
     */
    data class DownloadResult(
        val url: String,
        val filename: String,
        val sizeBytes: Long,
        val success: Boolean,
        val errorMessage: String? = null
    )

    /**
     * Статистика загрузки
     */
    data class DownloadStats(
        val totalFiles: Int,
        val successfulDownloads: Int,
        val failedDownloads: Int,
        val totalBytes: Long,
        val elapsedTimeMs: Long
    )

    /**
     * @param urls список URL для загрузки
     * @param outputDir директория для сохранения файлов
     * @param onProgress callback для отображения прогресса (downloaded, total)
     * @return статистика загрузки
     */
    fun run(
        urls: List<String>,
        outputDir: String,
        onProgress: (Int, Int) -> Unit = { _, _ -> }
    ): DownloadStats = runBlocking {
        if (urls.isEmpty()) {
            return@runBlocking DownloadStats(0, 0, 0, 0, 0)
        }

        val startTime = System.currentTimeMillis()
        val downloadedCount = AtomicInteger(0)
        val total = urls.size

        // Создаем директорию если её нет
        File(outputDir).mkdirs()

        val deferreds = urls.map { url ->
            async {
                val result = downloadFile(url, outputDir)
                val count = downloadedCount.incrementAndGet()
                onProgress(count, total)
                println("Downloaded $count/$total")
                result
            }
        }

        val results = deferreds.awaitAll()
        val elapsedTime = System.currentTimeMillis() - startTime

        val successful = results.count { it.success }
        val failed = results.count { !it.success }
        val totalBytes = results.filter { it.success }.sumOf { it.sizeBytes }

        DownloadStats(
            totalFiles = total,
            successfulDownloads = successful,
            failedDownloads = failed,
            totalBytes = totalBytes,
            elapsedTimeMs = elapsedTime
        )
    }

    /**
     * Вспомогательная функция для загрузки одного файла
     */
    suspend fun downloadFile(url: String, outputDir: String): DownloadResult {
        return withContext(Dispatchers.IO) {
            try {
                val filename = "image_${System.nanoTime()}.jpg"
                val file = File(outputDir, filename)

                val connection = URL(url).openConnection()
                connection.connectTimeout = 10000
                connection.readTimeout = 10000

                connection.getInputStream().use { input ->
                    file.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }

                DownloadResult(
                    url = url,
                    filename = filename,
                    sizeBytes = file.length(),
                    success = true
                )
            } catch (e: Exception) {
                DownloadResult(
                    url = url,
                    filename = "",
                    sizeBytes = 0,
                    success = false,
                    errorMessage = e.message
                )
            }
        }
    }
}
