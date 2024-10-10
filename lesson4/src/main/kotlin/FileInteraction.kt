package ru.tbank.education.school

abstract class FileInteraction(val name : String, val needCreate : Boolean, val needClear : Boolean) {
    abstract fun writeFile(content: String)

    abstract fun appendFile(content: String)

    abstract fun readFile() : MutableList<List<String>>

    abstract fun createFile()

    open fun clear() {
        writeFile("")
    }
}
