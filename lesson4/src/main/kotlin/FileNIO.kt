package ru.tbank.education.school

import java.io.FileNotFoundException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

class FileNIO(name: String, needCreate : Boolean = false, needClear: Boolean = false) : FileInteraction(name, needCreate, needClear) {
    init {
        if (needCreate) {
            createFile()
        }
    }

    override fun writeFile(content: String) {
        try {
            if (!Files.exists(Paths.get(name))) {
                throw FileNotFoundException("Файл $name не найден")
            }
            Files.write(Paths.get(name), content.toByteArray())
        } catch (e : Exception) {
            println(e)
        }
    }

    override fun appendFile(content: String) {
        try {
            if (!Files.exists(Paths.get(name))) {
                throw FileNotFoundException("Файл $name не найден")
            }
            Files.write(Paths.get(name), (content + '\n').toByteArray(), StandardOpenOption.APPEND)
        } catch (e : Exception) {
            println(e)
        }
    }

    override fun readFile() : MutableList<List<String>> {
        val data = mutableListOf<List<String>>()

        try {
            if (!Files.exists(Paths.get(name))) {
                throw FileNotFoundException("Файл $name не найден")
            }
            val lines = Files.readAllLines(Paths.get(name))
            for (line in lines) {
                data.add(line.split(" "))
            }
        }
        catch (e : Exception) {
            println(e)
        }
        return data
    }

    override fun createFile() {
        try {
            Files.createFile(Paths.get(name))
            if (needClear) {
                writeFile("")
            }
        } catch (e : Exception) {
            println(e)
        }
    }

}