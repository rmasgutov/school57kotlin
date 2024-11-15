package ru.tbank.education.school


object Average {
    //second edition
    fun processFileIO(source: String, target: String) {
        Work_with_file(target).write(Work_with_file(source).read().map {
            it.split(" ").map {
                it.toInt()
            }.average().toString()
        }.toString().filter { it != '[' && it != ']' })
    }


    fun processFileNIO(source: String, target: String) {
        Work_with_file(target, 2).write(Work_with_file(source, 2).read().map {
            it.split(" ").map {
                it.toInt()
            }.average().toString()
        }.toString().filter { it != '[' && it != ']' })
    }
}
