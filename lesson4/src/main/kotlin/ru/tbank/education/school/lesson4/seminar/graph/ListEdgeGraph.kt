import java.util.ArrayList
import java.util.HashSet


class ListEdgeGraph: Graph {

    private var edges: MutableList<Pair<Int, Int>> = ArrayList<Pair<Int,Int>>()
    private var verticies: MutableSet<Int> = HashSet<Int>()

    override fun addVertex(vertex: Int) {
        verticies.add(vertex)
    }
    override fun addEdge(start: Int, finish: Int) {
        if (!verticies.contains(start) || !verticies.contains(finish)) {
            throw IllegalArgumentException("Cannot add this edge because some of verticies does not exist!")
        }
        edges.add(Pair<Int, Int>(start, finish))
    }

    override fun bfs(vertex: Int): List<Pair<Int, Int>> {
        var qu = ArrayDeque<Int>()
        var visited: MutableMap<Int, Int> = HashMap<Int, Int>( )
        for (vertex in verticies) {
            visited[vertex] = 0
        }
        qu.addLast(vertex)
        visited[vertex] = 1
        var result: MutableMap<Int, Int> = HashMap<Int, Int>()

        result.put(vertex, 0)
        while (qu.isNotEmpty()) {
            val vertex = qu.removeFirst()
            for(edge in edges) {
                if (edge.first == vertex || edge.second == vertex) {
                    if (visited[edge.first] == 0) {
                        qu.add(edge.first)
                        visited[edge.first] = 1
                        result.put(edge.first, result[vertex]?.plus(1) ?: -1)
                    }
                    if (visited[edge.second] == 0) {
                        qu.add(edge.second)
                        visited[edge.second] = 1
                        result.put(edge.second, result[vertex]?.plus(1) ?: -1)
                    }
                }
            }
        }

        return result.toList()
    }

    override fun print() {
        for (vertex in verticies) {
            print("$vertex, ")
        }
        println()
        for (edge in edges) {
            println("(${edge.first}, ${edge.second})")
        }
    }
}