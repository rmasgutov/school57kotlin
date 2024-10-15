package ru.tbank.education.school

import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths

class Utils {
    companion object {
        fun operateFileIO(source: String, target: String, transform: (List<String>) -> String) {
            try {
                File(target).writeText(
                    transform(File(source).readLines())
                )
            } catch (e: FileNotFoundException) {
                println("File not found: ${e.message}")
            } catch (e: IOException) {
                println("Error while using file: ${e.message}")
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }

        fun operateFileNIO(source: String, target: String, transform: (List<String>) -> String) {
            try {
                Files.write(
                    Paths.get(target),
                    transform(
                        Files.readAllLines(Paths.get(source))
                    ).toByteArray()
                )
            } catch (e: FileNotFoundException) {
                println("File not found: ${e.message}")
            } catch (e: IOException) {
                println("Error while using file: ${e.message}")
            } catch (e: Exception) {
                println("Error: ${e.message}")
            }
        }
    }
}