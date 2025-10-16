interface Graph {
    fun addVertex(number: Int)
    fun addEdge(edge: Pair<Int, Int>)
    fun bfs(start: Int): MutableList<Int>

    fun print()
}
open class Graph(): Graph {
    override fun addVertex(number: Int) { /* уже реализовано выше */ }
    override fun addEdge(edge: Pair<Int, Int>) { /* уже реализовано выше */ }
    override fun bfs(start: Int): MutableList<Int> { return mutableListOf() }

    override fun print() {
        when (this) {
            is GraphAdjacencyMatrix -> {
                println("Adjacency Matrix:")
                adjacencyMatrix.forEach { row ->
                    println(row.joinToString(" ") { if (it) "1" else "0" })
                }
            }
            is GraphListsVertexs -> {
                println("Adjacency Lists:")
                for ((v, neighbors) in listsVertexs) {
                    println("$v -> ${neighbors.joinToString(", ")}")
                }
            }
            is GraphListEdge -> {
                println("Edge List:")
                listEdge.forEach { (u, v) -> println("($u, $v)") }
            }
            else -> println("Unknown graph type")
        }
    }
}

class GraphAdjacencyMatrix(val adjacencyMatrix: MutableList<MutableList<Boolean>>): Graph() {
    fun getGraph(): MutableList<MutableList<Boolean>> { return adjacencyMatrix }
}

class GraphListsVertexs(val listsVertexs: MutableMap<Int, MutableList<Int>>): Graph() {
    fun getGraph(): MutableMap<Int, MutableList<Int>> { return listsVertexs }
}

class GraphListEdge(val listEdge: MutableList<Pair<Int, Int>>): Graph() {
    fun getGraph(): MutableList<Pair<Int, Int>> { return listEdge }
}

fun main() {
    val gm = GraphAdjacencyMatrix(mutableListOf())
    gm.addVertex(0); gm.addVertex(1); gm.addVertex(2)
    gm.addEdge(0 to 1); gm.addEdge(1 to 2)
    println("AdjacencyMatrix BFS from 0: ${gm.bfs(0)}")
    gm.print()

    val gl = GraphListsVertexs(mutableMapOf())
    gl.addEdge(0 to 1)
    gl.addEdge(1 to 2)
    println("ListsVertexs BFS from 0: ${gl.bfs(0)}")
    gl.print()

    val ge = GraphListEdge(mutableListOf())
    ge.addVertex(0)
    ge.addVertex(1)
    ge.addEdge(0 to 1)
    println("ListEdge BFS from 0: ${ge.bfs(0)}")
    ge.print()
}
