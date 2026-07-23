import java.util.PriorityQueue

fun main() {
    println(findKthLargest(intArrayOf(3,2,3,1,2,4,5,5,6), k = 4))
}

fun findKthLargest(nums: IntArray, k: Int): Int {
    val queue = PriorityQueue<Int>()
    nums.forEach { num ->
        queue.offer(num)
        if (queue.size > k) {
            queue.poll()
        }
    }
    return queue.peek()
}
