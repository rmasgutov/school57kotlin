package ru.tbank.education.school.lesson4.seminar.graph

import ru.tbank.education.school.lesson4.seminar.graph.tests.createBranchedGraph
import ru.tbank.education.school.lesson4.seminar.graph.tests.createSimpleConnectedGraph
import kotlin.system.measureTimeMillis

fun testAdjacencyListGraphBFS() {
    println("=== Тестирование BFS для AdjacencyListGraph ===")

    println("\n1. Маленький граф (100 вершин):")
    val smallGraph = createSimpleConnectedGraph(AdjacencyListGraph(), 100)

    val smallTime = measureTimeMillis {
        val result = smallGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${smallTime}ms")

    println("\n2. Средний граф (10000 вершин):")
    val mediumGraph = createSimpleConnectedGraph(AdjacencyListGraph(), 10000)

    val mediumTime = measureTimeMillis {
        val result = mediumGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${mediumTime}ms")

    println("\n3. Большой граф (100000 вершин):")
    val largeGraph = createSimpleConnectedGraph(AdjacencyListGraph(), 100000)

    val largeTime = measureTimeMillis {
        val result = largeGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${largeTime}ms")

    println("\n4. Ветвящийся граф (100000 вершин):")
    val branchedGraph = createBranchedGraph(AdjacencyListGraph(), 100000, 10)

    val branchedTime = measureTimeMillis {
        val result = branchedGraph.bfs("V0")
        println("BFS результат: ${result.take(10)}...")
        println("Размер: ${result.size}")
    }
    println("Время выполнения: ${branchedTime}ms")
}

fun main() {
    testAdjacencyListGraphBFS()
}