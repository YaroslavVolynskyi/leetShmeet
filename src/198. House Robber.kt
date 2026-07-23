import kotlin.math.max

fun main() {
//    var arr = intArrayOf(2,7,9,3,1)
//    println(rob(arr))
//    println(robDynamicApproach(arr))

    println(letterCombinations2("23"))
}

fun letterCombinations2(digits: String): List<String> {
    val result = mutableListOf<String>()
    val map = mapOf<Char, String>(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )
    val sb = StringBuilder()
//    for (d in digits) {
        track2(sb, digits, result, 0, map)
//    }
    return result
}

fun track2(sb: StringBuilder, digits: String, result: MutableList<String>, index: Int, map: Map<Char,String>) {
    if (sb.length == digits.length) {
        result.add(sb.toString())
        return
    }

    var currentLetters = map[digits[index]]!!
    for (c in currentLetters) {
        sb.append(c)
        track2(sb, digits, result, index + 1, map)
        sb.deleteCharAt(sb.length - 1)
    }
}

fun rob(nums: IntArray): Int {
    var memo = IntArray(nums.size) { i -> -1}
    return robFrom(0, memo, nums)
}

fun robFrom(houseIndex: Int, memo: IntArray, nums: IntArray): Int {
    println("houseIndex=$houseIndex, memo= ${memo.contentToString()}")
    if (houseIndex >= nums.size) {
        return 0
    }
    if (memo[houseIndex] != -1) {
        println("houseIndex from cache = $houseIndex")
        return memo[houseIndex]
    } else {
        var thisPlusNext = nums[houseIndex] + robFrom(houseIndex + 2, memo, nums)
        var next = robFrom(houseIndex + 1, memo, nums)
        var max = Math.max(
            thisPlusNext,
            next
        )
        memo[houseIndex] = max
        println("houseIndex=${houseIndex}, thisPlusNext=$thisPlusNext, next=$next, max=$max")
        return max
    }
}

fun robDynamicApproach(nums: IntArray): Int {
    if (nums.isEmpty()) return 0
    if (nums.size == 1) return nums[0]
    var memo = IntArray(nums.size) { i ->
        when (i) {
            nums.size - 1 -> nums[nums.size - 1]
            nums.size - 2 -> max(nums[nums.size - 1], nums[nums.size - 2])
            else -> -1
        }
    }
    for (i in nums.size - 3 downTo 0) {
        var thisPlusNext = nums[i] + memo[i + 2]
        var next = memo[i + 1]
        var max = max(thisPlusNext, next)
        memo[i] = max
    }

    return memo[0]
}
