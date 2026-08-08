package rand12

import kotlin.random.Random

fun main() {
    val s = Solution2(intArrayOf(1, 3, 2, 4))
    for (i in 0 .. 10) {
        println(s.pickIndex())
    }
}



class Solution2(val w: IntArray) {

    val prefixArray = IntArray(w.size)
    var sum = 0
    init {
        w.forEachIndexed { index, el ->
            sum += el
            prefixArray[index] = sum
        }
    }

    fun pickIndex(): Int {
        val target = Random.nextInt(from = 1, until = sum + 1)
        var left = 0
        var right = w.size - 1
        while (left < right) {
            val mid = (left + right) / 2
            if (prefixArray[mid] < target) {
                left = mid + 1
            } else {
                right = mid
            }
        }
        return left
    }
}

/**
 * 1 3 3 3 5 5 5 5 5
 */
class Solution(w: IntArray) {

    val list = mutableListOf<Int>()
    init {
        w.forEachIndexed { index, el ->
            repeat(el) {
                list.add(index)
            }
        }
    }

    fun pickIndex(): Int {
        return list[Random.nextInt(until = list.size)]
    }
}