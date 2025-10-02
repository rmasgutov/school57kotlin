class EdgeListGraph : Graph {
    private val edges = mutableListOf<Pair<Int, Int>>()
    private val vertices = mutableSetOf<Int>()

    override fun addVertex(vertex: Int) {
        vertices.add(vertex)
    }

    override fun addEdge(start: Int, finish: Int) {
        if (!vertices.contains(start) || !vertices.contains(finish))
            throw IllegalArgumentException("Some vertices do not exist!")

        edges.add(Pair(start, finish))
    }

    override fun bfs(vertex: Int): List<Pair<Int, Int>> {
        val visited = vertices.associateWith { 0 }.toMutableMap()
        val result = mutableMapOf<Int, Int>()
        val queue = ArrayDeque<Int>()

        if (!vertices.contains(vertex)) return emptyList()

        queue.addLast(vertex)
        visited[vertex] = 1
        result[vertex] = 0

        while (queue.isNotEmpty()) {
            val current = queue.removeFirst()
            edges.forEach { (from, to) ->
                if (from == current && visited[to] == 0) {
                    queue.addLast(to)
                    visited[to] = 1
                    result[to] = result[current]!! + 1
                } else if (to == current && visited[from] == 0) {
                    queue.addLast(from)
                    visited[from] = 1
                    result[from] = result[current]!! + 1
                }
            }
        }

        return result.toList()
    }

    override fun print() {
        println("Vertices: $vertices")
        println("Edges:")
        edges.forEach { println("\t(${it.first}, ${it.second})") }
    }
}
