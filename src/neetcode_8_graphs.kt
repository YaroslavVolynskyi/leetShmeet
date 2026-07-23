import java.util.PriorityQueue
import java.util.Queue

fun main() {
//    val node1 = Node(1)
//    val node2 = Node(2)
//    val node3 = Node(3)
//    node1.neighbors.add(node2)
//    node2.neighbors.add(node1)
//    node2.neighbors.add(node3)
//    node3.neighbors.add(node2)
//
//    val cloned = cloneGraph(node2)

//    val grid = arrayOf(
//        intArrayOf(2147483647,  -1,         0,          2147483647),
//        intArrayOf(2147483647,  2147483647, 2147483647, -1),
//        intArrayOf(2147483647, -1,          2147483647, -1),
//        intArrayOf(0,          -1,          2147483647, 2147483647)
//    )
//
//    grid.forEach { a ->
//        a.forEach { print("$it ") }
//        println()
//    }
//    islandsAndTreasure(grid)
//println()
//    grid.forEach { a ->
//        a.forEach { print("$it ") }
//        println()
//    }

//    val fruits = arrayOf(
//        intArrayOf(1,1,0),
//        intArrayOf(0,1,1),
//        intArrayOf(0,1,2)
//    )
//    println(orangesRotting2(fruits))
//    val heights = arrayOf(
//        intArrayOf(4, 2, 7, 3, 4),
//        intArrayOf(7, 4, 6, 4, 7),
//        intArrayOf(6, 3, 5, 3, 6)
//    )
//    val result = pacificAtlantic(heights)
//    println(result)

//    val board = arrayOf(
//        charArrayOf('X', 'X', 'X', 'X'),
//        charArrayOf('X', 'O', 'O', 'X'),
//        charArrayOf('X', 'X', 'O', 'X'),
//        charArrayOf('X', 'O', 'X', 'X'),
//    )
//    solve(board)
//    val res = findOrder(numCourses = 4, prerequisites =
//        // course  0 is available
//        // courses 2 and 1 become available after finishing course 0
//        // course  3 becomes available after finishing 1 and 2
//        arrayOf(
//            // take course 0 if I want to take 1
//            intArrayOf(1, 0),
//
//            // take course 0 if I want to take 2
//            intArrayOf(2, 0),
//
////            intArrayOf(2, 3),
//
//            // take course 1 if I want to take 3
//            intArrayOf(3, 1),
//
//            // take course 2 if I want to take 3
//            intArrayOf(3, 2)
//        )
//    )
//    println(res.contentToString())

//    println(validTree(5, arrayOf(
//        intArrayOf(0, 1), intArrayOf(0, 2), intArrayOf(0, 3), intArrayOf(1, 4)
//    )))

//    println(
//        countComponents(n = 5, edges = arrayOf(intArrayOf(0, 1), intArrayOf(1,2),intArrayOf(3,4)))
//    )

//    println(
//        findRedundantConnection(
//            arrayOf(intArrayOf(1,2), intArrayOf(1,3), intArrayOf(1,4), intArrayOf(3,4), intArrayOf(4,5))
//        ).contentToString()
//    )

//    println(
//        countComponents2(n = 5, edges = arrayOf(intArrayOf(0, 1), intArrayOf(1,2),intArrayOf(3,4)))
//    )

//    val res = findItinerary(
//        listOf(listOf("BUF","HOU"),listOf("HOU","SEA"),listOf("JFK","BUF"))
//    )
//    res.forEach { print(it) }

//    println(
//        swimInWater(arrayOf(
//            intArrayOf(0,1,2,10),
//            intArrayOf(9,14,4,13),
//            intArrayOf(12,3,8,15),
//            intArrayOf(11,5,7,6)
//        ))
//    )

//    println(
//        findCheapestPrice(n = 4,
//            flights = arrayOf(
//                intArrayOf(0,1,200),
//                intArrayOf(1,2,100),
//                intArrayOf(1,3,300),
//                intArrayOf(2,3,100)
//            ),
//            src = 0, dst = 3, k = 1
//        )
//    )


}

/**
 * https://neetcode.io/problems/min-cost-to-connect-points/question?list=neetcode150
 */
fun minCostConnectPoints(points: Array<IntArray>): Int {

}

