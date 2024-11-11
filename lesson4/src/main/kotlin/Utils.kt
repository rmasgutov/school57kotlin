package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class Utils {
    companion object {
        /**
         * Метод позволяет безопасно работать с файлами.
         * @param executable Исполняемый код.
         */
        private fun operateFile(executable: () -> Unit) {
            try {
                executable()
            } catch (e: FileNotFoundException) {
                println("File not found: ${e.message}")
            } catch (e: IOException) {
                println("Error while using file: ${e.message}")
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }

        /**
         * Метод позволяет писать в файл с помощью функции, опираясь на содержимое другого файла, используя java.io.
         * @param source путь до исходного файла.
         * @param target путь до файла с результатами.
         * @param transform функция, преобразующая содержимое входного файла в содержимое выходного.
         */
        fun operateFileIO(source: String, target: String, transform: (List<String>) -> String) {
            operateFile {
                File(target).writeText(
                    transform(File(source).readLines())
                )
            }
        }

        /**
         * Метод позволяет писать в файл с помощью функции, опираясь на содержимое другого файла, используя java.nio.
         * @param source путь до исходного файла.
         * @param target путь до файла с результатами.
         * @param transform функция, преобразующая содержимое входного файла в содержимое выходного.
         */
        fun operateFileNIO(source: String, target: String, transform: (List<String>) -> String) {
            operateFile {
                Files.write(
                    Paths.get(target),
                    transform(
                        Files.readAllLines(Paths.get(source))
                    ).toByteArray()
                )
            }
        }
    }
}