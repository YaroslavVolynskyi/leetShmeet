package priority_queue

import java.util.PriorityQueue
import kotlin.math.sqrt
import kotlin.random.Random

fun main() {
//    val kthLargest = KthLargest(3, intArrayOf(14, 12, 8, 1, 2, 3, 3))
//    while (kthLargest.queue.isNotEmpty()) {
//        println(kthLargest.queue.poll())
//    }
//    println(kthLargest.add(3))   // return 3
//    println(kthLargest.add(5))   // return 3
//    println(kthLargest.add(6))  // return 3
//    println(kthLargest.add(7))  // return 5
//    println(kthLargest.add(8))  // return 6

//    println(lastStoneWeight(intArrayOf(2,3,6,2,4)))

//    kClosest(points = arrayOf(intArrayOf(0,2), intArrayOf(2,2)), k = 1)
//        .forEach { arr -> println(arr.contentToString()) }

//    println("kthLargest = " + findKthLargest(
//        intArrayOf(2,3,1,1,5,5,4), 3
//    ))

//    val s = mutableListOf<Int>()
//    val m = mutableMapOf<Int, Int>()
//    println("leastInterval " + leastInterval(charArrayOf('A','A','A','B','C'), n = 3))
//
//    val vowelSet = hashSetOf('p')

    println(maskify("sdfmsdmf789"))
}

fun maskify(cc: String): String {
    if (cc.length <= 4) {
        return cc
    }

    return cc.substring(0, cc.length - 4).map{'#'}.joinToString("") + cc.substring(cc.length - 4)
}

/**
 * https://neetcode.io/problems/task-scheduling/solution
 */
fun leastInterval(tasks: CharArray, n: Int): Int {
    return 0
}

/**
 * https://neetcode.io/problems/kth-largest-element-in-an-array/question?list=neetcode150
 */
fun findKthLargest(nums: IntArray, k: Int): Int {
    val queue = PriorityQueue<Int>( compareByDescending { it })
    nums.forEach { queue.add(it) }
    repeat(k - 1) {
        queue.poll()
    }
    return queue.poll()
}

/**
 * https://neetcode.io/problems/k-closest-points-to-origin/question?list=neetcode150
 */
fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
    val queue = PriorityQueue<IntArray>( compareBy { it ->
        sqrt(it[1].toDouble() * it[1] + it[0] * it[0])
    })
    points.forEach {
        queue.add(it)
    }
    var count = k
    var result = mutableListOf<IntArray>()
    while (count > 0) {
        result.add(queue.poll())
        count--
    }
    return result.toTypedArray()
}

/**
 * https://neetcode.io/problems/last-stone-weight/question?list=neetcode150
 */
fun lastStoneWeight(stones: IntArray): Int {
    val queue = PriorityQueue<Int>(compareByDescending { it })
    stones.forEach { queue.add(it) }
    while (queue.size != 1) {
        val first = queue.poll()
        val second = queue.poll()
        queue.add(Math.abs(first - second))
    }
    return queue.poll()
}

/**
 * https://neetcode.io/problems/kth-largest-integer-in-a-stream/question?list=neetcode150
 */
class KthLargest(val k: Int, val nums: IntArray) {

    val queue = PriorityQueue<Int>()
    init {
        nums.forEach { num ->
            add(num)
        }
    }

    fun add(`val`: Int): Int {
        queue.add(`val`)
//        if (queue.size > k) {
//            queue.remove()
//        }
        return queue.peek()
    }
}

class ValueStorage<T> {
    private val values = mutableListOf<T>()
    private val indexes = mutableMapOf<T, Int>()

    fun add(newValue: T): Boolean {
        if (indexes.containsKey(newValue)) {
            return false
        }
        indexes[newValue] = values.size
        values.add(newValue)
        return true
    }

    fun remove(valueToRemove: T): Boolean {
        val indexOfValueToRemove = indexes[valueToRemove]
        if (indexOfValueToRemove != null) {
            values[indexOfValueToRemove] = values.last()
            indexes[values.last()] = indexOfValueToRemove
            values.removeLast()
            indexes.remove(valueToRemove)
            return true
        }
        return false
    }

    fun random(): T {
        return values[Random.nextInt(until = values.size)]
    }
}

