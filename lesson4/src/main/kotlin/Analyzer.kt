package ru.tbank.education.school


object Analyzer {
    //second edition
    fun processFileIO(source: String, target: String) {
        val input = Work_with_file(source)
        val output = Work_with_file(target)
        val cnt_lines: Int = input.read().size
        val cnt_words: Int = input.read().map {
            it.split(" ").size
        }.sum()
        val cnt_words_uniq = input.read().map {
            it.split(" ").map {
                it.uppercase().filter {
                    it in "1234567890QWERTYUIOPASDFGHJKLZXCVBNM"
                }
            }
        }.toSet().size
        val average_length = input.read().map {
            it.split(" ").map {
                it.uppercase().filter {
                    it in "1234567890QWERTYUIOPASDFGHJKLZXCVBNM"
                }.length
            }.sum()
        }.sum().toFloat() / cnt_words
        val out: String = "Общее количество строк: $cnt_lines \nОбщее количество слов: $cnt_words \n" +
                "Уникальные слова: $cnt_words_uniq \nСредняя длина слов: $average_length"
        output.write(out)
    }


    fun processFileNIO(source: String, target: String) {
        val input = Work_with_file(source, 2)
        val output = Work_with_file(target, 2)
        val cnt_lines: Int = input.read().size
        val cnt_words: Int = input.read().map {
            it.split(" ").size
        }.sum()
        val cnt_words_uniq = input.read().map {
            it.split(" ").map {
                it.uppercase().filter {
                    it in "1234567890QWERTYUIOPASDFGHJKLZXCVBNM"
                }
            }
        }.toSet().size
        val average_length = input.read().map {
            it.split(" ").map {
                it.uppercase().filter {
                    it in "1234567890QWERTYUIOPASDFGHJKLZXCVBNM"
                }.length
            }.sum()
        }.sum().toFloat() / cnt_words
        val out: String = "Общее количество строк: $cnt_lines \nОбщее количество слов: $cnt_words \n" +
                "Уникальные слова: $cnt_words_uniq \nСредняя длина слов: $average_length"
        output.write(out)
    }
}
