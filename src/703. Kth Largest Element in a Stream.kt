import java.util.PriorityQueue

fun main() {
    var obj = KthLargest(3, intArrayOf(4, 5, 8, 2))
    var param_1 = obj.add(10)
    println(param_1)
}

class KthLargest(val k: Int, nums: IntArray) {

    val queue = PriorityQueue<Int>()

    init {
        nums.forEach { num ->
            add(num)
        }
    }

    fun add(`val`: Int): Int {
        queue.offer(`val`)
        if (queue.size > k) {
            queue.poll()
        }
        return queue.peek()
    }

}