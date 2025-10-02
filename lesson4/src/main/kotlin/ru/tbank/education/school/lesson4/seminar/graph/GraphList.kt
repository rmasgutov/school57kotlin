package ru.tbank.education.school.ru.tbank.education.school.lesson4.seminar.graph

import BFS
import Graph
import PrintGraph

class GraphList : Graph, BFS, PrintGraph {
    private var g: MutableList<MutableList<Int>> = mutableListOf()
    private var n: Int = g.size

    override fun addVertex() {
        g.add(mutableListOf())
        n++
    }

    override fun addEdge(u: Int, v: Int) {
        if (u < n && v < n) {
            g[u].add(v)
            g[v].add(u)
        }
    }

    override fun length(u: Int, v: Int): Int {
        var queue: ArrayDeque<Int> = ArrayDeque(arrayListOf(u))
        var used: BooleanArray = BooleanArray(n);
        var dist: ArrayList<Int> = ArrayList(MutableList(n) { 0 })
        used[u] = true
        while (queue.isNotEmpty()) {
            val x = queue.removeFirst()
            if (x == v) {return dist[x]}
            val next = dist[x] + 1
            for (w in g[x]) {
                if (!used[w]) {
                    queue.add(w)
                    used[w] = true
                    dist[w] = next
                }
            }
        }
        return dist[v]
    }

    override fun print() {
        for (i in 0 until n) {
            println("$i ${g[i]}")
        }
    }
}