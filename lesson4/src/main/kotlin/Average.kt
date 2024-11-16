package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
object Average {
    fun processFileIO(source: String, target: String) {
        try{
            val a = File(source)
            val b = File(target)
            if (!a.exists()){throw FileNotFoundException("файл для ввода не найден")
            }
            if (!b.exists()){throw FileNotFoundException("файл для вывода не найден")}
            val list = a.readLines()
            for(i in list){
                val nums = i.split(" ").map{it.toInt()}
                if(nums.size != 0){val ans = nums.sum()/nums.size
                b.writeText(ans.toString())
                b.writeText("\n")}
            }
        }catch(e: Exception){
            println("Произошла ошибка: ${e.message}")
        }
    }
    fun processFileNIO(source: String, target: String) {
        try{
            val a = Paths.get(source)
            val b = Paths.get(target)
            if (!Files.exists(a)){throw FileNotFoundException("файл для ввода не найден")}
            if (!Files.exists(b)){throw FileNotFoundException("файл для вывода не найден")}
            val list = Files.readAllLines(a)
            for(i in list){
                val nums = i.split(" ").map{it.toInt()}
                if(nums.size!=0){val ans = nums.sum()/nums.size
                    Files.writeString(b, ans.toString())
                    Files.writeString(b, "\n")}
            }
        }catch(e: Exception){
            println("Произошла ошибка:${e.message}")
        }
    }
}
