interface Graph {
    fun addVertex(vertex: Int)
    fun addEdge(start: Int, finish: Int)
    fun bfs(vertex: Int): List<Pair<Int, Int>>

    fun print()
}