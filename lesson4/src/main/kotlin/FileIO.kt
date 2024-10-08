package ru.tbank.education.school

import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException
import java.io.FileOutputStream

class FileIO(var name: String, needCreate : Boolean = false, val needClear: Boolean = false) {
    init {
        if (needCreate) {
            createFile()
        }
    }

    fun readFile() : MutableList<List<String>> {
        val file : File
        val data = mutableListOf<List<String>>()

        try {
            if (!File(name).exists()) {
                throw FileNotFoundException("Файл $name не найден")
            }
            file = File(name)
            val lines = file.readLines()
            for (line in lines) {
                data.add(line.split(" "))
            }
        }
        catch (e : Exception) {
            println(e)
        }
        return data
    }

    fun writeFile(content: String) {
        try {
            if (!File(name).exists()) {
                throw FileNotFoundException("Файл $name не найден")
            }
            val stream = FileOutputStream(name)
            stream.use {
                stream.write((content).toByteArray())
            }
        } catch (e : Exception) {
            println(e)
        }
    }

    fun appendFile(content: String) {
        try {
            if (!File(name).exists()) {
                throw FileNotFoundException("Файл $name не найден")
            }
            val stream = FileOutputStream(name, true)
            stream.use {
                stream.write((content + '\n').toByteArray())
            }
        } catch (e : Exception) {
            println(e)
        }
    }

    fun clear() {
        writeFile("")
    }

    fun createFile() {
        try {
            File(name).createNewFile()
            if (needClear) {
                writeFile("")
            }
        } catch (e : Exception) {
            println(e)
        }
    }
}