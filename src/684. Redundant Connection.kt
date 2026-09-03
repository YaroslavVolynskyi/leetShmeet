package l684

fun main() {
    println(
        findRedundantConnection(
            edges = arrayOf(
                intArrayOf(1, 2),
                intArrayOf(1, 3),
                intArrayOf(2, 3)
            )
        ).contentToString()
    )
    println()
}

fun findRedundantConnection(edges: Array<IntArray>): IntArray {
    val set = mutableSetOf<Int>()
    edges.forEach {
        set.add(it[0])
        set.add(it[1])
    }
    val parents = IntArray(set.size + 1) { i -> i }
    edges.forEach { edge ->
        val result = union(parents, edge)
        if (!result) {
            return edge
        }
    }

    return intArrayOf()
}

private fun union(parents: IntArray, edge: IntArray): Boolean {
    val parent1 = findParent(parents, edge[0])
    val parent2 = findParent(parents, edge[1])
    if (parent1 == parent2) {
        return false
    }
    parents[parent2] = parent1
    return true
}

private fun findParent(parents: IntArray, i: Int): Int {
    var current = i
    while (current != parents[current]) {
        current = parents[current]
    }
    return current
}
