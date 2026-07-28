package neetcode_12_greedy

fun main() {
    println(
        maxSubArray(
//            intArrayOf(2,-3,4,-2,2,1,-1,4)
            intArrayOf(-1)
        )
    )
}

/**
 * https://neetcode.io/problems/maximum-subarray/question?list=neetcode150
 */
fun maxSubArray(nums: IntArray): Int {
    var curSum = 0
    var max = nums[0]
    nums.forEach { num ->
        if (curSum < 0) {
            curSum = 0
        }
        curSum += num
        max = Math.max(max, curSum)
    }
    return max
}