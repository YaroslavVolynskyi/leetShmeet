package practice

import java.awt.Image
import java.util.PriorityQueue

fun main() {
    val node1 = Node(1)
    val node2 = Node(2)
    val node3 = Node(3)
    node1.neighbors = arrayListOf(node2)
    node2.neighbors = arrayListOf(node1, node3)
    node3.neighbors = arrayListOf(node2)

//    val cloned = clone(node1)
//    val cloned2 = cloneBfs(node1)

//    println(
//        orangesRotting(
//            arrayOf(
//                intArrayOf(1,1,0),
//                intArrayOf(0,1,1),
//                intArrayOf(0,1,2)
//                )
//        )
//    )


//    val q = PriorityQueue<Int>(compareByDescending { it })
//    q.add(34)
//    q.add(11)
//    q.add(89)
//    while (q.isNotEmpty()) {
//        println(q.poll())
//    }

    val image = arrayOf(
        intArrayOf(0, 0, 0, 0, 0, 0, 0),
        intArrayOf(0, 1, 1, 1, 1, 0, 0),
        intArrayOf(1, 0, 0, 0, 0, 1, 0),
        intArrayOf(0, 1, 1, 1, 0, 1, 0),
        intArrayOf(0, 0, 0, 0, 1, 1, 0),
        intArrayOf(0, 0, 0, 0, 0, 0, 0),
    )
    val shoreline = shoreline(image, 1 to 1)
    val borders = borders(image, 1 to 1)
//    val lakes = lakes(image, shoreline)

//    println(lakes)

    println(
        isInsideBorders(1, 5, borders, image)
    )
    println(
        isInsideBorders(2, 4, borders, image)
    )
    println(
        isInsideBorders(2, 6, borders, image)
    )

    val lakes = lakes(image, borders)
    println(lakes)


    println()
}

private fun isInsideBorders(i: Int, j: Int, borders: Set<Pair<Int, Int>>, image: Array<IntArray>): Boolean {
    if (i to j in borders) {
        return true
    }
    val iRow = borders.filter { it.first == i }.sortedBy { it.second }
    val jCol = borders.filter { it.second == j }.sortedBy { it.first }
    if (iRow.isEmpty() || jCol.isEmpty()) {
        return false
    }
    return j in iRow.first().second .. iRow.last().second
            && i in jCol.first().first .. jCol.last().first
}

fun lakes(image: Array<IntArray>, borders: Set<Pair<Int, Int>>): Int {
    var lakesCount = 0
    val visited = hashSetOf<Pair<Int, Int>>()
    val directions = listOf(0 to 1, 0 to -1, -1 to 0, 1 to 0)
    for (i in image.indices) {
        for (j in image[i].indices) {
            if (i to j in visited) {
                continue
            }
            visited.add(i to j)
            if (image[i][j] == 1) {
                continue
            }
            var isLake = isInsideBorders(i, j, borders, image)
//            if (!isInsideBorders(i, j, borders, image)) {
//                continue
//            }
            // from below means we're in a lake
            val queue = ArrayDeque<Pair<Int, Int>>()
            queue.add(i to j)
            while (queue.isNotEmpty()) {
                val current = queue.removeFirst()
                for ((dirI, dirJ) in directions) {
                    val nextI = current.first + dirI
                    val nextJ = current.second + dirJ
                    if (nextI !in image.indices || nextJ !in image[nextI].indices) {
                        isLake = false
                        continue
                    }
                    if (nextI to nextJ in visited) {
                        continue
                    }
                    visited.add(nextI to nextJ)
                    if (image[nextI][nextJ] == 0) {
                        if (!isInsideBorders(nextI, nextJ, borders, image)) {
                            isLake = false
                        }
                        queue.add(nextI to nextJ)
                    }
                }
            }
            if (isLake) {
                lakesCount++
            }
        }
    }

    return lakesCount
}

