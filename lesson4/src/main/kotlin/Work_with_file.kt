package ru.tbank.education.school


import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Files
import java.nio.file.Paths
import kotlin.RuntimeException
import kotlin.io.path.exists
import kotlin.io.path.readLines
import kotlin.io.path.writeBytes


class Work_with_file(val name: String, var type:Int = 1) {
    //second edition
    fun pr() {
        if (find(name)) {
            val file = File(name)
            val stream = FileInputStream(file)
            stream.use {
                val bytes = stream.readAllBytes()
                val string = String(bytes)
                print(string)
            }
        } else {
            throw RuntimeException("Такого файла не существует")
        }
    }


    fun write(content: String) {
        if (!find(name)) throw RuntimeException()
        if (type == 1) {
            val stream = FileOutputStream(name)
            stream.use {
                stream.write(content.toByteArray())
            }
            return
        }
        if (type == 2) {
            Paths.get(name).writeBytes(content.toByteArray())
            return
        }
        throw RuntimeException()
    }


    fun find(_name:String):Boolean{
        if (type == 1) return File(_name).exists()
        if (type == 2) return Paths.get(name).exists()
        throw RuntimeException()
    }


    fun read():List<String> {
        if (!find(name)) throw RuntimeException()
        if (type == 1) {
            val stream = FileInputStream(name)
            stream.use{
                return stream.bufferedReader().readLines()
            }
        }
        if (type == 2) return Paths.get(name).readLines()
        throw RuntimeException()
    }
}