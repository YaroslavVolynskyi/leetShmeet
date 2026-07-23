/**
 * 200. Number of Islands
 * https://leetcode.com/problems/number-of-islands/description/
 */
fun main() {
    val grid2 = arrayOf(
        arrayOf("0","1","1","1","0"),
        arrayOf("0","1","0","1","0"),
        arrayOf("1","1","0","0","0"),
        arrayOf("0","0","0","0","0")
    )

    val visited = Array(grid2.size) { i ->
        BooleanArray(grid2[i].size) { false }
    }

    var grid = arrayOf(
        charArrayOf('1','1','0','1','1'),
        charArrayOf('1','1','0','0','0'),
        charArrayOf('0','0','0','0','0'),
        charArrayOf('1','1','0','1','1'),
    )
    var num = numIslands(grid)
    println(num)
}

fun numIslands(grid: Array<CharArray>): Int {
    var islandsCount = 0
    val visited = Array(grid.size) {
        i -> BooleanArray(grid[i].size) { false }
    }
    for (i in 0 .. grid.size - 1) {
        for (j in 0 .. grid[i].size - 1) {
            if (grid[i][j] == '1' && !visited[i][j]) {
                islandsCount++
                exploreConnected2(grid, i, j, visited)
            }
        }
    }
    return islandsCount
}

fun exploreConnected2(grid: Array<CharArray>, currentI: Int, currentJ: Int, visited: Array<BooleanArray>) {
    var queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(currentI to currentJ)
    visited[currentI][currentJ] = true
    while (queue.isNotEmpty()) {
        val (i, j) = queue.removeFirst()
        for ((directionI, directionJ) in listOf(0 to 1, 1 to 0, 0 to -1, -1 to 0)) {
            var nextI = i + directionI
            var nextJ = j + directionJ
            if (nextI !in grid.indices || nextJ !in grid[nextI].indices) {
                continue
            }
            if (visited[nextI][nextJ]) {
                continue
            }
            if (grid[nextI][nextJ] == '0') {
                continue
            }
            queue.add(nextI to nextJ)
            visited[nextI][nextJ] = true
        }
    }
}

fun exploreConnected(grid: Array<CharArray>, currentI: Int, currentJ: Int, visited: Array<BooleanArray>) {
    if (currentI < 0 || currentI >= grid.size || currentJ < 0 || currentJ >= grid[currentI].size) {
        return
    }
    if (visited[currentI][currentJ] || grid[currentI][currentJ] == '0') {
        return
    }
    visited[currentI][currentJ] = true
    if (grid[currentI][currentJ] == '1') {
        exploreConnected(grid, currentI, currentJ + 1, visited)
        exploreConnected(grid, currentI + 1, currentJ, visited)
        exploreConnected(grid, currentI, currentJ - 1, visited)
        exploreConnected(grid, currentI - 1, currentJ, visited)
    }
}
