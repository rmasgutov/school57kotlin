package ru.tbank.education.school

fun main() {
    Average.processFileIO("C:\\Users\\alisa\\IdeaProjects\\school57kotlin\\lesson4\\src\\test\\resources\\numbers.txt", "C:\\Users\\alisa\\IdeaProjects\\school57kotlin\\lesson4\\src\\test\\resources\\numbers_avg.txt")

    Average.processFileNIO("C:\\Users\\alisa\\IdeaProjects\\school57kotlin\\lesson4\\src\\test\\resources\\numbers.txt", "C:\\Users\\alisa\\IdeaProjects\\school57kotlin\\lesson4\\src\\test\\resources\\numbers_avg.txt")

    Analyzer.processFileIO("C:\\Users\\alisa\\IdeaProjects\\school57kotlin\\lesson4\\src\\test\\resources\\analyzer.txt", "C:\\Users\\alisa\\IdeaProjects\\school57kotlin\\lesson4\\src\\test\\resources\\1.txt")

    Analyzer.processFileNIO("C:\\Users\\alisa\\IdeaProjects\\school57kotlin\\lesson4\\src\\test\\resources\\analyzer.txt", "C:\\Users\\alisa\\IdeaProjects\\school57kotlin\\lesson4\\src\\test\\resources\\1.txt")
}