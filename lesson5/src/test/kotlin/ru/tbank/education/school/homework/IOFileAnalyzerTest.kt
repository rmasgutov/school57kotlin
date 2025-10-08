package ru.tbank.education.school.homework

class IOFileAnalyzerTest : FileAnalyzerTest() {
    override val analyzer: FileAnalyzer = IOFileAnalyzer()
}