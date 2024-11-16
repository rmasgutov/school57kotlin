package ru.tbank.education.school.lesson2

import java.io.File
import java.io.FileOutputStream

fun main() {
    val file = File("lesson2/src/main/kotlin/ru/tbank/education/school/lesson2/config3.json")
    file.createNewFile()

    //val path:Path = Paths.get("lesson2/src/main/kotlin/ru/tbank/education/school/lesson2/config2.json")
    //Files.createFile(path)

    val fileOutputStream = FileOutputStream("lesson2/src/main/kotlin/ru/tbank/education/school/lesson2/config3.json")
    fileOutputStream.write("jfdsfjkdfjkdsfjdks".toByteArray())
    fileOutputStream.close()
    val fileOutputStream1 = FileOutputStream("lesson2/src/main/kotlin/ru/tbank/education/school/lesson2/config.json")
    fileOutputStream1.write("Ахахахахх зато ты Ангелина III  которая как только проснулась сразу у нее по расписанию нежанье в своей сорокоэтажной кроватке с готовым чемоданчиком выживалова на случай атаки со стороны Израиля и США, с винтовой лестницей в пролете которой расположен 120метровый бакал из нефрита, роданита, сулинита, флююрита, рубина, топаза, алмаза, самородков золота и серебра внутри которого лежит сейф с платининовыми и золотыми слитками поверх которых фонтаном льется вино, шампанское, шоколадные фонтан, ангелы сверху подносят завтрак состоящих из 40 блюд разных стран мира, облачко окутывает твое нежное тельце и сдувает всякую пылинку что посмела дотронуться до самого величества захары морковны малютки балютки анабельки сосалочки ебланочки".toByteArray())
    fileOutputStream1.close()
}