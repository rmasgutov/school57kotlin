package ru.tbank.education.school

import ru.tbank.education.school.Analyzer.processFileIO
import ru.tbank.education.school.Analyzer.processFileNIO

fun main() {
    Average.processFileNIO("/Users/marianepomyaschaya/IdeaProjects/school57kotlin/lesson4/src/test/resources/numbers.txt", "/Users/marianepomyaschaya/IdeaProjects/school57kotlin/lesson4/src/test/resources/numbers_avg.txt")
}