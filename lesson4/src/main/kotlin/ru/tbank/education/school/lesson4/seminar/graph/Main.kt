import kotlin.math.round
import kotlin.random.Random
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

fun main() {
    val rnd = Random(42)
    var vertexCount = 2000
    val edgeCount = vertexCount * vertexCount / 10
    var graphs: ArrayList<Pair<String, Graph>> = arrayListOf(
        "AdjacencyList" to AdjacencyListGraph(),
        "EdgeList" to EdgeListGraph(),
        "AdjacencyMatrix" to AdjacencyMatrixGraph(),
    )

    println("Random graph ($vertexCount vertexes, $edgeCount edges):")
    for ((name, graph) in graphs) {
        for (v in 0 until vertexCount) graph.addVertex(v)

        val edgeAddTime = measureTimeMillis {
            repeat(edgeCount) {
                try {
                    graph.addEdge(rnd.nextInt(vertexCount), rnd.nextInt(vertexCount))
                } catch (_: Exception) {
                }
            }
        }
        val times = mutableListOf<Long>()
        repeat(3) {
            val t = measureNanoTime { graph.bfs(0) }
            times.add(t)
        }

        println("${name}:\n\t edgeAddTime: ${edgeAddTime}ms, bfsTime: ${round(times.average() / 1_000_000)}ms")
    }

    println()

    vertexCount = 5_000
    graphs = arrayListOf(
        "AdjacencyList" to AdjacencyListGraph(),
        "EdgeList" to EdgeListGraph(),
        "AdjacencyMatrix" to AdjacencyMatrixGraph(),
    )

    println("Linear graph ($vertexCount vertexes, ${vertexCount - 1} edges):")
    for ((name, graph) in graphs) {
        for (v in 0 until vertexCount) graph.addVertex(v)

        val edgeAddTime = measureTimeMillis {
            for (v in 0 until (vertexCount - 1)) {
                graph.addEdge(v, v + 1)
            }
        }
        val times = mutableListOf<Long>()
        repeat(3) {
            val t = measureNanoTime { graph.bfs(0) }
            times.add(t)
        }

        println("${name}:\n\t edgeAddTime: ${edgeAddTime}ms, bfsTime: ${round(times.average() / 1_000_000)}ms")
    }
}
