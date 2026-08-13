package dijkstra

import java.util.PriorityQueue

fun main() {

    println(
        networkDelayTime(arrayOf(
            intArrayOf(2,1,1),
            intArrayOf(2,3,1),
            intArrayOf(3,4,1)
        ), n = 4, k = 2)
    )
}

fun networkDelayTime(times: Array<IntArray>, n: Int, k: Int): Int {
    val map = hashMapOf<Int, MutableList<Pair<Int, Int>>>()
    times.forEach { time ->
        map.getOrPut(time[0]) { mutableListOf() }.add(time[1] to time[2])
    }

    val queue = PriorityQueue<Pair<Int, Int>>( compareBy { (node, time) -> time })
    queue.add(k to 0)
    val visited = hashSetOf<Int>()
    var totalTime = 0
    while(queue.isNotEmpty()) {
        val (node, time) = queue.poll()
        if (node in visited) {
            continue
        }
        visited.add(node)
        totalTime = Math.max(time, totalTime)
        map[node]?.let { list ->
            list.forEach { (neighborNode, weight) ->
                if (neighborNode !in visited) {
                    queue.add(neighborNode to time + weight)
                }
            }
        }
    }

    return if (visited.size == n) totalTime else -1
}