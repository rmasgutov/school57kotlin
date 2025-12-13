package seminar.tasks

import kotlinx.coroutines.runBlocking

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
        TODO("Реализуйте многопоточный загрузчик изображений")
    }

    /**
     * Вспомогательная функция для загрузки одного файла
     */
    suspend fun downloadFile(url: String, outputDir: String): DownloadResult {
        TODO("Реализуйте загрузку одного файла")
    }
}