/**
 * https://neetcode.io/problems/cheapest-flight-path/question?list=neetcode150
 */
fun findCheapestPrice(n: Int, flights: Array<IntArray>, src: Int, dst: Int, k: Int): Int {
    val map = mutableMapOf<Int, MutableList<Pair<Int, Int>>>() // node -> list (destinationNode to price)
    val pricesMap = mutableMapOf<Int, Int>()
    for (i in 0 until n) {
        map[i] = mutableListOf()
        pricesMap[i] = if (i == src) 0 else Int.MAX_VALUE
    }
    flights.forEach { flight ->
        map[flight[0]]!!.add(flight[1] to flight[2])
    }
    val queue = ArrayDeque<Pair<Int, Int>>() // destination to price
    queue.add(src to 0)
    var c = 0
    while (queue.isNotEmpty() && c != k+1) {
        repeat(queue.size) {
            val (currentCity, price) = queue.removeFirst()
            map[currentCity]?.forEach { (neighbor, priceToNeighbor) ->
                val newPrice = price + priceToNeighbor
                if (pricesMap[neighbor]!! > newPrice) {
                    pricesMap[neighbor] = newPrice
                    queue.add(neighbor to newPrice)
                }
            }
        }
        c++
    }

    return if (pricesMap[dst]!! == Int.MAX_VALUE) -1 else pricesMap[dst]!!
}

/**
 * https://neetcode.io/problems/swim-in-rising-water/question?list=neetcode150
 */
fun swimInWater(grid: Array<IntArray>): Int {
    val queue = PriorityQueue<Pair<Int,Int>>(compareBy { grid[it.first][it.second] })
    queue.add(0 to 0)
    val path = mutableSetOf<Int>()
    val visited = mutableSetOf<Pair<Int, Int>>()
    while (queue.isNotEmpty()) {
        val (i, j) = queue.poll()
        if(visited.contains(i to j)) {
            continue
        }
        visited.add(i to j)
        path.add(grid[i][j])
        println("${grid[i][j]}")
        if (i == grid.size - 1 && j == grid[i].size - 1) {
            return path.max()
        }
        for ((dirI, dirJ) in listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)) {
            val nextI = dirI + i
            val nextJ = dirJ + j
            if (nextI >= 0 && nextI < grid.size && nextJ >= 0 && nextJ < grid[nextI].size) {
                if (!visited.contains(nextI to nextJ)) {
                    queue.add(nextI to nextJ)
                }
            }
        }
    }
    return 0
}

fun nextInWater(i: Int, j: Int, grid: Array<IntArray>, path: MutableSet<Int>) {
    if (i == grid.size && j == grid[i].size) {

    }
    for ((dirI, dirJ) in listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)) {
        val nextI = dirI + i
        val nextJ = dirJ + j

    }
}

/**
 * https://neetcode.io/problems/reconstruct-flight-path/question?list=neetcode150
 */
fun findItinerary(tickets: List<List<String>>): List<String> {
    val adjacencyMap = mutableMapOf<String, MutableList<String>>()
    tickets.forEach { ticket ->
        adjacencyMap.getOrPut(ticket[0]) { mutableListOf() }.add(ticket[1])
    }
    adjacencyMap.values.forEach { it.sortDescending() }
    val route = mutableListOf<String>()
    ticketDFS(adjacencyMap, "JFK", route)

    return route.reversed()
}

fun ticketDFS(map: Map<String, MutableList<String>>, currentCity: String, route: MutableList<String>) {
    val neighbors = map[currentCity]
    while (!neighbors.isNullOrEmpty()) {
        val next = neighbors.removeLast()
        ticketDFS(map, next, route)
    }
    route.add(currentCity)
}

fun countComponents2(n: Int, edges: Array<IntArray>): Int {
    val parent = IntArray(n) { it }
    fun findRoot(node: Int): Int {
        var currentNode = node
        while(currentNode != parent[currentNode]) {
            currentNode = parent[currentNode]
        }
        return currentNode
    }
    edges.forEach { edge ->
        val root0 = findRoot(edge[0])
        val root1 = findRoot(edge[1])
        if (root1 != root0) {
            parent[root0] = root1
        }
    }
    var count = 0
    for (i in 0 until n) {
        if (findRoot(i) == i) {
            count++
        }
    }
    return count
}

