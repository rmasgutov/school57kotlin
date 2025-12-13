package seminar

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import seminar.tasks.ImageDownloader
import java.nio.file.Path
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Тесты для Task12: Многопоточный загрузчик изображений
 */
class ImageDownloaderTest {

    @Test
    fun `should return correct stats for successful downloads`(@TempDir tempDir: Path) {
        val urls = listOf(
            "https://picsum.photos/200/300?random=1",
            "https://picsum.photos/200/300?random=2",
            "https://picsum.photos/200/300?random=3"
        )

        var progressCalls = 0
        val stats = ImageDownloader.run(urls, tempDir.toString()) { downloaded, total ->
            progressCalls++
            println("Progress: $downloaded/$total")
        }

        assertEquals(3, stats.totalFiles)
        assertTrue(stats.successfulDownloads >= 0)
        assertTrue(stats.failedDownloads >= 0)
        assertEquals(3, stats.successfulDownloads + stats.failedDownloads)
        assertTrue(stats.elapsedTimeMs >= 0)
    }

    @Test
    fun `should handle empty url list`(@TempDir tempDir: Path) {
        val stats = ImageDownloader.run(emptyList(), tempDir.toString())

        assertEquals(0, stats.totalFiles)
        assertEquals(0, stats.successfulDownloads)
        assertEquals(0, stats.failedDownloads)
    }

    @Test
    fun `should handle invalid urls gracefully`(@TempDir tempDir: Path) {
        val urls = listOf(
            "https://invalid-url-that-does-not-exist.com/image.jpg"
        )

        val stats = ImageDownloader.run(urls, tempDir.toString())

        assertEquals(1, stats.totalFiles)
        assertEquals(1, stats.failedDownloads)
    }
}
