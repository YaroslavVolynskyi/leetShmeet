package l210

/**
 * https://leetcode.com/problems/course-schedule-ii/description/
 */

fun main() {
    val res = findOrder(4,
        arrayOf(
            intArrayOf(1,0),
            intArrayOf(2,0),
            intArrayOf(3,1),
            intArrayOf(3,2)
        )
    )
    println(res.contentToString())

    val res2 = findOrder(2, arrayOf())
    println(res2.contentToString())

    println(
        findOrder(2, arrayOf(intArrayOf(1, 0)))
            .contentToString()
    )

    println()
}

fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
    val map = mutableMapOf<Int, MutableList<Int>>()
    val secondMap = mutableMapOf<Int, MutableList<Int>>()
    for (i in 0 .. numCourses - 1) {
        map[i] = mutableListOf()
        secondMap[i] = mutableListOf()
    }
    prerequisites.forEach { prereq ->
        map[prereq[0]]!!.add(prereq[1])
        secondMap[prereq[1]]!!.add(prereq[0])
    }
    val order = mutableListOf<Int>()
    val queue = ArrayDeque<Int>()
    map.filter { entry -> entry.value.isEmpty() }.keys.forEach {
        queue.add(it)
    }
    val indegree = IntArray(numCourses)
    map.forEach { entry ->
        indegree[entry.key] = entry.value.size
    }

    while (queue.isNotEmpty()) {
        val current = queue.removeFirst()
        order.add(current)
        secondMap[current]?.forEach { next ->
            indegree[next]--
            if (indegree[next] == 0) {
                queue.add(next)
            }
        }
//        map.forEach { entry ->
//            if (entry.value.remove(current) && entry.value.isEmpty()) {
//                queue.add(entry.key)
//            }
//        }
    }

    return if (numCourses == order.size) order.toIntArray() else intArrayOf()
}
