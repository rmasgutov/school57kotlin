package ru.tbank.education.school.ru.tbank.education.school.lesson4.seminar.graph.tests

import ru.tbank.education.school.lesson4.seminar.graph.AdjacencyMatrixGraph
import ru.tbank.education.school.lesson4.seminar.graph.tests.createBranchedGraph
import ru.tbank.education.school.lesson4.seminar.graph.tests.createSimpleConnectedGraph

import kotlin.system.measureTimeMillis

fun testAdjacencyMatrixGraphBFS() {
    println("=== Тестирование BFS для AdjacencyMatrixGraph ===")

    println("\n1. Маленький граф (100 вершин):")
    val smallGraph = createSimpleConnectedGraph(AdjacencyMatrixGraph(), 100)

    val smallTime = measureTimeMillis {
        val result = smallGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${smallTime}ms")

    println("\n2. Средний граф (1000 вершин):")
    val mediumGraph = createSimpleConnectedGraph(AdjacencyMatrixGraph(), 1000)

    val mediumTime = measureTimeMillis {
        val result = mediumGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${mediumTime}ms")

    println("\n3. Большой граф (5000 вершин):")
    val largeGraph = createSimpleConnectedGraph(AdjacencyMatrixGraph(), 5000)

    val largeTime = measureTimeMillis {
        val result = largeGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${largeTime}ms")

    println("\n4. Ветвящийся граф (3000 вершин):")
    val branchedGraph = createBranchedGraph(AdjacencyMatrixGraph(), 3000, 10)

    val branchedTime = measureTimeMillis {
        val result = branchedGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${branchedTime}ms")
}

fun main() {
    testAdjacencyMatrixGraphBFS()
}