//fun findRoot(edges: Array<IntArray>, node: Int): Int {
//    edges.forEach { edge ->
//        if (edge[0] == node) {
//
//        }
//    }
//}

/**
 * https://neetcode.io/problems/redundant-connection/question?list=neetcode150
 */
fun findRedundantConnection(edges: Array<IntArray>): IntArray {
    val map = mutableMapOf<Int, MutableList<Int>>()
    edges.forEach { edge ->
        map.getOrPut(edge[0]) { mutableListOf() }.add(edge[1])
        map.getOrPut(edge[1]) { mutableListOf() }.add(edge[0])
        if (hasCycle(edge[0], map, edge[0], mutableSetOf())) {
            return edge
        }
    }
    return intArrayOf()
}

fun hasCycle(currentNode: Int, map: Map<Int, List<Int>>, parent: Int, visited: MutableSet<Int>): Boolean {
    visited.add(currentNode)
    map[currentNode]?.forEach { neighbor ->
        if (neighbor == parent) {
        } else if (visited.contains(neighbor)) {
            return true
        } else if (hasCycle(neighbor, map, parent = currentNode, visited)) {
            return true
        }
    }
    return false
}

/**
 * https://neetcode.io/problems/count-connected-components/question?list=neetcode150
 */
fun countComponents(n: Int, edges: Array<IntArray>): Int {
    val map = mutableMapOf<Int, MutableList<Int>>()
    edges.forEach { edge ->
        map.getOrPut(edge[0]) { mutableListOf()}.add(edge[1])
        map.getOrPut(edge[1]) { mutableListOf()}.add(edge[0])
    }
    var count = 0
    val visited = mutableSetOf<Int>()
    var i = 0
    while (i < n && visited.size != n) {
        var oldSize = visited.size
        countComponentsDfs(i, map, visited)
        i++
        if (visited.size > oldSize) {
            count++
        }
    }

    return count
}

fun countComponentsDfs(currentNode: Int, map: Map<Int, List<Int>>, visited: MutableSet<Int>) {
    if (visited.contains(currentNode)) {
        return
    }
    visited.add(currentNode)
    map[currentNode]?.forEach { neighbor ->
        countComponentsDfs(neighbor, map, visited)
    }
}

/**
 * https://neetcode.io/problems/valid-tree/solution
 */
fun validTree(n: Int, edges: Array<IntArray>): Boolean {
    if (edges.size != n - 1) {
        return false
    }
    val map = hashMapOf<Int, MutableList<Int>>()
    edges.forEach { edge ->
        map.getOrPut(edge[0]) { mutableListOf() }.add(edge[1])
        map.getOrPut(edge[1]) { mutableListOf() }.add(edge[0])
    }
    val visited = hashSetOf<Int>()
    validTreeDfs(0, map, visited)
    return visited.size == n
}

fun validTreeDfs(currentNode: Int, map: Map<Int, List<Int>>, visited: MutableSet<Int>) {
    if (visited.contains(currentNode)) {
        return
    }
    visited.add(currentNode)
    map[currentNode]?.forEach { neighbor ->
        validTreeDfs(neighbor, map, visited)
    }
    return
}

/**
 * https://neetcode.io/problems/course-schedule-ii/solution
 */
fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
// key - course to unlock; value - list of courses that must be taken
    val map = mutableMapOf<Int, MutableList<Int>>()
    prerequisites.forEach { prereq ->
        map.getOrPut(prereq[0]) { mutableListOf() }.add(prereq[1])
    }
    for (i in 0 .. numCourses - 1) {
        map.getOrPut(i) { mutableListOf() }
    }

    val visited = mutableSetOf<Int>()
    val order = mutableListOf<Int>()
    map.forEach { entry ->
        val currentCourse = entry.key
        if (!canFinishCurrent(currentCourse, map, mutableSetOf(), visited, order)) {
            return intArrayOf()
        }
    }
    return order.toIntArray()
}

/**
 * https://neetcode.io/problems/course-schedule/question?list=neetcode150
 */
