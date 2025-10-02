package ru.tbank.education.school.ru.tbank.education.school.lesson4.seminar.graph

fun main() {
    var graph: GraphList = GraphList();
    val n: Int = readln().toInt()
    for (i in 0 until n) { graph.addVertex(); }
    for (i in 0 until n) {
        val (u, v) = readln().split(" ").map { it.toInt() }
        graph.addEdge(u, v)
    }
    val start: Int = readln().toInt()
    for (i in 0 until n) {
        println(graph.length(start, i))
    }
    graph.print()
}