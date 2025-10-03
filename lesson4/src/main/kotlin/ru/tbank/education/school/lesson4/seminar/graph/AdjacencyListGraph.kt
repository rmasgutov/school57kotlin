class AdjacencyListGraph : Graph {
    private val adjacencyList: MutableMap<Int, MutableList<Int>> = HashMap()

    override fun addVertex(vertex: Int) {
        adjacencyList.getOrPut(vertex) { ArrayList() }
    }

    override fun addEdge(start: Int, finish: Int) {
        if (!adjacencyList.containsKey(start) || !adjacencyList.containsKey(finish))
            throw IllegalArgumentException("Some vertices do not exist!")

        adjacencyList[start]!!.add(finish)
    }

    override fun bfs(vertex: Int): List<Pair<Int, Int>> {
        val visited = mutableMapOf<Int, Int>().withDefault { 0 }
        val result = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Int>()

        if (!adjacencyList.containsKey(vertex)) return emptyList()

        queue.addLast(vertex)
        visited[vertex] = 1
        result[vertex] = 0

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            for (neighbor in adjacencyList[current]!!) {
                if (visited.getValue(neighbor) == 0) {
                    queue.addLast(neighbor)
                    visited[neighbor] = 1
                    result[neighbor] = result[current]!! + 1
                }
            }
        }

        return result.toList()
    }

    override fun print() {
        println("Vertices: ${adjacencyList.keys}")
        println("Edges:")
        adjacencyList.forEach { (v, neighbors) ->
            neighbors.forEach { n -> println("\t($v, $n)") }
        }
    }
}
