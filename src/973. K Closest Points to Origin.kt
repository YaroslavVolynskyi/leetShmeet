import java.util.PriorityQueue

fun main() {
    var res = kClosest( arrayOf(intArrayOf(9,9), intArrayOf(12, 12), intArrayOf(3,3), intArrayOf(6,6)), k = 2)
    res.forEach { println(it.contentToString()) }
}

fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
    val heap = PriorityQueue<IntArray>(compareByDescending { point ->
        point[0]*point[0] + point[1]*point[1]
    })
    points.forEach { point ->
        heap.offer(point)
        if (heap.size > k) {
            heap.poll()
        }
    }
    return Array(heap.size) { heap.poll() }
}

