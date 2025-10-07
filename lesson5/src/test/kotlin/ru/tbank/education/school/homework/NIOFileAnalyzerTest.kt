package ru.tbank.education.school.homework

class NIOFileAnalyzerTest : FileAnalyzerTest() {
    override val analyzer: FileAnalyzer = NIOFileAnalyzer()
}