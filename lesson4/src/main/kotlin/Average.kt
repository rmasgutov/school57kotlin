package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

object Average {

    fun processFileIO(source: String, target: String) {
        try {
            val fin = File(source)
            val fout = File(target)
            val lines = fin.readLines()
            val meanLines = lines.map { line ->
                val nums = line.split(" ").map { it.toInt() }
                if (nums.isNotEmpty()) nums.sum() / nums.size
                else 0
            }
            fout.writeText(meanLines.joinToString("\n"))
        }
        catch (e: FileNotFoundException) {
            println("Такого файла не существует: ${e.message}")
        }
        catch (e: IOException) {
            println("Ошибка при работе с файлом(и): ${e.message}")
        }
    }

    fun processFileNIO(source: String, target: String) {
        try {
            val fin = Paths.get(source)
            val fout = Paths.get(target)
            val lines = Files.readAllLines(fin)
            val meanLines = lines.map { line ->
                val nums = line.split(" ").map { it.toInt() }
                if (nums.isNotEmpty()) nums.sum() / nums.size
                else 0
            }
            Files.write(fout, meanLines.joinToString("\n").toByteArray())
        }
        catch (e: FileNotFoundException) {
            println("Такого файла не существует: ${e.message}")
        }
        catch (e: IOException) {
            println("Ошибка при работе с файлом(и): ${e.message}")
        }
    }

}