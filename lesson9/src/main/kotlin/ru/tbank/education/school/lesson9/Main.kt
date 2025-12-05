package ru.tbank.education.school.lesson9

import java.io.File
import java.io.FileOutputStream
import java.util.Random

fun main() {
    val file = File("qwerty.txt")
    FileOutputStream(file).use{ output ->
        val buffer = ByteArray(1073741824)
        val cnt = 0
        while (cnt < 1073741824) {
            Random(0).nextBytes(buffer)
            cnt += buffer.size
            output.write(buffer)
        }

    }
}

private fun FileOutputStream.use(function: Any) {
    TODO("Not yet implemented")
}

