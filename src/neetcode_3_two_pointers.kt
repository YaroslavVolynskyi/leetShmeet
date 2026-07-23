
import kotlin.collections.forEachIndexed
import kotlin.math.max
import kotlin.math.min

fun main() {
//    println(isPalindrome("Was it a car or a cat I saw"))
//    val res = threeSum2(intArrayOf(-1,-1,0,1,2,-1,-1,-4))
//    println(res.toString())
//    println(maxArea(intArrayOf(1,7,2,5,24,7,3,6)))
//    println(trap(intArrayOf(0,2,0,3,1,0,1,3,2,1)))
//    println(trap(intArrayOf(0,0,0,3,1,0,1,3,2,1)))
//    println(trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
    println(trap(intArrayOf(0,1,0,2,1,0, 0,0,0,0,0,0)))
}

/**
 * https://neetcode.io/problems/trapping-rain-water/question?list=neetcode150
 */
fun trap(height: IntArray): Int {
    var area = 0
    val maxLeft = IntArray(height.size)
    val maxRight = IntArray(height.size)
    maxLeft[0] = height[0]
    for (i in 1 .. maxLeft.size - 1) {
        maxLeft[i] = max(maxLeft[i - 1], height[i])
    }
    maxRight[maxRight.size - 1] = height[maxRight.size - 1]
    for (i in maxRight.size - 2 downTo 0) {
        maxRight[i] = max(maxRight[i + 1], height[i])
    }
    for (i in height.indices) {
        area += min(maxRight[i], maxLeft[i]) - height[i]
    }
    return area
}

/**
 * https://neetcode.io/problems/max-water-container/question?list=neetcode150
 * Input: height = [1,7,2,5,4,7,3,6]
 */
fun maxArea(heights: IntArray): Int {
    var left = 0
    var right = heights.size - 1
    var max = 0
    while (left < right) {
        max = max(max, (right - left) * min(heights[left], heights[right]))
        if (heights[left] <= heights[right]) {
            left++
        } else {
            right--
        }
    }
    return max
}

/**
 * https://neetcode.io/problems/three-integer-sum/question?list=neetcode150
 */
fun threeSum2(nums: IntArray): List<List<Int>> {
    val res = mutableListOf<List<Int>>()
    nums.sort()
    for (outer in 0 .. nums.size - 1) {
        if (outer > 0 && nums[outer] == nums[outer - 1]) {
            continue
        }
        var num = nums[outer]
        var left = outer + 1
        var right = nums.size - 1
        var target = -num
        while (left < right) {
            val sum = nums[left] + nums[right]
            if (sum == target) {
                res.add(mutableListOf(num, nums[left], nums[right]))
                left++
                right--
                while(left < right && nums[left] == nums[left - 1]) {
                    left++
                }
            } else if (sum < target) {
                left++
            } else {
                right--
            }
        }
    }
    return res
}

/**
 * https://neetcode.io/problems/two-integer-sum-ii/question?list=neetcode150
 * Input: numbers = [1,2,3,4], target = 3
 * Output: [1,2]
 */
fun twoSum2(numbers: IntArray, target: Int): IntArray {
    var left = 0
    var right = numbers.size - 1
    while (left < right) {
        val sum = numbers[left] + numbers[right]
        if (sum == target) {
            return intArrayOf(left + 1, right + 1)
        } else if (sum < target) {
            left++
        } else {
            right--
        }
    }
    return intArrayOf()
}

/**
 * https://neetcode.io/problems/is-palindrome/history?list=neetcode150&submissionIndex=1
 */
fun isPalindrome(s: String): Boolean {
    var left = 0
    var right = s.length - 1
    while (left < right) {
        while(left < right && !s[left].isLetterOrDigit()) {
            left++
        }
        while(left < right && !s[right].isLetterOrDigit()) {
            right--
        }
        if (s[left].lowercaseChar() != s[right].lowercaseChar()) {
            return false
        }
        left++
        right--
    }
    return true
}