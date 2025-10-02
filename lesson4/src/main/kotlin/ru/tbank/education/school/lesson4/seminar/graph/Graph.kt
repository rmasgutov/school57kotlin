package ru.tbank.education.school.lesson4.seminar.graph

interface Graph {
    fun addVertex(vertex: String)

    fun addEdge(vertex1: String, vertex2: String, weight: Double)

    fun bfs(startVertex: String): List<String>

    fun printGraph()
}


class AdjacencyListGraph : Graph {
    private val adjacencyList = mutableMapOf<String, MutableList<Pair<String, Double>>>()

    override fun addVertex(vertex: String) {
        if (!adjacencyList.containsKey(vertex)) {
            adjacencyList[vertex] = mutableListOf()
        }
    }

    override fun addEdge(vertex1: String, vertex2: String, weight: Double) {
        addVertex(vertex1)
        addVertex(vertex2)

        adjacencyList[vertex1]?.add(Pair(vertex2, weight))
        adjacencyList[vertex2]?.add(Pair(vertex1, weight))
    }

    override fun bfs(startVertex: String): List<String> {
        if (!adjacencyList.containsKey(startVertex)) return emptyList()

        val visited = mutableSetOf<String>()
        val result = mutableListOf<String>()
        val queue = mutableListOf<String>()

        visited.add(startVertex)
        queue.add(startVertex)

        while (queue.isNotEmpty()) {
            val current = queue.removeAt(0)
            result.add(current)

            val neighbors = adjacencyList[current]?.map { it.first } ?: emptyList()

            for (neighbor in neighbors) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor)
                    queue.add(neighbor)
                }
            }
        }

        return result
    }

    override fun printGraph() {
        println("Списки смежности:")
        adjacencyList.forEach { (vertex, neighbors) ->
            print("$vertex: ")
            neighbors.forEach { (neighbor, weight) ->
                print("-> $neighbor($weight) ")
            }
            println()
        }
    }
}


class AdjacencyMatrixGraph : Graph {
    private val vertices = mutableListOf<String>()
    private val matrix = mutableListOf<MutableList<Double>>()

    override fun addVertex(vertex: String) {
        if (vertex !in vertices) {
            vertices += vertex
            matrix.forEach { it += 0.0 }
            matrix += MutableList(vertices.size) { 0.0 }
        }
    }

    override fun addEdge(vertex1: String, vertex2: String, weight: Double) {
        addVertex(vertex1)
        addVertex(vertex2)

        val index1 = vertices.indexOf(vertex1)
        val index2 = vertices.indexOf(vertex2)

        matrix[index1][index2] = weight
        matrix[index2][index1] = weight
    }

    override fun bfs(startVertex: String): List<String> {
        if (!vertices.contains(startVertex)) return emptyList()

        val visited = BooleanArray(vertices.size)
        val result = mutableListOf<String>()
        val queue = mutableListOf<Int>()

        val startIndex = vertices.indexOf(startVertex)
        visited[startIndex] = true
        queue.add(startIndex)

        while (queue.isNotEmpty()) {
            val CurrentIndex = queue.removeAt(0)
            result.add(vertices[CurrentIndex])

            for (i in vertices.indices) {
                if (matrix[CurrentIndex][i] > 0 && !visited[i]) {
                    visited[i] = true
                    queue.add(i)
                }
            }

        }
        return result
    }

    override fun printGraph() {
        println("Матрица смежности:")
        print("    ")
        vertices.forEach { print("$it ") }
        println()

        vertices.forEachIndexed { i, vertex ->
            print("$vertex: ")
            matrix[i].forEach { weight ->
                print("$weight ")
            }
            println()
        }
    }
}


class EdgeListGraph : Graph {
    private val edges = mutableListOf<Triple<String, String, Double>>()
    private val vertices = mutableSetOf<String>()

    override fun addVertex(vertex: String) {
        vertices.add(vertex)
    }

    override fun addEdge(vertex1: String, vertex2: String, weight: Double) {
        addVertex(vertex1)
        addVertex(vertex2)
        if (vertex1 != vertex2) {
            edges.add(Triple(vertex1, vertex2, weight))
        }
    }

    override fun bfs(startVertex: String): List<String> {
        if (!vertices.contains(startVertex)) return emptyList()

        val visited = mutableSetOf<String>()
        val result = mutableListOf<String>()
        val queue = mutableListOf<String>()

        visited.add(startVertex)
        queue.add(startVertex)

        while (queue.isNotEmpty()) {
            val current = queue.removeAt(0)
            result.add(current)

            val neighbors = edges
                .filter { it.first == current || it.second == current }
                .map { if (it.first == current) it.second else it.first }
                .filter { it != current }
                .toSet()

            neighbors.forEach { neighbor ->
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor)
                    queue.add(neighbor)
                }
            }
        }
        return result
    }

    override fun printGraph() {
        println("Список рёбер:")
        println("Вершины: ${vertices.joinToString()}")
        println("Рёбра:")
        edges.forEach { (v1, v2, weight) ->
            println("$v1 -- $v2 ($weight)")
        }
    }
}