package ru.tbank.education.school.lesson4.seminar.graph.tests

import ru.tbank.education.school.lesson4.seminar.graph.Graph

fun createSimpleConnectedGraph(graphType: Graph, vertexCount: Int): Graph {
    require(vertexCount > 0) { "Количество вершин должно быть положительным" }

    val graph = graphType

    for (i in 0 until vertexCount) {
        graph.addVertex("V$i")
    }

    for (i in 1 until vertexCount) {
        graph.addEdge("V${i-1}", "V$i", 1.0)
    }

    return graph
}

fun createBranchedGraph(graphType: Graph, vertexCount: Int, maxBranching: Int = 3): Graph {
    require(vertexCount > 0) { "Количество вершин должно быть положительным" }

    val graph = graphType
    val availableVertices = mutableListOf<String>()

    graph.addVertex("V0")
    availableVertices.add("V0")

    var vertexIndex = 1

    while (vertexIndex < vertexCount && availableVertices.isNotEmpty()) {
        val parent = availableVertices.random()

        val remainingVertices = vertexCount - vertexIndex
        val branchCount = minOf((1..maxBranching).random(), remainingVertices)

        for (i in 0 until branchCount) {
            if (vertexIndex >= vertexCount) break

            val newVertex = "V$vertexIndex"
            graph.addVertex(newVertex)
            graph.addEdge(parent, newVertex, 1.0)

            availableVertices.add(newVertex)
            vertexIndex++
        }

        if (availableVertices.size > 1) {
            availableVertices.remove(parent)
        }
    }

    return graph
}