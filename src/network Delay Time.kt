package networkdelaytime

import java.util.PriorityQueue

fun main() {
    println(
        networkDelayTime(
            arrayOf(
                intArrayOf(1,2,1),
                intArrayOf(2,3,1),
                intArrayOf(1,4,4),
                intArrayOf(3,4,1),

                intArrayOf(1, 3, 2),
                intArrayOf(3, 2, 2),
                intArrayOf(2, 4, 2)
                ), n = 4, k = 1,
        )
    )
}

/**
 * https://neetcode.io/problems/network-delay-time/question
 */
fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
    val adjacencyMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>() // node -> (neighbor, time to neighbor)
    val distancesMap = mutableMapOf<Int, Int>() // node, distance
    for (i in 1 .. n) {
        adjacencyMap[i] = mutableListOf()
        distancesMap[i] = if (i == k) 0 else Int.MAX_VALUE
    }
    times.forEach { time ->
        adjacencyMap[time[0]]!!.add(time[1] to time[2])
    }
    val heap = PriorityQueue<Pair<Int, Int>>(compareBy { it.first} ) // Pair(distance, node)
    heap.add(0 to k)

    while (heap.isNotEmpty()) {
        val (distance, node) = heap.poll()
        if (distance > distancesMap[node]!!) {
            continue
        }
        adjacencyMap[node]?.forEach { (neighborNode, neighborTime) ->
            val newDistance = distance + neighborTime
            if (newDistance < distancesMap[neighborNode]!!) {
                distancesMap[neighborNode] = newDistance
                heap.add(newDistance to neighborNode)
            }
        }
    }

    return if (distancesMap.values.max() == Int.MAX_VALUE) {
        -1
    } else {
        distancesMap.values.max()
    }
}




















//fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
//    val results = mutableMapOf<Int, Int>()
//    val adjacencyMap = mutableMapOf<Int, MutableList<Pair<Int, Int>>>()
//    for (i in 1 .. n) {
//        adjacencyMap[i] = mutableListOf()
//        results[i] = Int.MAX_VALUE
//    }
//    results[k] = 0
//    times.forEach { time ->
//        adjacencyMap[time[0]]?.add(time[1] to time[2])
//    }
//
//    val heap = PriorityQueue<Pair<Int, Int>>(compareBy { it.first }) // Pair(distance, node)
//    heap.add(0 to k)
//    while (heap.isNotEmpty()) {
//        val (distance, node) = heap.poll()
//        if (distance > results[node]!!) {
//            continue
//        }
//        adjacencyMap[node]?.forEach { (neighbor, weight) ->
//            val newDistance = distance + weight
//            if (newDistance < results[neighbor]!!) {
//                results[neighbor] = newDistance
//                heap.add(newDistance to neighbor)
//            }
//        }
//    }
//
//    val maxDist = results.values.max()!!
//    return if (maxDist == Int.MAX_VALUE) -1 else maxDist
//}
