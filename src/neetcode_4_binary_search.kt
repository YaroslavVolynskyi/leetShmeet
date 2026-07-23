import kotlin.math.ceil

fun main() {
//    println(search(nums=intArrayOf(-1,0,2,4,6,8), target=4))
//    var matrix = arrayOf(
//        intArrayOf(1,2,4,8),
//        intArrayOf(10,11,12,13),
//        intArrayOf(14,20,30,40)
//    )
//    println(searchMatrix(matrix, 13))

//    println(minEatingSpeed2(piles=intArrayOf(1,4,3,2) , h=9))
//    val timeMap = TimeMap()
//    timeMap.set("alice", "happy", 1) // store the key "alice" and value "happy" along with timestamp = 1.
//    val res1 = timeMap.get("alice", 1) // return "happy"
//    val res2 = timeMap.get(
//        "alice",
//        2
//    ) // return "happy", there is no value stored for timestamp 2, thus we return the value at timestamp 1.
//    timeMap.set("alice", "sad", 3) // store the key "alice" and value "sad" along with timestamp = 3.
//    val res3 = timeMap.get("alice", 3) // return "sad"
}

///**
// * https://neetcode.io/problems/median-of-two-sorted-arrays/solution
// */
//fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
//    val m = nums1.size
//    val n = nums2.size
//    val mid = (m + n) / 2
//}

/**
 * https://neetcode.io/problems/time-based-key-value-store/question?list=neetcode150
 */
class TimeMap() {

    val map = mutableMapOf<String, MutableList<Pair<String, Int>>>()

    fun set(key: String, value: String, timestamp: Int) {
        val list = map[key] ?: mutableListOf()
        list.add(value to timestamp)
        map[key] = list
    }

    fun get(key: String, timestamp: Int): String {
        val list = map[key]
        if (list != null) {
            var left = 0
            var right = list.size - 1
            var res = ""
            while (left <= right) {
                var mid = (left + right) / 2
                if (list[mid].second <= timestamp) {
                    res = list[mid].first
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
            return res
        }
        return ""
    }
}


/**
 * https://neetcode.io/problems/eating-bananas/question?list=neetcode150
 */
fun minEatingSpeed2(piles: IntArray, h: Int): Int {
    var maxSpeed = piles.max()
    var minSpeed = 1
    while (minSpeed < maxSpeed) {
        var midSpeed = (maxSpeed + minSpeed) / 2
        var time = 0
        piles.forEach { time += ceil(it.toDouble() / midSpeed).toInt() }
        if (time > h) {
            minSpeed = midSpeed + 1
        } else {
            maxSpeed = midSpeed
        }
    }

    return minSpeed
}


/**
 * https://neetcode.io/problems/search-2d-matrix/question?list=neetcode150
 */
fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
    var startRow = 0
    var endRow = matrix.size - 1
    while (startRow <= endRow) {
        var mid = (startRow + endRow) / 2
        var first = matrix[mid][0]
        var last = matrix[mid][matrix[mid].size - 1]
        if (target in first..last) {
            var res = search(matrix[mid], target)
            return res != -1
        } else if (target < first) {
            endRow = mid - 1
        } else if (target > last) {
            startRow = mid + 1
        }
    }

    return false
}

///**
// * https://neetcode.io/problems/median-of-two-sorted-arrays/question?list=neetcode150
// */
//fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
//
//}

/**
 * https://neetcode.io/problems/binary-search/history?submissionIndex=4
 */
fun search(nums: IntArray, target: Int): Int {
    var left = 0
    var right = nums.size - 1
    while (left <= right) {
        var mid = (left + right) / 2
        if (nums[mid] == target) {
            return mid
        } else if (nums[mid] < target) {
            left = mid + 1
        } else {
            right = mid - 1
        }
    }
    return -1
}