import kotlin.math.max
import kotlin.math.min

fun main() {
    println(climbStairsRecursive(45))
}

///**
// * https://neetcode.io/problems/house-robber/question?list=neetcode150
// */
//fun rob2(nums: IntArray): Int {
//    for (i in 0 .. nums.size - 1) {
//
//    }
//    var sum = max(nums[])
//    return 0
//}

/**
 * https://neetcode.io/problems/min-cost-climbing-stairs/question?list=neetcode150
 */
fun minCostClimbingStairs2(cost: IntArray): Int {
    var totalCost = IntArray(cost.size + 1) { -1 }
    totalCost[0] = 0
    totalCost[1] = 0
    for (i in 2 .. totalCost.size - 1) {
        totalCost[i] = min(totalCost[i - 1] + cost[i - 1],
            totalCost[i - 2] + cost[i - 2])
    }

    return totalCost[totalCost.size-1]
}

/**
 * https://neetcode.io/problems/climbing-stairs/question?list=neetcode150
 */
fun climbStairsRecursive(n: Int): Int {
    if (n <= 2) {
        return n
    }
    return climbStairsRecursive(n - 1) + climbStairsRecursive(n - 2)
}

fun climbStairsArray(n: Int): Int {
    val array = IntArray(n + 1)
    array[0] = 0
    array[1] = 1
    array[2] = 2
    for (i in 2 .. n) {
        array[i] = array[i - 1] + array[i - 2]
    }
    return array[n]
}

fun climbStairs(n: Int): Int {
    // if (n <= 2) {
    //     return n
    // }
    // return climbStairs(n - 1) + climbStairs(n - 2)
    // if (n <= 2) {
    //     return n
    // }
    // val array = IntArray(n + 1)
    // array[1] = 1
    // array[2] = 2
    // for (i in 3 .. n) {
    //     array[i] = array[i - 1] + array[i - 2]
    // }
    // return array[n]
    if (n <= 2) {
        return n
    }
    var current = 2
    var previous = 1
    for (i in 3 .. n) {
        var temp = current
        current += previous
        previous = temp
    }
    return current
}

