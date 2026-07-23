import java.util.PriorityQueue
import kotlin.collections.forEach

fun main() {
    val res = canFinish(numCourses = 4, prerequisites =
        // course  0 is available
        // courses 2 and 1 become available after finishing course 0
        // course  3 becomes available after finishing 1 and 2
        arrayOf(
            // take course 0 if I want to take 1
            intArrayOf(1, 0),

            // take course 0 if I want to take 2
            intArrayOf(2, 0),

            // take course 1 if I want to take 3
            intArrayOf(3, 1),

            // take course 2 if I want to take 3
            intArrayOf(3, 2)
        )
    )
    println(res)
}

fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
    // graph with available courses. index - required course; list - indexes of courses required to unlock required course
    val graph = Array(numCourses) {
        mutableListOf<Int>()
    }
    for (prereq in prerequisites) {
        val requiredCourse = prereq[0]
        val oneOfCoursesNeededToUnlockRequired = prereq[1]
        graph[requiredCourse].add(oneOfCoursesNeededToUnlockRequired)
    }

    val queue = ArrayDeque<Int>()
    for (i in 0 .. graph.size - 1) {
        val listOfCourses = graph[i]
        if (listOfCourses.isEmpty()) {
            queue.add(i)
        }
    }

    var finishedCourses = mutableSetOf<Int>()
    if (queue.isEmpty()) {
        return false
    } else {
        while(queue.isNotEmpty()) {
            val finishedCourse = queue.removeFirst()
            finishedCourses.add(finishedCourse)
            for (i in graph.indices) {
                val listOfCourses = graph[i]
                listOfCourses.remove(finishedCourse)
                if (listOfCourses.isEmpty() && !finishedCourses.contains(i) && !queue.contains(i)) {
                    queue.add(i)
                }
            }
        }
    }

    return finishedCourses.size == numCourses
}
