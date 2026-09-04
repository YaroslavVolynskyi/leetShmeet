package l1319

/**
 * https://leetcode.com/problems/number-of-operations-to-make-network-connected/description/
 */

fun main() {
    println(
        makeConnected(
            n = 5,
            connections = arrayOf(
                intArrayOf(2, 4),
                intArrayOf(0, 1),
                intArrayOf(0, 2),
                intArrayOf(1, 2),
            )
        )
    )
    println()
}

fun makeConnected(n: Int, connections: Array<IntArray>): Int {
    val parents = IntArray(n) { i -> i }
    var redundantConnectionsCount = 0

    connections.forEach { connection ->
        val parent1 = findParent(parents, connection[0])
        val parent2 = findParent(parents, connection[1])
        if (parent1 != parent2) {
            parents[parent2] = parent1
        } else {
            redundantConnectionsCount++
        }
    }
    parents.toList().forEachIndexed { index, parent ->
        parents[index] = findParent(parents, parent)
    }
    val rootsCount = parents.toSet().size
    val requiredNewCoonections = rootsCount - 1
    return if (requiredNewCoonections <= redundantConnectionsCount) {
        requiredNewCoonections
    } else {
        -1
    }
}

fun findParent(parents: IntArray, i: Int): Int {
    var current = i
    while(current != parents[current]) {
        current = parents[current]
    }
    return current
}
