package l990

/**
 * https://leetcode.com/problems/satisfiability-of-equality-equations/description/
 */

fun main() {
    println(
        equationsPossible(
            equations = arrayOf("a==b", "b==c", "c==d", "d==e", "e==a")
        )
    )
    println()
}

fun equationsPossible(equations: Array<String>): Boolean {
    val edges = mutableListOf<Pair<Char, Char>>()
    val notEdges = mutableListOf<Pair<Char, Char>>()
    equations.forEach { equ ->
        if (equ.substring(1, 3) == "==") {
            edges.add(equ[0] to equ[3])
        } else {
            notEdges.add(equ[0] to equ[3])
        }

    }
    val parents = mutableMapOf<Char, Char>()
    edges.forEach { edge ->
        val parent1 = findParent(parents, edge.first)
        val parent2 = findParent(parents, edge.second)
        if (parent2 != parent1) {
            parents[parent2] = parent1
        }
    }
    val keys = parents.keys.toSet()
    keys.forEach { key ->
        val parent = findParent(parents, key)
        parents[key] = parent
    }

    notEdges.forEach { notEdge ->
        val parent1 = findParent(parents, notEdge.first)
        val parent2 = findParent(parents, notEdge.second)
        if (parent1 == parent2) {
            return false
        }
    }

    return true
}

fun findParent(parents: MutableMap<Char, Char>, char: Char): Char {
    parents.getOrPut(char) { char }
    var current = char
    while(current != parents[current]) {
        current = parents[current]!!
    }
    return current
}
