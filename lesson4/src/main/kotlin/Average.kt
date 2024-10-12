package ru.tbank.education.school

import java.io.File

/** Класс для вычисления среднего арифметического. */
object Average {
    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами. Обработка
     * выполняется через Java IO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileIO(source: String, target: String) {
        val inputFile = File(javaClass.classLoader.getResource(source)!!.file)
        val outputFile = File(javaClass.classLoader.getResource(target)!!.file)
        for (test in inputFile.readLines()) {
            println(test)
        }
    }

    /**
     * Метод обрабатывает исходный файл и записывает результат в файл с результатами. Обработка
     * выполняется через Java NIO.
     * @param source путь до исходного файла.
     * @param target путь до файла с результатами.
     */
    fun processFileNIO(source: String, target: String) {
        TODO()
    }
}
