package ru.tbank.education.school.lesson4

import org.junit.jupiter.api.Test
import java.io.File
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.FileAlreadyExistsException

class CreateFilesExamples {

    @Test
    fun `create file using IO`() {
        val file = File("src/test/resources/fileCreatedUsingIO.txt")
        file.createNewFile()
    }

    @Test
    fun `create file using NIO`() {
        val path = Paths.get("src/test/resources/fileCreatedUsingNIO.txt")
        try {
            Files.createFile(path)
        } catch (ignored: FileAlreadyExistsException) { }
    }

    @Test
    fun `create file using IO OutputStream`() {
        FileOutputStream("src/test/resources/fileCreatedUsingOutputStream.txt")
            .use { }
    }
}
