package ru.tbank.education.school

import java.io.*
import java.nio.file.*

object Average {
    fun processFileIO(source: String, target: String) {
        try {
            val reader = BufferedReader(FileReader(source))
            val writer = BufferedWriter(FileWriter(target))

            var line: String?

            while(true)
            {
                line=reader.readLine()
                if (line == null)
                    break
                val pl = line.split(" ")
                var sum = 0.0;
                var c = 0;
                for (p in pl) {
                    sum+= p.toInt()
                    c++
                }
                if (c > 0) {
                    val sa = sum / c;
                    writer.write(sa.toString())
                    writer.newLine()
                }
            }

            reader.close()
            writer.close()
        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
        }
    }


    fun processFileNIO(source: String, target: String) {
        try {
            val inp = Path.of(source)
            val outp = Path.of(target)
            val lines = Files.readAllLines(inp)
            val res = mutableListOf<String>()

            for(line in lines) {
                val pl = line.split(" ")
                var sum = 0.0;
                var c = 0;
                for (p in pl) {
                    sum += p.toInt()
                    c++
                }
                if (c > 0) {
                    val sa = sum / c;
                    res.add(sa.toString())
                }
            }
            Files.write(outp, res)
        } catch (e: Exception) {
            println("Оштбка: ${e.message}")
        }
    }
}


fun main() {
    val inputFile = "numbers.txt"
    val outputFileIO = "result_io.txt"
    val outputFileNIO = "result_nio.txt"

    val inputText = """
        1 2 3
        4 5 6
        10 20 30
    """.trimIndent()

    java.io.File(inputFile).writeText(inputText)

    println("Входной файл создан: $inputFile")

    Average.processFileIO(inputFile, outputFileIO)
    println("Результат через IO записан в $outputFileIO")

    Average.processFileNIO(inputFile, outputFileNIO)
    println("Результат через NIO записан в $outputFileNIO")

    val ioResult = java.io.File(outputFileIO).readText()
    val nioResult = java.io.File(outputFileNIO).readText()

    println("\nРезультаты:")
    println("IO :\n$ioResult")
    println("NIO:\n$nioResult")
}
