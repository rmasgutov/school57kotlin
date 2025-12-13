package seminar

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.io.TempDir
import seminar.tasks.WithContextIO
import java.io.File
import java.nio.file.Path
import kotlin.test.assertEquals

/**
 * Тесты для Task11: withContext
 */
class WithContextIOTest {

    @Test
    fun `should read files in parallel`(@TempDir tempDir: Path) {
        // Создаем тестовые файлы
        val file1 = File(tempDir.toFile(), "file1.txt").apply { writeText("Content 1") }
        val file2 = File(tempDir.toFile(), "file2.txt").apply { writeText("Content 2") }
        val file3 = File(tempDir.toFile(), "file3.txt").apply { writeText("Content 3") }

        val filePaths = listOf(file1.absolutePath, file2.absolutePath, file3.absolutePath)

        val result = WithContextIO.run(filePaths)

        assertEquals(3, result.size)
        assertEquals("Content 1", result[file1.absolutePath])
        assertEquals("Content 2", result[file2.absolutePath])
        assertEquals("Content 3", result[file3.absolutePath])
    }

    @Test
    fun `should handle empty file list`() {
        val result = WithContextIO.run(emptyList())
        assertEquals(0, result.size)
    }
}