fun shoreline(image: Array<IntArray>, start: Pair<Int, Int>): Set<Pair<Int, Int>> {
    if (image[start.first][start.second] == 0) {
        return hashSetOf()
    }
    val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0, -1 to -1, 1 to 1, 1 to -1, -1 to 1)
    val shoreline = hashSetOf<Pair<Int, Int>>()
    val visited = hashSetOf<Pair<Int, Int>>()
    var queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(start.first to start.second)
    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        if (current in visited) {
            continue
        }
        visited.add(current)
        for ((dirI, dirJ) in directions) {
            val nextI = current.first + dirI
            val nextJ = current.second + dirJ
            if (nextI in image.indices && nextJ in image[nextI].indices) {
                if (image[nextI][nextJ] == 1) {
                    queue.add(nextI to nextJ)
                } else {
                    shoreline.add(nextI to nextJ)
//                    image[nextI][nextJ] = 9
                }
            }
        }
    }

    return shoreline
}

fun borders(image: Array<IntArray>, start: Pair<Int, Int>): Set<Pair<Int, Int>> {
    if (image[start.first][start.second] == 0) {
        return hashSetOf()
    }
    val directions = listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0, -1 to -1, 1 to 1, 1 to -1, -1 to 1)
    val shoreline = hashSetOf<Pair<Int, Int>>()
    val borders = hashSetOf<Pair<Int, Int>>()
    val visited = hashSetOf<Pair<Int, Int>>()
    var queue = ArrayDeque<Pair<Int, Int>>()
    queue.add(start.first to start.second)
    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        if (current in visited) {
            continue
        }
        visited.add(current)
        for ((dirI, dirJ) in directions) {
            val nextI = current.first + dirI
            val nextJ = current.second + dirJ
            if (nextI in image.indices && nextJ in image[nextI].indices) {
                if (image[nextI][nextJ] == 1) {
                    queue.add(nextI to nextJ)
                    borders.add(nextI to nextJ)
                } else {
                    shoreline.add(nextI to nextJ)
//                    image[nextI][nextJ] = 9
                }
            }
        }
    }

    return borders
}

fun orangesRotting(grid: Array<IntArray>): Int {
    val queue = ArrayDeque<Pair<Int, Int>>()
    var freshOranges = 0
    for (i in grid.indices) {
        for (j in grid[i].indices) {
            if (grid[i][j] == 2) {
                queue.add(i to j)
            } else if (grid[i][j] == 1) {
                freshOranges++
            }
        }
    }
    var days = 0

    while (queue.isNotEmpty()) {
        if (freshOranges == 0) {
            break
        }
        repeat(queue.size) {
            val (i, j) = queue.removeFirst()
            for ((dirI, dirJ) in listOf(0 to 1, 0 to -1, 1 to 0, -1 to 0)) {
                val nextI = i + dirI
                val nextJ = j + dirJ
                if (nextI in grid.indices
                    && nextJ in grid[i].indices
                    && grid[nextI][nextJ] == 1
                    ) {
                    freshOranges--
                    grid[nextI][nextJ] = 2
                    queue.addLast(nextI to nextJ)
                }
            }
        }
        days++
    }

    return if (freshOranges > 0) -1 else days
}

fun cloneBfs(node: Node?): Node? {
    if (node == null) {
        return null
    }
    val clonedMap = mutableMapOf<Node, Node>()
    val queue = ArrayDeque<Node>()
    queue.add(node)
    clonedMap[node] = Node(node.`val`)
    while(queue.isNotEmpty()) {
        val currentNode = queue.removeFirst()
        val cloned = clonedMap[currentNode]!!
        currentNode.neighbors.forEach { neighbor ->
            if (neighbor != null) {
                if (neighbor !in clonedMap) {
                    clonedMap[neighbor] = Node(neighbor.`val`)
                    queue.add(neighbor)
                }
                cloned.neighbors.add(clonedMap[neighbor])
            }
        }
    }
    return clonedMap[node]
}

fun clone(node: Node?): Node? {
    return clone(node, mutableMapOf())
}

private fun clone(node: Node?, clonedMap: MutableMap<Node, Node>): Node? {
    if (node == null) {
        return null
    }
    if (node in clonedMap) {
        return clonedMap[node]
    }
    val clonedNode = Node(node.`val`)
    clonedMap[node] = clonedNode
    node.neighbors.forEach { neighbor ->
        clonedNode.neighbors.add(
            clone(neighbor, clonedMap)
        )
    }
    return clonedNode
}

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList()
}