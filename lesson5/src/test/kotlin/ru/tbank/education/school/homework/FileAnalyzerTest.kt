package ru.tbank.education.school.homework

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.File
import java.nio.file.Files

abstract class FileAnalyzerTest {
    abstract val analyzer: FileAnalyzer

    lateinit var tempInputFile: File
    lateinit var tempOutputFile: File

    @BeforeEach
    fun setUp() {
        tempInputFile = Files.createTempFile("input", ".txt").toFile()
        tempOutputFile = Files.createTempFile("output", ".txt").toFile()
    }

    @AfterEach
    fun tearDown() {
        // Удаляем временные файлы, если они существуют
        if (tempInputFile.exists()) tempInputFile.delete()
        if (tempOutputFile.exists()) tempOutputFile.delete()
    }

    @Test
    fun `should count lines and words correctly`() {
        tempInputFile.writeText(
            """
            Hello world
            This is a test
            Kotlin is great
            """.trimIndent()
        )

        val result = analyzer.countLinesAndWordsInFile(tempInputFile.absolutePath, tempOutputFile.absolutePath)

        assertTrue(result)
        val expected = """
            Общее количество строк: 3
            Общее количество слов: 9
        """.trimIndent()
        assertEquals(expected, tempOutputFile.readText().trim())
    }

    @Test
    fun `should handle empty file`() {
        tempInputFile.writeText("")

        val result = analyzer.countLinesAndWordsInFile(tempInputFile.absolutePath, tempOutputFile.absolutePath)

        assertTrue(result)
        val expected = """
            Общее количество строк: 0
            Общее количество слов: 0
        """.trimIndent()
        assertEquals(expected, tempOutputFile.readText().trim())
    }

    @Test
    fun `should handle multiple spaces`() {
        tempInputFile.writeText("Hello   world   Kotlin")

        val result = analyzer.countLinesAndWordsInFile(tempInputFile.absolutePath, tempOutputFile.absolutePath)

        assertTrue(result)
        val expected = """
            Общее количество строк: 1
            Общее количество слов: 3
        """.trimIndent()
        assertEquals(expected, tempOutputFile.readText().trim())
    }

    @Test
    fun `should handle tabs`() {
        tempInputFile.writeText("Hello\tworld\tKotlin")

        val result = analyzer.countLinesAndWordsInFile(tempInputFile.absolutePath, tempOutputFile.absolutePath)

        assertTrue(result)
        val expected = """
            Общее количество строк: 1
            Общее количество слов: 3
        """.trimIndent()
        assertEquals(expected, tempOutputFile.readText().trim())
    }

    @Test
    fun `should handle mixed whitespace`() {
        tempInputFile.writeText("Hello   \tworld\nKotlin   test\tend")

        val result = analyzer.countLinesAndWordsInFile(tempInputFile.absolutePath, tempOutputFile.absolutePath)

        assertTrue(result)
        val expected = """
            Общее количество строк: 2
            Общее количество слов: 5
        """.trimIndent()
        assertEquals(expected, tempOutputFile.readText().trim())
    }

    @Test
    fun `should ignore empty parts after splitting`() {
        tempInputFile.writeText("   Hello   \t\t\n\nworld  ")

        val result = analyzer.countLinesAndWordsInFile(tempInputFile.absolutePath, tempOutputFile.absolutePath)

        assertTrue(result)
        val expected = """
            Общее количество строк: 4
            Общее количество слов: 2
        """.trimIndent()
        assertEquals(expected, tempOutputFile.readText().trim())
    }

    @Test
    fun `should handle empty lines`() {
        tempInputFile.writeText(
            """
            First line
            
            Third line
            """.trimIndent()
        )

        val result = analyzer.countLinesAndWordsInFile(tempInputFile.absolutePath, tempOutputFile.absolutePath)

        assertTrue(result)
        val expected = """
            Общее количество строк: 3
            Общее количество слов: 4
        """.trimIndent()
        assertEquals(expected, tempOutputFile.readText().trim())
    }

    @Test
    fun `should return false if input file does not exist`() {
        val result = analyzer.countLinesAndWordsInFile("nonexistent.txt", tempOutputFile.absolutePath)

        assertFalse(result)
    }

    @Test
    fun `should return false if input is a directory`() {
        val inputDir = Files.createTempDirectory("input_dir").toFile()
        val result = analyzer.countLinesAndWordsInFile(inputDir.absolutePath, tempOutputFile.absolutePath)

        assertFalse(result)

        inputDir.delete()
    }

    @Test
    fun `should return false if output directory does not exist`() {
        tempInputFile.writeText("Hello world")
        val output = File("/nonexistent/path/output.txt")

        val result = analyzer.countLinesAndWordsInFile(tempInputFile.absolutePath, output.absolutePath)

        assertFalse(result)
    }

    @Test
    fun `should return false if output is a directory`() {
        tempInputFile.writeText("Hello world")
        val outputDir = Files.createTempDirectory("output_dir").toFile()

        val result = analyzer.countLinesAndWordsInFile(tempInputFile.absolutePath, outputDir.absolutePath)

        assertFalse(result)
        outputDir.delete()
    }
}