fun canFinish2(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    // key - course to unlock; value - list of courses that must be taken
    val map = mutableMapOf<Int, MutableList<Int>>()
    prerequisites.forEach { prereq ->
        map.getOrPut(prereq[0]) { mutableListOf() }.add(prereq[1])
    }
    for (i in 0 .. numCourses - 1) {
        map.getOrPut(i) { mutableListOf() }
    }

    val visited = mutableSetOf<Int>()
    var canFinish = true
    map.forEach { entry ->
        val currentCourse = entry.key
        canFinish = canFinish && canFinishCurrent(currentCourse, map, mutableSetOf(), visited, mutableListOf())
    }
    return canFinish
}

fun canFinishCurrent(course: Int, map: Map<Int, List<Int>>, path: MutableSet<Int>, visited: MutableSet<Int>, order: MutableList<Int>): Boolean {
    if (path.contains(course)) {
        return false
    }
    if (visited.contains(course)) {
        return true
    }
    path.add(course)
    val prerequisites = map[course]
    if (prerequisites.isNullOrEmpty()) {
        path.remove(course)
        visited.add(course)
        order.add(course)
        return true
    }
    var canFinish = true
    prerequisites.forEach { courseThatMustBeTaken ->
        canFinish = canFinish && canFinishCurrent(courseThatMustBeTaken, map, path, visited, order)
    }

    path.remove(course)
    if (canFinish) {
        visited.add(course)
        order.add(course)
    }
    return canFinish
}



fun capture(i: Int, j: Int, board: Array<CharArray>) {
    if (board[i][j] != 'O') {
        return
    }
    board[i][j] = 'T'
    for ((dirI, dirJ) in listOf(1 to 0, -1 to 0, 0 to -1, 0 to 1)) {
        val nextI = i + dirI
        val nextJ = j + dirJ
        if (nextI !in board.indices || nextJ !in board[nextI].indices) {
            continue
        }
        if (board[nextI][nextJ] == 'X') {
            continue
        }
        if (board[nextI][nextJ] == 'O') {
//            board[nextI][nextJ] = 'T'
            capture(nextI, nextJ, board)
        }
    }
}

/**
 * https://neetcode.io/problems/surrounded-regions/question?list=neetcode150
 */
fun solve(board: Array<CharArray>) {
    // run captures from borders rows and cols
    // first row
    for (i in board[0].indices) {
        capture(0, i, board)
    }
    // last row
    for (i in board[board.size - 1].indices) {
        capture(board.size - 1, i, board)
    }
    // first col
    for (i in board.indices) {
        capture(i, 0, board)
    }
    // last col
    for (i in board.indices) {
        capture(i, board[i].size - 1, board)
    }
    for (i in board.indices) {
        for (j in board[i].indices) {
            if (board[i][j] == 'O') {
                board[i][j] = 'X'
            } else if (board[i][j] == 'T') {
                board[i][j] = 'O'
            }
        }
    }
}

/**
 * https://neetcode.io/problems/pacific-atlantic-water-flow/question?list=neetcode150
 */
fun pacificAtlantic(heights: Array<IntArray>): List<List<Int>> {
    val pacific = hashSetOf<Pair<Int, Int>>()
    val atlantic = hashSetOf<Pair<Int, Int>>()
    val visited = Array(heights.size) { i ->
        BooleanArray(heights[i].size) { false }
    }
    // first col
    for (i in 0 .. heights.size - 1) {
        dfs(i, 0, heights, pacific, visited)
    }
    // first row
    for (j in 0 .. heights[0].size - 1) {
        dfs(0, j, heights, pacific, visited)
    }

    visited.forEachIndexed { i, line ->
        for (j in line.indices) {
            visited[i][j] = false
        }
    }
    // last col
    for (i in 0 .. heights.size - 1) {
        dfs(i, heights[i].size - 1, heights, atlantic, visited)
    }
    // last row
    for (j in 0 .. heights[0].size - 1) {
        dfs(heights.size - 1, j, heights, atlantic, visited)
    }
    val result = mutableListOf<List<Int>>()
    for (i in heights.indices) {
        for (j in heights[i].indices) {
            if ((i to j) in atlantic && (i to j) in pacific) {
                result.add(listOf(i, j))
            }
        }
    }
    return result
}

