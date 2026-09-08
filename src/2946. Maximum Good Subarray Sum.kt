package l2946

/**
 * https://leetcode.com/problems/maximum-good-subarray-sum/description/
 */

fun main() {
    println(
        minimumSubarraySum(
//            nums = intArrayOf(1, 2, 3, 4, 5, 6),
//            nums = intArrayOf(2, 5, -100, 2, 9),
            nums = intArrayOf(1,3,2,4,1,2)
        )
    )
    println()
}

fun minimumSubarraySum(nums: IntArray): Long {
    var minSum = Long.MAX_VALUE
    val maxPrefixByValue = HashMap<Long, Long>()
    var prefixSum = 0L

    for (i in 0 .. nums.size - 1) {
        val value = nums[i].toLong()
        val prefixBefore = prefixSum

        prefixSum += value

        val prefixForSameValue = maxPrefixByValue[value]

        if (prefixForSameValue != null) {
            val subarraySum = prefixSum - prefixForSameValue
            if (subarraySum < minSum) {
                minSum = subarraySum
            }
        }

        if (prefixForSameValue == null || prefixBefore > prefixForSameValue) {
            maxPrefixByValue[value] = prefixBefore
        }
    }

    return if (minSum == Long.MAX_VALUE) 0 else minSum
}

fun maximumSubarraySum(nums: IntArray, k: Int): Long {
    val map = mutableMapOf<Long, Long>()
    var maxSum = Long.MIN_VALUE
    var prefixSum = 0L
    for (i in nums.indices) {
        val value = nums[i].toLong()

        val currentPrefix = map[value]
        if (currentPrefix == null || currentPrefix < prefixSum) {
            map[value] = prefixSum
        }
        prefixSum += value

        val prefix1 = map[value - k]
        if (prefix1 != null) {
            val sum = prefixSum - prefix1
            if (sum > maxSum) {
                maxSum = sum
            }
        }
        val prefix2 = map[value + k]
        if (prefix2 != null) {
            val sum = prefixSum - prefix2
            if (sum > maxSum) {
                maxSum = sum
            }
        }
    }
    return if (maxSum == Long.MIN_VALUE) 0 else maxSum
}









//fun maximumSubarraySum(nums: IntArray, k: Int): Long {
//    var maxSum = Long.MIN_VALUE
//    val prefixSumMap = mutableMapOf<Long, Long>()
//    var prefixSum = 0L
//    for (i in nums.indices) {
//        val value = nums[i].toLong()
//        val currentPrefix = prefixSumMap[value]
//        if (currentPrefix == null || prefixSum < currentPrefix) {
//            prefixSumMap[value] = prefixSum
//        }
//        prefixSum += value
//
//        val prefix1 = prefixSumMap[value - k]
//        if (prefix1 != null) {
//            val sum = prefixSum - prefix1
//            if (sum > maxSum) {
//                maxSum = sum
//            }
//        }
//
//        val prefix2 = prefixSumMap[value + k]
//        if (prefix2 != null) {
//            val sum = prefixSum - prefix2
//            if (sum > maxSum) {
//                maxSum = sum
//            }
//        }
//
//    }
//
//    return if (maxSum == Long.MIN_VALUE) 0L else maxSum
//}

//fun maximumSubarraySum(nums: IntArray, k: Int): Long {
//    var maxSum = Long.MIN_VALUE
//    for (i in 0 .. nums.size - 1) {
//        var sum = 0L
//        for (j in i .. nums.size - 1) {
//            sum += nums[j]
//            if (Math.abs(nums[j] - nums[i]) == k) {
//                if (sum > maxSum) {
//                    maxSum = sum
//                }
//            }
//        }
//    }
//
//    return if (maxSum == Long.MIN_VALUE) 0 else maxSum
//}
