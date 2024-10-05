package ru.tbank.education.school.lesson1

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.nio.file.Paths
import java.nio.file.Files
import java.nio.file.Path
import java.util.Scanner
import java.io.PrintWriter
import java.io.BufferedWriter
import java.io.FileWriter

fun main() {
//    создание файла
//1    val file = File("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json")
//    file.createNewFile()
//2    val path: Path = Paths.get("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config2.json")
//    Files.createFile(path)
//3    FileOutputStream("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config3.json")
//    чтение файла
//1    val file = File("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json")
//    val stream = FileInputStream(file)
//    val bytes = stream.readAllBytes()
//    val string = String(bytes)
//    println("Content: $string")
//2   val data1 = Files.readAllLines(Paths.get("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json"))
//    data1.forEach { println(it) }
//3    val data2 = Files.lines(Paths.get("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json"))
//    data2.forEach { println(it) }
//4    val scanner = Scanner(Paths.get("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json"))
//    scanner.useDelimiter(" ")
//    println(scanner.nextLine())
//    запись в файл
//1    val writer = BufferedWriter(FileWriter("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json", true))
//    writer.append("ПРивет")
//    writer.close()
//2    val writer = PrintWriter(FileWriter("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json", true))
//    writer.append("ПР")
//    writer.close()
//3    val writer = FileOutputStream("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json", true)
//    writer.write("ПРO".toByteArray())
//    writer.close()
//4    val path = Paths.get(c)
//    Files.write(path, "ПРO".toByteArray()) нет append
//    перемещение файлов
//1    val sourse = File("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json")
//    sourse.renameTo(File("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config4.json"))
//2    val sourse = Path.get("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config.json")
//    sourse.move(File("lesson1/src/main/kotlin/ru/tbank/education/school/lesson1/config4.json"))
//    обход директории
1    val paths = Files.walk(Paths.get("lesson1/src"))
    paths.forEach { println(it.fileName) }
//2    val paths = Files.walkFileTree(Paths.get("lesson1/src"), )
//    paths.forEach { println(it.fileName) }

}