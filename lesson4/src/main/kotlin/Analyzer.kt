package ru.tbank.education.school

import java.io.*
import java.nio.file.*

object Analyzer {

    fun processFileIO(s: String, t: String) {
        try {
            val r = BufferedReader(FileReader(s))
            val ls = mutableListOf<String>()
            var l: String?

            while (true) {
                l = r.readLine()
                if (l == null) break
                ls.add(l)
            }
            r.close()

            var wc = 0
            var tl = 0
            val aw = mutableListOf<String>()

            for (ln in ls) {
                val ws = ln.split(" ")
                for (w in ws) {
                    if (w.isNotEmpty()) {
                        wc++
                        tl += w.length
                        aw.add(w.lowercase())
                    }
                }
            }

            val uw = aw.toSet()
            val al = if (wc > 0) tl.toDouble() / wc else 0.0

            val w = BufferedWriter(FileWriter(t))
            w.write("Общее количество строк: ${ls.size}\n")
            w.write("Общее количество слов: $wc\n")
            w.write("Уникальные слова: ${uw.size}\n")
            w.write("Средняя длина слов: %.2f\n".format(al))
            w.close()

            println("Файл обработан через IO: $t")
        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
        }
    }

    fun processFileNIO(s: String, t: String) {
        try {
            val ip = Path.of(s)
            val op = Path.of(t)
            val ls = Files.readAllLines(ip)

            var wc = 0
            var tl = 0
            val aw = mutableListOf<String>()

            for (ln in ls) {
                val ws = ln.split(" ")
                for (w in ws) {
                    if (w.isNotEmpty()) {
                        wc++
                        tl += w.length
                        aw.add(w.lowercase())
                    }
                }
            }

            val uw = aw.toSet()
            val al = if (wc > 0) tl.toDouble() / wc else 0.0

            val rs = listOf(
                "Общее количество строк: ${ls.size}",
                "Общее количество слов: $wc",
                "Уникальные слова: ${uw.size}",
                "Средняя длина слов: %.2f".format(al)
            )

            Files.write(op, rs)
            println("Файл обработан через NIO: $t")
        } catch (e: Exception) {
            println("Ошибка: ${e.message}")
        }
    }
}

fun main() {
    val fIn = "text.txt"
    val fOutIO = "text_result_io.txt"
    val fOutNIO = "text_result_nio.txt"

    val txt = """
        На улице мрачно
        Температура около 10 градусов
        хочу каникулы
    """.trimIndent()

    java.io.File(fIn).writeText(txt)

    println("Входной файл создан: $fIn")

    Analyzer.processFileIO(fIn, fOutIO)
    Analyzer.processFileNIO(fIn, fOutNIO)

    val ioR = java.io.File(fOutIO).readText()
    val nioR = java.io.File(fOutNIO).readText()

    println("\nРезультаты:")
    println("IO:\n$ioR")
    println("NIO:\n$nioR")
}

