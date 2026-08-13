package shortest

import java.util.PriorityQueue

fun main() {
    println(
        shortestPathBinaryMatrix(
            arrayOf(
                intArrayOf(0,0,0),
                intArrayOf(1,1,0),
                intArrayOf(1,1,0)
            )
        )
    )
}

fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
    if (grid[0][0] == 1) {
        return -1
    }
//    val queue = PriorityQueue<Triple<Int, Int, Int>>( compareBy { it.third } ) // plain dijkstra
    val queue = PriorityQueue<Triple<Int, Int, Int>>( compareBy { it.third + heuristic(it.first, it.second, grid) }) // A*
    queue.add(Triple(0, 0, 1))
    val visited = hashSetOf<Pair<Int, Int>>()
    while (queue.isNotEmpty()) {
        val (i, j, distance) = queue.poll()
        if (i to j in visited) {
            continue
        }
        visited.add(i to j)
        if (i == grid.size - 1 && j == grid[i].size - 1) {
            return distance
        }

        for ((dirI, dirJ) in listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0, 1 to 1, -1 to -1, -1 to 1, 1 to -1)) {
            val nextI = i + dirI
            val nextJ = j + dirJ
            if (nextI in 0 .. grid.size - 1
                && nextJ in 0 .. grid[nextI].size - 1
                && grid[nextI][nextJ] == 0
                && nextI to nextJ !in visited
                ) {
                queue.add(Triple(nextI, nextJ, distance + 1))
            }
        }
    }

    return -1
}

private fun heuristic(i: Int, j: Int, grid: Array<IntArray>): Int {
    return Math.max(
        Math.abs(i - (grid.size - 1)),
        Math.abs(j - (grid[i].size - 1))
    )
}