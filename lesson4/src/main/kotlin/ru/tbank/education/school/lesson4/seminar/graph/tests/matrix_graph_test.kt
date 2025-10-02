package ru.tbank.education.school.ru.tbank.education.school.lesson4.seminar.graph.tests

import ru.tbank.education.school.lesson4.seminar.graph.AdjacencyMatrixGraph
import ru.tbank.education.school.lesson4.seminar.graph.tests.createBranchedGraph
import ru.tbank.education.school.lesson4.seminar.graph.tests.createSimpleConnectedGraph

import kotlin.system.measureTimeMillis

fun testAdjacencyMatrixGraphBFS() {
    println("=== Тестирование BFS для AdjacencyMatrixGraph ===")

    println("\n1. Маленький граф (5 вершин):")
    val smallGraph = createSimpleConnectedGraph(AdjacencyMatrixGraph(), 5)
    smallGraph.printGraph()

    val smallTime = measureTimeMillis {
        val result = smallGraph.bfs("V0")
        println("BFS результат: $result")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${smallTime}ms")

    println("\n2. Средний граф (50 вершин):")
    val mediumGraph = createSimpleConnectedGraph(AdjacencyMatrixGraph(), 50)

    val mediumTime = measureTimeMillis {
        val result = mediumGraph.bfs("V0")
        println("BFS размер: ${result.size}")
        println("Первые 10: ${result.take(10)}")
    }
    println("Время выполнения: ${mediumTime}ms")

    println("\n3. Большой граф (200 вершин):")
    val largeGraph = createSimpleConnectedGraph(AdjacencyMatrixGraph(), 200)

    val largeTime = measureTimeMillis {
        val result = largeGraph.bfs("V0")
        println("BFS размер: ${result.size}")
    }
    println("Время выполнения: ${largeTime}ms")

    println("\n4. Ветвящийся граф (15 вершин):")
    val branchedGraph = createBranchedGraph(AdjacencyMatrixGraph(), 15, 2)
    branchedGraph.printGraph()

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