package ru.tbank.education.school

import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.io.FileNotFoundException

object Analyzer {
    fun processFileIO(source: String, target: String) {
        try{
            val a = File(source)
            val b = File(target)
            if (!a.exists()){throw FileNotFoundException("файл для ввода не найден")
            }
            if (!b.exists()){throw FileNotFoundException("файл для вывода не найден")
            }
            val list = a.readLines()
            var sum:Int = 0
            var sum2:Int = 0
            var s:MutableSet<String> = mutableSetOf()
            for(i in list){
                val words = i.split(" ")
                sum += words.size
                for(j in words){sum2+=j.length
                s.add(j)}
            }
            b.writeText("Общее количество строк: ${list.size.toString()}\nОбщее количество слов: ${sum.toString()}\nУникальные слова: ${s.size.toString()}\nСредняя длина слов: ${(sum2/sum).toString()}\n")

        }catch(e: IOException){
            println("Произошла ошибка: ${e.message}")
        }
    }

    fun processFileNIO(source: String, target: String) {
        val a = Paths.get(source)
            val b = Paths.get(target)
            if (!Files.exists(a)){throw FileNotFoundException("файл для ввода не найден")
            }
            if (!Files.exists(b)){throw FileNotFoundException("файл для вывода не найден")
            }
        try{
            val list = Files.readAllLines(a)
            var sum:Int = 0
            var sum2:Int = 0
            var s:MutableSet<String> = mutableSetOf()
            for(i in list){
                val words = i.split(" ")
                sum += words.size
                for(j in words){sum2+=j.length
                    s.add(j)}
            }
            Files.writeString(b,"Общее количество строк: ${list.size.toString()}\nОбщее количество слов: ${sum.toString()}\nУникальные слова: ${s.size.toString()}\nСредняя длина слов: ${(sum2/sum).toString()}\n")

        }catch(e: Exception){
            println("Произошла ошибка: ${e.message}")
        }
    }
}
