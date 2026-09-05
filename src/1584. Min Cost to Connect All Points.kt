package l1584

import java.awt.Point
import java.util.PriorityQueue

/**
 * https://leetcode.com/problems/min-cost-to-connect-all-points/description/
 */

fun main() {
    println(
        minCostConnectPointsBFS(
            points = arrayOf(
                intArrayOf(0, 0),
                intArrayOf(2, 2),
                intArrayOf(3, 10),
                intArrayOf(5, 2),
                intArrayOf(7, 0)
            )
        )
    )
    println()
}

fun minCostConnectPointsBFS(points: Array<IntArray>): Int {
    val queue = PriorityQueue<Pair<Int, Int>>(compareBy { it.first }) // distance to index
    queue.add(0 to 0)
    val visited = hashSetOf<Int>()
    var totalDistance = 0
    while (queue.isNotEmpty() && visited.size != points.size) {
        val (distance, index) = queue.poll()
        if (index in visited) {
            continue
        }
        visited.add(index)
        totalDistance += distance
        for (i in points.indices) {
            if (i !in visited) {
                queue.add(distance(points[i], points[index]) to i)
            }
        }
    }

    return totalDistance
}



private fun distance(point1: IntArray, point2: IntArray): Int {
    return Math.abs(point1[0] - point2[0]) + Math.abs(point1[1] - point2[1])
}
