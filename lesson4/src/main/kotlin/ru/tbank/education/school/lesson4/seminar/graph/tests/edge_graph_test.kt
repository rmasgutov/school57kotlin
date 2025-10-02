package ru.tbank.education.school.ru.tbank.education.school.lesson4.seminar.graph.tests

import ru.tbank.education.school.lesson4.seminar.graph.EdgeListGraph
import ru.tbank.education.school.lesson4.seminar.graph.tests.createBranchedGraph
import ru.tbank.education.school.lesson4.seminar.graph.tests.createSimpleConnectedGraph

import kotlin.system.measureTimeMillis

fun testEdgeListGraphBFS() {
    println("=== Тестирование BFS для EdgeListGraph ===")

    println("\n1. Маленький граф (100 вершин):")
    val smallGraph = createSimpleConnectedGraph(EdgeListGraph(), 100)

    val smallTime = measureTimeMillis {
        val result = smallGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${smallTime}ms")

    println("\n2. Средний граф (10000 вершин):")
    val mediumGraph = createSimpleConnectedGraph(EdgeListGraph(), 1000)

    val mediumTime = measureTimeMillis {
        val result = mediumGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${mediumTime}ms")

    println("\n3. Большой граф (10000 вершин):")
    val largeGraph = createSimpleConnectedGraph(EdgeListGraph(), 10000)

    val largeTime = measureTimeMillis {
        val result = largeGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${largeTime}ms")

    println("\n4. Ветвящийся граф (10000 вершин):")
    val branchedGraph = createBranchedGraph(EdgeListGraph(), 10000, 10)

    val branchedTime = measureTimeMillis {
        val result = branchedGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${branchedTime}ms")
}

fun main() {
    testEdgeListGraphBFS()
}