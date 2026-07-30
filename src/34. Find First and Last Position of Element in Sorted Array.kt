package arrays

fun main() {
    println(
        searchRange(
//        intArrayOf(5,7,7,8,8,10), 8
            intArrayOf(2, 2), 2
        ).contentToString()
    )
}

fun searchRange(nums: IntArray, target: Int): IntArray {
    fun lowerBound(): Int {
        var left = 0
        var right = nums.size
        var result = -1
        while (left < right) {
            var midIndex = (left + right) / 2
            var mid = nums[midIndex]
            if (mid == target) {
                result = midIndex
                right = midIndex
            } else if (mid < target) {
                left = midIndex + 1
            } else {
                right = midIndex
            }
        }
        return result
    }
    fun upperBound(): Int {
        var left = 0
        var right = nums.size
        var result = -1
        while (left < right) {
            var midIndex = (left + right) / 2
            var mid = nums[midIndex]
            if (mid == target) {
                result = midIndex
                left = midIndex + 1
            } else if (mid < target) {
                left = midIndex + 1
            } else {
                right = midIndex
            }
        }
        return result
    }
    var lower = lowerBound()
    var upper = upperBound()

    return intArrayOf(lower, upper)
}