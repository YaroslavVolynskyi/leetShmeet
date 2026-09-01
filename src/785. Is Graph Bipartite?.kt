package l785

fun main() {

    println(
        isBipartite(arrayOf(
            intArrayOf(1,2,3),
            intArrayOf(0,2),
            intArrayOf(0,1,3),
            intArrayOf(0,2)
        ))
    )
}

fun isBipartite(graph: Array<IntArray>): Boolean {
    val map = mutableMapOf<Int, MutableList<Int>>()
    graph.forEachIndexed { index, nodes ->
        map.getOrPut(index) { mutableListOf() }
        nodes.forEach { node ->
            map[index]!!.add(node)
        }
    }
    val visited = hashSetOf<Int>()
    val redNodes = hashSetOf<Int>()
    val blackNodes = hashSetOf<Int>()
    val queue = ArrayDeque<Int>()
    map.keys.forEach { key ->
        if (key in visited) {
            return@forEach
        }
        queue.add(key)
        while (queue.isNotEmpty()) {
            val node = queue.removeFirst()
            if (node in visited) {
                continue
            }
            visited.add(node)
            if (node !in redNodes && node !in blackNodes) {
                redNodes.add(node)
            }
            map[node]?.forEach { neighbor ->
                if (node in redNodes) {
                    if (neighbor in redNodes) {
                        return false
                    }
                    if (neighbor !in blackNodes) {
                        blackNodes.add(neighbor)
                        queue.add(neighbor)
                    }
                }
                if (node in blackNodes) {
                    if (neighbor in blackNodes) {
                        return false
                    }
                    if (neighbor !in redNodes) {
                        redNodes.add(neighbor)
                        queue.add(neighbor)
                    }
                }
            }
        }
    }

    return true
}