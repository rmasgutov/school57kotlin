class AdjacencyMatrixGraph : Graph {
    private val verticesList = ArrayList<Int>()
    private val indexOf = HashMap<Int, Int>()
    private var matrix: Array<BooleanArray> = arrayOf()

    override fun addVertex(vertex: Int) {
        if (indexOf.containsKey(vertex)) return
        val newIndex = verticesList.size
        verticesList.add(vertex)
        indexOf[vertex] = newIndex

        val n = newIndex + 1
        val newMatrix = Array(n) { BooleanArray(n) { false } }
        for (i in 0 until newIndex) {
            System.arraycopy(matrix[i], 0, newMatrix[i], 0, newIndex)
        }
        matrix = newMatrix
    }

    override fun addEdge(start: Int, finish: Int) {
        val si = indexOf[start] ?: throw IllegalArgumentException("Vertex $start does not exist")
        val fi = indexOf[finish] ?: throw IllegalArgumentException("Vertex $finish does not exist")
        matrix[si][fi] = true
    }

    override fun bfs(vertex: Int): List<Pair<Int, Int>> {
        if (!indexOf.containsKey(vertex)) return emptyList()

        val visited = linkedMapOf<Int, Int>()
        for (v in verticesList) visited[v] = 0

        val result = linkedMapOf<Int, Int>()
        val queue = ArrayDeque<Int>()

        queue.addLast(vertex)
        visited[vertex] = 1
        result[vertex] = 0

        while (queue.isNotEmpty()) {
            val cur = queue.removeFirst()
            val curIndex = indexOf[cur]!!

            for (neiIndex in matrix[curIndex].indices) {
                if (matrix[curIndex][neiIndex]) {
                    val nei = verticesList[neiIndex]
                    if (visited[nei] == 0) {
                        queue.addLast(nei)
                        visited[nei] = 1
                        result[nei] = result[cur]!! + 1
                    }
                }
            }
        }

        return result.toList()
    }

    override fun print() {
        println("Vertices: $verticesList")
        println("Adjacency matrix:")
        print("\t")
        verticesList.forEach { print(String.format("%4s", it)) }
        println()
        for (i in matrix.indices) {
            print(String.format("%4s", verticesList[i]))
            for (j in matrix[i].indices) {
                print(String.format("%4d", if (matrix[i][j]) 1 else 0))
            }
            println()
        }
    }
}
