package travelingsalesemen

fun main() {
    println(
        shortestPathLength(
//            graph = arrayOf(
//          /*0*/  intArrayOf(1),
//          /*1*/  intArrayOf(0,2,4),
//          /*2*/  intArrayOf(1,3,4),
//          /*3*/  intArrayOf(2),
//          /*4*/  intArrayOf(1,2)
            graph = arrayOf(
                intArrayOf(1, 2, 3),
                intArrayOf(0),
                intArrayOf(0),
                intArrayOf(0)
            )
        )
    )
}

/**
 * https://leetcode.com/problems/shortest-path-visiting-all-nodes/description/
 */
fun shortestPathLength(graph: Array<IntArray>): Int {
    val map = hashMapOf<Int, MutableList<Int>>()
    graph.forEachIndexed { index, array ->
        map[index] = mutableListOf()
        array.forEach { map[index]!!.add(it) }
    }

    val queue = ArrayDeque<Pair<Int, Set<Int>>>() // node where currently standing to set of already visited nodes
    val visitedStates = mutableSetOf<Pair<Int, Set<Int>>>()
    map.keys.forEach {
        queue.add(it to setOf(it))
    }
    var steps = 0
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val (currentNode, visitedSet) = queue.removeFirst()
            if (visitedSet.size == map.size) {
                return steps
            }
            map[currentNode]?.forEach { neighbor ->
                val newVisitedSet = visitedSet + neighbor
                val newState = neighbor to newVisitedSet
                if (newState !in visitedStates) {
                    visitedStates.add(newState)
                    queue.add(newState)
                }
            }
        }
        steps++
    }
    return steps
}






//fun shortestPathLength(graph: Array<IntArray>): Int {
//    val map = hashMapOf<Int, MutableList<Int>>()
//    graph.forEachIndexed { index, array ->
//        map[index] = mutableListOf()
//        array.forEach { map[index]!!.add(it) }
//    }
//
//    val visitedStates = hashSetOf<Pair<Int, Set<Int>>>()
//    val queue = ArrayDeque<Pair<Int, Set<Int>>>()
//    map.keys.forEach {
//        queue.add(it to setOf(it))
//        visitedStates.add(it to setOf(it))
//    }
//    var steps = 0
//    while (queue.isNotEmpty()) {
//        repeat(queue.size) {
//            val (node, collectedNodesSet) = queue.removeFirst()
//            if (collectedNodesSet.size == map.size) {
//                return steps
//            }
//            map[node]?.forEach { neighbor ->
//                val nextCollected = collectedNodesSet + neighbor      // new set = old + neighbor
//                val state = neighbor to nextCollected
//                if (state !in visitedStates) {
//                    visitedStates.add(state)
//                    queue.add(state)
//                }
//            }
//        }
//        steps++
//    }
//
//    return steps
//}

//fun bfs(map: Map<Int, List<Int>>, startNode: Int, state: Map<Int, MutableSet<Int>>): Int {
//
//}

//fun bfs(nodesCount: Int, node: Int, visitedNodes: MutableSet<Int>, path: MutableList<Int>, map: Map<Int, List<Int>>): Int {
//    val queue = ArrayDeque<Int>()
//    queue.add(node)
//    var steps = 0
//    while (queue.isNotEmpty()) {
//        if (visitedNodes.size == nodesCount) {
//            println(path.toString())
//            return steps
//        }
//        repeat(queue.size) {
//            val currentNode = queue.removeFirst()
//            path.add(currentNode)
//            visitedNodes.add(currentNode)
//            map[currentNode]?.forEach { neighbor ->
//                queue.add(neighbor)
//            }
//        }
//        steps++
//    }
//    return steps
//}

