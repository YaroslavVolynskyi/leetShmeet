package randomflipmattrix

import kotlin.random.Random

fun main() {
    val s = Solution2(4, 5)
    for (i in 0 .. 10) {
        s.flip()
    }
    s.reset()
}

class Solution2(val m: Int, val n: Int) {

    var target = m * n
    val map = mutableMapOf<Int, Int>()

    fun flip(): IntArray {
        val randomValue = Random.nextInt(until = target)
        val index = map.getOrDefault(randomValue, randomValue)
        target--
        map[randomValue] = map.getOrDefault(target, target)
        val i = index / n
        val j = index % n
        return intArrayOf(i, j)
    }

    fun reset() {
        target = m * n
        map.clear()
    }
}

class Solution(val m: Int, val n: Int) {

    var nextI = Random.nextInt(m)
    var nextJ = Random.nextInt(n)
    val used = hashSetOf<Pair<Int, Int>>()

//    var matrix = Array(m) { i ->
//        Array(n) { 0 }
//    }

    fun flip(): IntArray {
        while (
            nextI to nextJ !in used
            && nextI in (0 .. m - 1)
            && nextJ in (0 .. n - 1)
//            && matrix[nextI][nextJ] != 0
        ) {
            nextI = Random.nextInt(until = m)
            nextJ = Random.nextInt(until = n)
        }
//        matrix[nextI][nextJ] = 1
        used.add(nextI to nextJ)
        return intArrayOf(nextI, nextJ)
    }

    fun reset() {
        nextI = Random.nextInt(m)
        nextJ = Random.nextInt(n)
//        matrix = Array(m) { i ->
//            Array(n) { 0 }
//        }
        used.clear()
    }
}