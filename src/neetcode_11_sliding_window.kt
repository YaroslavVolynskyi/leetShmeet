package slidingwindow

import java.util.PriorityQueue
import java.util.Stack

fun main() {
//    println(maxProfit(intArrayOf(10,1,5,6,7,1)))
    println(
        maxSlidingWindow(nums = intArrayOf(1,2,1,0,4,2,6), k = 3)
            .contentToString()
    )
}

/**
 * https://neetcode.io/problems/sliding-window-maximum/question?list=neetcode150
 */
fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
    val queue = PriorityQueue<Int>(compareByDescending { it })
    for (i in 0 .. k - 1) {
        queue.add(nums[i])
    }
    var result = IntArray(nums.size - k + 1)
    result[0] = queue.peek()
    var i = 0
    while (i < nums.size - k) {
        queue.remove(nums[i])
        queue.add(nums[i + k])
        result[i+1] = queue.peek()
        i++
    }

    return result
}

/**
 * https://neetcode.io/problems/buy-and-sell-crypto/solution
 */
fun maxProfit(prices: IntArray): Int {
    var buy = 0
    var sell = 1
    var maxProfit = 0

    while(sell < prices.size) {
        if (prices[sell] > prices[buy]) {
            var profit = prices[sell] - prices[buy]
            if (profit > maxProfit) {
                maxProfit = profit
            }
        } else {
            buy = sell
        }
        sell++
    }

    return maxProfit
}