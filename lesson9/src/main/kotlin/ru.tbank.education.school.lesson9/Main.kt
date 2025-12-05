package ru.tbank.education.school.lesson9
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random

fun main() {
        val file = File("qwerty")
        FileOutputStream(file).use{ output->
            val buffer = ByteArray(  8000)
            Random(0).nextBytes(buffer)
            output.write(buffer)
        }
}