package l547

fun main() {
//    println(
//        findCircleNum(
//            isConnected = arrayOf(
//                intArrayOf(1, 1, 0),
//                intArrayOf(1, 1, 0),
//                intArrayOf(0, 0, 1)
//            )
//        )
//    )
    println(
        findCircleNum(
            //             1  2  3  4
            isConnected = arrayOf(
                intArrayOf(1, 0, 0, 1),
                intArrayOf(0, 1, 1, 0),
                intArrayOf(0, 1, 1, 1),
                intArrayOf(1, 0, 1, 1)
            )
        )
    )
    println()
}

fun findCircleNum(isConnected: Array<IntArray>): Int {
    val parents = IntArray(isConnected.size) { i -> i }
    for (i in isConnected.indices) {
        val parentI = findParent(parents, i)
        for (j in isConnected[i].indices) {
            if (isConnected[i][j] == 1) {
                val parentJ = findParent(parents, j)
                if (parentJ != parentI) {
                    parents[parentJ] = parentI
                }
            }
        }
    }

    return parents.map { findParent(parents, it) }.toSet().size
}

private fun findParent(parents: IntArray, i: Int): Int {
    var current = i
    while (current != parents[current]) {
        current = parents[current]
    }
    return current
}
