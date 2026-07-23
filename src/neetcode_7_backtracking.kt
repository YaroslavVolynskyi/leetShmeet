fun main() {
//    val res = subsets2(intArrayOf(1, 2, 3))
//    println(res)

//    val res = combinationSum3(candidates= intArrayOf(1,2,3,4,5), target=7)
//    res.forEach { println(it) }
//    val res = permute2(intArrayOf(1,2,3))
//    res.forEach { println(it) }
//    val res = subsetsWithDup(intArrayOf(1, 2, 1))
//    res.forEach { println(it) }
//    val res = generateParenthesis(3)
//    res.forEach { println(it) }

    val res = partition("aab")
    res.forEach { println(it) }
}

/**
 * https://neetcode.io/problems/palindrome-partitioning/question?list=neetcode150
 */
fun partition(s: String): List<List<String>> {
    val result = mutableListOf<List<String>>()
    val current = StringBuilder()
    part3(result, s, current, mutableListOf())
    return result
}

fun part3(result: MutableList<List<String>>, s: String, current: StringBuilder, currentList: MutableList<String>) {
    val currentVal = current.toString()
    if (currentVal == currentVal.reversed()) {
        currentList.add(currentVal)
    }

}

/**
 * https://neetcode.io/problems/generate-parentheses/question?list=neetcode150
 */
fun generateParenthesis(n: Int): List<String> {
    val result = mutableListOf<String>()
    val sb = StringBuilder()
    gen3(0, 0, n, result, sb)
    return result
}

fun gen3(open: Int, close: Int, n: Int, result: MutableList<String>, sb: StringBuilder) {
    if (sb.length == n * 2) {
        result.add(sb.toString())
        return
    }
    if (open < n) {
        sb.append("(")
        gen3(open + 1, close, n, result, sb)
        sb.deleteCharAt(sb.length - 1)
    }
    if (close < open) {
        sb.append(")")
        gen3(open, close + 1, n, result, sb)
        sb.deleteCharAt(sb.length - 1)
    }
}

/**
 * https://neetcode.io/problems/subsets-ii/question?list=neetcode150
 */
fun subsetsWithDup(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    nums.sort()
    trackDup(result, nums, mutableListOf(), 0)
    return result
}

fun trackDup(result: MutableList<List<Int>>, nums: IntArray, currentList: MutableList<Int>, index: Int) {
    result.add(currentList.toList())
    if (index >= nums.size ) {
        return
    }

    for (i in index .. nums.size - 1) {
        if (i > index && nums[i - 1] == nums[i]) {
            continue
        }
        currentList.add(nums[i])
        trackDup(result, nums, currentList, i + 1)
        currentList.removeLast()
    }
}

/**
 * https://neetcode.io/problems/permutations/question?list=neetcode150
 */
fun permute2(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    val visited = BooleanArray(nums.size) {false}
    combs2(nums, result, mutableListOf(), visited)
    return result
}

fun combs2(nums: IntArray, result: MutableList<List<Int>>, current: MutableList<Int>, visited: BooleanArray) {
    if (current.size == nums.size) {
        result.add(current.toList())
        return
    }

    for (i in 0 .. nums.size - 1) {
        if (visited[i]) {
            continue
        }
        current.add(nums[i])
        visited[i] = true
        combs2(nums, result, current, visited)
        current.removeLast()
        visited[i] = false
    }
}


/**
 * https://neetcode.io/problems/combination-target-sum-ii/history?list=neetcode150&submissionIndex=2
 */
fun combinationSum3(candidates: IntArray, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    candidates.sort()
    combinations3(candidates, target, mutableListOf(), 0, result)
    return result
}

fun combinations3(nums: IntArray, target: Int, currentList: MutableList<Int>, index: Int, result: MutableList<List<Int>>) {
    val sum = currentList.sum()
    if (sum == target) {
        result.add(currentList.toList())
        return
    } else if (sum > target || index >= nums.size) {
        return
    }

    for (i in index .. nums.size - 1) {
        if (i > index && nums[i - 1] == nums[i]) {
            continue
        }
        currentList.add(nums[i])
        combinations3(nums, target, currentList, i + 1, result)
        currentList.removeLast()
    }
}

/**
 * https://neetcode.io/problems/combination-target-sum/question?list=neetcode150
 */
fun combinationSum(nums: IntArray, target: Int): List<List<Int>> {
    val result = mutableListOf<List<Int>>()

    combinations2(nums, target, result, mutableListOf(), 0)
    return result
}

fun combinations2(nums: IntArray, target: Int, result: MutableList<List<Int>>, current: MutableList<Int>, index: Int) {
    val sum = current.sum()
//    result.add(current.toList())

    if (sum == target) {
        result.add(current.toList())
    } else if (sum > target || index >= nums.size) {
        return
    }

    for (i in index .. nums.size - 1) {
        current.add(nums[i])
        combinations2(nums, target, result, current, i)
        current.removeLast()
    }
}

/**
 * https://neetcode.io/problems/subsets/question?list=neetcode150
 */
fun subsets2(nums: IntArray): List<List<Int>> {
    val result = mutableListOf<List<Int>>()
    track(result, nums, mutableListOf(), 0)
    return result
}

fun track(result: MutableList<List<Int>>, nums: IntArray, currentList: MutableList<Int>, index: Int) {
    result.add(currentList.toList())
    if (index >= nums.size ) {
        return
    }

    for (i in index .. nums.size - 1) {
        currentList.add(nums[i])
        track(result, nums, currentList, i + 1)
        currentList.removeLast()
    }
}