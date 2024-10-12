package ru.tbank.education.school.lesson4

import org.junit.jupiter.api.Test
import java.io.BufferedWriter
import java.io.FileOutputStream
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.file.Files
import java.nio.file.Paths

class WriteFilesExamples {

    @Test
    fun `write file using BufferedWriter`() {
        BufferedWriter(FileWriter("src/test/resources/fileCreatedUsingBufferedWriter.txt"))
            .use { writer ->
                writer.write("Hello World!")
            }
    }

    @Test
    fun `append write file using BufferedWriter`() {
        BufferedWriter(FileWriter("src/test/resources/fileCreatedUsingBufferedWriter.txt", true))
            .use { writer ->
                writer.append("Hello World!")
            }
    }

    @Test
    fun `write file using PrintWriter`() {
        PrintWriter(FileWriter("src/test/resources/fileCreatedUsingBufferedWriter.txt"))
            .use { writer ->
                writer.println("Hello World!")
            }
    }

    @Test
    fun `write file using FileOutputStream`() {
        val fileOutputStream = FileOutputStream("src/test/resources/fileCreatedUsingFileOutputStream.txt")
        fileOutputStream.use {
            it.write("Hello World!".toByteArray())
        }
    }

    @Test
    fun `write file using NIO`() {
        val path = Paths.get("src/test/resources/fileWriteUsingNIO.txt")
        Files.write(path, "Hello World!".toByteArray())
    }
}
