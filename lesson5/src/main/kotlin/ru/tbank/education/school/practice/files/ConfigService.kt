package ru.tbank.education.school.practice.files

/**
 * Задание:
 *
 * Класс ConfigService должен:
 * 1. Чтение всех строк конфигурационного файла по указанному пути.
 * 2. Гарантированно закрывать файл после чтения, даже если произошло исключение.
 * 3. При ошибке чтения выбрасывать IOException наружу.
 *
 * В текущей реализации есть баг: BufferedReader не закрывается при IOException.
 * Ваша задача исправить класс так, чтобы ресурс закрывался корректно.
 */
import java.io.BufferedReader
import java.io.Closeable
import java.io.File
import java.io.FileReader
import java.io.IOException

class ConfigService(private val path: String) {

    fun readConfig(): List<String> {
        val file = File(path)
        val reader = BufferedReader(FileReader(file))
        val lines = mutableListOf<String>()
        reader.use {
            try {
                var line = reader.readLine()
                while (line != null) {
                    lines.add(line)
                    line = reader.readLine()
                }
            } catch (ex: IOException){
                println("Ошибка IO: ${ex.message}")
            }
        }
        return lines
    }
}

class Simple(val id: String): Closeable{
    override fun close() {
        println("Закрыли ресурс")
    }
}

//fun main(){
//    val simple = Simple("123")
//    println("Получили ресурс")
//    simple.use {
//        println("Делаем что-то важное")
//    }
//}