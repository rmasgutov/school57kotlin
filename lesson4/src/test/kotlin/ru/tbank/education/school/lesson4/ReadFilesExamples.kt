package ru.tbank.education.school.lesson4

import org.junit.jupiter.api.Test
import java.io.*
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*


class ReadFilesExamples {

    @Test
    fun `read file using input stream`() {
        javaClass.classLoader.getResourceAsStream("data.txt")
            .use { inputStream ->
                println("input stream: " + readFromInputStream(inputStream!!))
            }
    }

    @Test
    fun `read file using file input stream`() {
        val file = File(javaClass.classLoader.getResource("data.txt")!!.file)
        FileInputStream(file).use { fileInputStream ->
            println("file input stream 2: " + readFromInputStream(fileInputStream))
        }
    }

    @Test
    fun `read file using buffered reader`() {
        javaClass.classLoader.getResourceAsStream("data.txt")
            .use { inputStream ->
                BufferedReader(InputStreamReader(inputStream!!))
                    .use { bufferedReader ->
                        println("buffered reader: " + bufferedReader.readText())
                    }
            }
    }

    @Test
    fun `read file using NIO`() {
        val path = Paths.get(javaClass.classLoader.getResource("data.txt")!!.toURI())
        println("NIO:")
        Files.readAllLines(path)
            .forEach { line ->
                println(line)
            }
    }

    @Test
    fun `read file using NIO and stream`() {
        val path = Paths.get(javaClass.classLoader.getResource("data.txt")!!.toURI())
        println("NIO and stream:")
        Files.lines(path)
            .forEach { line ->
                println(line)
            }
    }

    @Test
    fun `read file using scanner`() {
        val path = Paths.get(javaClass.classLoader.getResource("data.txt")!!.toURI())
        println("Scanner:")
        Scanner(path)
            .use { scanner ->
                scanner.useDelimiter("\n")
                while (scanner.hasNext()) {
                    println(scanner.nextLine())
                }
            }
    }


    @Throws(IOException::class)
    private fun readFromInputStream(inputStream: InputStream): String {
        val resultStringBuilder = StringBuilder()
        BufferedReader(InputStreamReader(inputStream))
            .use { br
                ->
                var line: String?
                while ((br.readLine().also { line = it }) != null) {
                    resultStringBuilder.append(line).append("\n")
                }
            }
        return resultStringBuilder.toString()
    }
}
