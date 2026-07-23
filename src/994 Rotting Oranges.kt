//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val grid = arrayOf(intArrayOf(2,1,1, 0),intArrayOf(1,1,0, 0),intArrayOf(0,1,1, 0), intArrayOf(0,1,1, 2))
    println(orangesRotting(grid))
}

fun orangesRotting(grid: Array<IntArray>): Int {
    printGrid(grid)

    var queue = ArrayDeque<Pair<Int, Int>>()
    var fresh = 0
    var minutes = 0
    for (i in 0 .. grid.size - 1) {
        for (j in 0 .. grid[i].size - 1) {
            if (grid[i][j] == 1) {
                fresh++
            } else if (grid[i][j] == 2) {
                queue.addFirst(Pair(i, j))
            }
        }

    }
//    printGrid(grid)
    println(queue)

    var neighbors = listOf(Pair(0, -1), Pair(-1, 0), Pair(0, 1), Pair(1, 0))
    while (queue.isNotEmpty() && fresh > 0) {
        var currentQueueSize = queue.size
        while (currentQueueSize > 0) {
            var rottenOrangeCoords = queue.removeFirst()
            var rotenI = rottenOrangeCoords.first
            var rotenJ = rottenOrangeCoords.second
            neighbors.forEach { coordsOfNeighbor ->
                var neighborForRottingI = rotenI + coordsOfNeighbor.first
                var neighborForRottingJ = rotenJ + coordsOfNeighbor.second
                if (neighborForRottingI >= 0 && neighborForRottingI < grid.size
                    && neighborForRottingJ >= 0 && neighborForRottingJ < grid[0].size
                    && grid[neighborForRottingI][neighborForRottingJ] == 1
                ) {
                    grid[neighborForRottingI][neighborForRottingJ] = 2
                    fresh--
                    queue.addLast(neighborForRottingI to neighborForRottingJ)
                }
            }
            currentQueueSize--
        }
        minutes++
        println("*****")
        printGrid(grid)
        println("*****")
    }

    println("after rotting")
    printGrid(grid)
    println("minutes $minutes")

    return if (fresh == 0) minutes else -1
}

fun printQueue(q: ArrayDeque<Pair<Int, Int>>) {

}

fun printGrid(grid: Array<IntArray>) {
    for (i in 0 .. grid.size - 1) {
        for (j in 0 .. grid[i].size - 1) {
            print("${grid[i][j]}  ")
        }
        println()
    }
}