fun dfs(i: Int, j: Int, heights: Array<IntArray>, pacific: HashSet<Pair<Int, Int>>, visited: Array<BooleanArray>) {
    pacific.add(i to j)
    visited[i][j] = true
    for ((dirI, dirJ) in listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)) {
        val nextI = i + dirI
        val nextJ = j + dirJ
        if (nextI !in heights.indices || nextJ !in heights[nextI].indices) {
            continue
        }
        if (visited[nextI][nextJ]) {
            continue
        }
        if (heights[nextI][nextJ] >= heights[i][j]) {
            dfs(nextI, nextJ, heights, pacific, visited)
        }
    }
}

/**
 * https://neetcode.io/problems/rotting-fruit/question?list=neetcode150
 */
fun orangesRotting2(grid: Array<IntArray>): Int {
    var days = 0
    val queue = ArrayDeque<Pair<Int, Int>>()
    var fresh = 0
    grid.forEachIndexed { i, line ->
        line.forEachIndexed { j, element ->
            if (element == 2) {
                queue.add(i to j)
            } else if (element == 1) {
                fresh++
            }
        }
    }

    while (queue.isNotEmpty()) {
        var newFresh = fresh
        repeat(queue.size) {
            val (i, j) = queue.removeFirst()
            fresh = rot(i, j, queue, grid, fresh)
        }
        if (fresh < newFresh) {
            days++
        }
    }
    if (fresh > 0) {
        return -1
    }
    return days
}

fun rot(i: Int, j: Int, queue: ArrayDeque<Pair<Int, Int>>, grid: Array<IntArray>, fresh: Int): Int {
    var newFreshCount = fresh
    for ((dirI, dirJ) in listOf(0 to 1, 0 to -1, -1 to 0, 1 to 0)) {
        val nextI = i + dirI
        val nextJ = j + dirJ
        if (nextI !in grid.indices || nextJ !in grid[nextI].indices) {
            continue
        } else if (grid[nextI][nextJ] == 0) {
            continue
        } else if (grid[nextI][nextJ] == 1) {
            newFreshCount--
            grid[nextI][nextJ] = 2
            queue.addLast(nextI to nextJ)
        }
    }
    return newFreshCount
}

/**
 * https://neetcode.io/problems/islands-and-treasure/question?list=neetcode150
 */
fun islandsAndTreasure(grid: Array<IntArray>) {
    val queue = ArrayDeque<Pair<Int, Int>>()
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (grid[i][j] == 0) {
                queue.add(i to j)
            }
        }
    }
    while (queue.isNotEmpty()) {
        val (currentI, currentJ) = queue.removeFirst()
        markNeighbors(currentI, currentJ, grid, queue)
    }
}

fun markNeighbors(i: Int, j: Int, grid: Array<IntArray>, queue: ArrayDeque<Pair<Int, Int>>) {
    for ((dirI, dirJ) in listOf(1 to 0, -1 to 0, 0 to 1, 0 to -1) ) {
        val nextI = i + dirI
        val nextJ = j + dirJ
        if (nextI !in grid.indices || nextJ !in grid[nextI].indices) {
            continue
        } else if (grid[nextI][nextJ] == -1 || grid[nextI][nextJ] == 0) {
            continue
        } else if (grid[nextI][nextJ] == 2147483647) {
            grid[nextI][nextJ] = grid[i][j] + 1
            queue.addLast(nextI to nextJ)
        }
    }
}

/**
 * https://neetcode.io/problems/clone-graph/question?list=neetcode150
 * Definition for a Node.
 * class Node(var `val`: Int) {
 *     var neighbors: ArrayList<Node?> = ArrayList()
 * }
 */

fun cloneGraph(node: Node?): Node? {
    return cloneGraph(node, mutableMapOf())
}

fun cloneGraph(node: Node?, visited: MutableMap<Node, Node>): Node? {
    if (node == null) {
        return null
    }
    if (visited[node] != null) {
        return visited[node]
    }
    val cloneNode = Node(
        `val` = node.`val`
    )
    visited[node] = cloneNode
    node.neighbors.forEach { neighbor ->
        cloneNode.neighbors.add(cloneGraph(neighbor, visited))
    }
    return cloneNode
}

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
}
