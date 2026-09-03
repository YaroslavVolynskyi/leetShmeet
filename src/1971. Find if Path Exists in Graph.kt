package l1971

fun main() {
    println(
        validPath(n = 6,
            edges = arrayOf(intArrayOf(0,1),intArrayOf(0,2),intArrayOf(3,5),intArrayOf(5,4),intArrayOf(4,3)),
            source = 4, destination = 5)
    )
    println()
}

fun validPath(n: Int, edges: Array<IntArray>, source: Int, destination: Int): Boolean {
    val rootsMap = mutableMapOf<Int, Int>()
    for (i in 0 .. n - 1) {
        rootsMap[i] = i
    }
    edges.forEach { edge ->
        val rootA = find(rootsMap, edge[0])
        val rootB = find(rootsMap, edge[1])
        if (rootA != rootB) {
            rootsMap[rootB] = rootA
        }
    }

    return find(rootsMap, source) == find(rootsMap, destination)
}

fun find(rootsMap: MutableMap<Int, Int>, x: Int): Int {
    var current = x
    while (rootsMap[current] != current) {
        rootsMap[current] = rootsMap[rootsMap[current]!!]!!
        current = rootsMap[current]!!
    }
    return current
}