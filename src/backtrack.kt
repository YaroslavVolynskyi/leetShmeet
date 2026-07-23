import kotlin.math.cos

fun main() {
    // 1.
//    permutations("123").forEach {
//        println(it)
//    }
    // 2.
//    generateParentheses(3).forEach {
//        println(it)
//    }
    //3.
//    letterCombinations("23").forEach {
//        println(it)
//    }

//    var list = allSums(intArrayOf(2, 3, 6, 7),7)
//    var list2 = allSums(intArrayOf(2, 3, 5), 8)
//    list2.forEach {
//        print("[ ")
//        it.forEach { el -> print("$el ") }
//        print("]")
//        println()
//    }

//    val nums = intArrayOf(1 , 2 , 3)
//    var res = permute(nums)
//    res.forEach { el ->
//        el.forEach { print(it) }
//        println()
//    }

//    val res = combine(4, 2)
//    val res = subsets(intArrayOf(1, 2, 3))
//    res.forEach { el ->
//        el.forEach { print(it) }
//        println()
//    }
//
//    val memo = IntArray(5) { -1 }

//    var ar = intArrayOf(1,100,1,1,1,100,1,1,100,1)
//    println("minCost = ${minCostClimbingStairs(ar)}")

//    var res = combinationSum2(intArrayOf(10,1,2,7,6,1,5), target = 8)
    var res = combinationSum2(intArrayOf(1, 1, 1,  6), target = 8)
    res.forEach {
        it.forEach { el -> print(el) }
        println()
    }
}

/**
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * https://leetcode.com/problems/combination-sum-ii/description/
 * 40. Combination Sum II
 */
fun combinationSum2(candidates: IntArray, target: Int): List<List<Int>> {
    val combinations = mutableListOf<List<Int>>()
    val currentCombination = mutableListOf<Int>()
    candidates.sort()
    backtrackSum2(candidates, target, combinations, currentCombination, 0)
    return combinations
}

fun backtrackSum2(candidates: IntArray, target: Int, results: MutableList<List<Int>>,
                  currentCombination: MutableList<Int>, startIndex: Int) {
    if (target == 0) {
        results.add(currentCombination.toList())
        return
    } else if (target < 0) {
        return
    }

    for (i in startIndex .. candidates.size - 1) {
        if (i > startIndex && candidates[i] == candidates[i - 1]) {
            continue
        }
        val current = candidates[i]
        currentCombination.add(current)
        backtrackSum2(candidates, target - current, results, currentCombination, i + 1)
        currentCombination.removeLast()
    }
}

fun tribonacci(n: Int): Int {
    val array = IntArray(n + 1) { i ->
        when (i) {
            0 -> 0
            1 -> 1
            2 -> 1
            else -> -1
        }
    }
    for (i in 3 .. n) {
        array[i] = array[i - 1] + array[i - 2] + array[i - 3]
    }

    return array[n]
}

/**
 * 746. Min Cost Climbing Stairs
 * https://leetcode.com/problems/min-cost-climbing-stairs/description/
 */

fun minCostClimbingStairs(cost: IntArray): Int {
    var totalCosts = IntArray(cost.size + 1) { -1 }
    totalCosts[0] = 0
    totalCosts[1] = 0
    for (i in 2 .. cost.size) {
        totalCosts[i] = Math.min(
            totalCosts[i - 1] + cost[i - 1],
            totalCosts[i - 2] + cost[i - 2]
        )
    }
    return totalCosts[totalCosts.size - 1]
}

/**
 * https://leetcode.com/problems/subsets/
 * 78. Subsets
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
fun subsets(nums: IntArray): List<List<Int>> {
    val results = mutableListOf<List<Int>>()
    val currentList = mutableListOf<Int>()
    subsetsCombs(results, currentList, nums, 0)
    return results
}

fun subsetsCombs(results: MutableList<List<Int>>, currentList: MutableList<Int>, nums: IntArray, startIndex: Int) {
    results.add(currentList.toList())
    for (i in startIndex .. nums.size - 1) {
        currentList.add(nums[i])
        subsetsCombs(results, currentList, nums, i + 1)
        currentList.removeLast()
    }
}

/**
 * 77. Combinations
 * https://leetcode.com/problems/combinations/description/
 *
 * Input: n = 4, k = 2
 * Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
 */
fun combine(n: Int, k: Int): List<List<Int>> {
    var results = mutableListOf<List<Int>>()

    val range = n
    val elementsInCombination = k
    val visited = BooleanArray(range) { i -> false }
    val currentComb = mutableListOf<Int>()
    combinations(results, range, elementsInCombination, currentComb, visited, 1)

    return results
}

fun combinations(results: MutableList<List<Int>>, range: Int, elementsInCombination: Int,
                 currentComb: MutableList<Int>, visited: BooleanArray, startIndex: Int) {
    if (currentComb.size == elementsInCombination) {
        results.add(currentComb.toList())
        return
    }
    for (i in startIndex .. range) {
//        if (visited[i - 1]) {
//            continue
//        }
        currentComb.add(i)
//        visited[i - 1] = true
        combinations(results, range, elementsInCombination, currentComb, visited,  i + 1)
//        visited[i - 1] = false
        currentComb.removeLast()
    }
}


fun permute(nums: IntArray): List<List<Int>> {
    val results = mutableListOf<List<Int>>()
    val currentList = mutableListOf<Int>()
    val visited = BooleanArray(nums.size) { i -> false }
    backtrack2(results, nums, currentList, visited)
    return results
}

fun backtrack2(results: MutableList<List<Int>>, nums: IntArray, currentList: MutableList<Int>, visited: BooleanArray) {
    if (currentList.size == nums.size) {
        results.add(currentList.toList())
    }

    for (i in nums.indices) {
        if (visited[i]) {
            continue
        }
        currentList.add(nums[i])
        visited[i] = true
        backtrack2(results, nums, currentList, visited)
        currentList.removeLast()
        visited[i] = false
    }
}

/**
 * 39. Combination Sum
 * https://leetcode.com/problems/combination-sum/description/
 * 4.
 * Combination Sum
 * Problem: Given an array of distinct positive integers candidates and a target integer target, return all unique combinations of candidates where the chosen numbers sum to target.
 * The same number may be chosen unlimited times. Two combinations are different only if the set of chosen numbers differs (order doesn't matter).
 * Example: candidates = [2, 3, 6, 7], target = 7
 * [[2, 2, 3], [7]]
 */
fun allSums(candidates: IntArray, target: Int): List<List<Int>> {
    var allSums = mutableListOf<List<Int>>()

    var currentCombination = mutableListOf<Int>()
    sumsVariations(candidates, 0, allSums, currentCombination, target)

    return allSums
}

fun sumsVariations(candidates: IntArray, startIndex: Int, allSums: MutableList<List<Int>>,
                   currentCombination: MutableList<Int>, currentTarget: Int) {
    if (currentTarget == 0) {
        allSums.add(currentCombination.toList())
        return
    } else if (currentTarget < 0) {
        return
    }

    for (i in startIndex .. candidates.size - 1) {
        val newTarget = currentTarget - candidates[i]
        currentCombination.add(candidates[i])
        sumsVariations(candidates, i, allSums, currentCombination, newTarget)
        currentCombination.removeLast()
    }
}


/**
 * 3.
 * Mapping:
 * 2 → "abc"
 * 3 → "def"
 * 4 → "ghi"
 * 5 → "jkl"
 * 6 → "mno"
 * 7 → "pqrs"
 * 8 → "tuv"
 * 9 → "wxyz"
 * Example: digits = "23"
 * ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 */
fun letterCombinations(input: String): List<String> {
    var combinations = mutableListOf<String>()
    var map = mutableMapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )
    // "23" ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
    var sb = StringBuilder()
    buildPhoneCombinations(input, 0, combinations, sb, map)

    return combinations
}

fun buildPhoneCombinations(input: String, slot: Int, combinations: MutableList<String>, sb: StringBuilder, map: Map<Char, String>) {
    if (sb.length == input.length) {
        combinations.add(sb.toString())
        return
    }
    var currentLettersArray = map[input[slot]]
    for (j in currentLettersArray!!.indices) {
        sb.append(currentLettersArray!![j])
        buildPhoneCombinations(input, slot + 1, combinations, sb, map)
        sb.deleteCharAt(sb.length - 1)
    }
}

/**
 * 2. valid combinations of brackets ammounts
 * ((()))
 * (()())
 * (())()
 * ()(())
 * ()()()
 */
fun generateParentheses(bracketsAmount: Int): List<String> {
    var combinations = mutableListOf<String>()
    var sb = StringBuilder()
    paranthesesBacktrack(0, 0, bracketsAmount, sb, combinations)
    return combinations
}

fun paranthesesBacktrack(usedOpenBrackets: Int, usedClosedBrackets: Int, bracketsAmount: Int, sb: StringBuilder, combinations: MutableList<String>) {
    if (sb.length == bracketsAmount * 2) {
        combinations.add(sb.toString())
        return
    }
    if (usedOpenBrackets < bracketsAmount) {
        sb.append("(")
        paranthesesBacktrack(usedOpenBrackets + 1, usedClosedBrackets, bracketsAmount, sb, combinations)
        sb.deleteCharAt(sb.length - 1)
    }
    if (usedClosedBrackets < usedOpenBrackets) {
        sb.append(")")
        paranthesesBacktrack(usedOpenBrackets, usedClosedBrackets + 1, bracketsAmount, sb, combinations)
        sb.deleteCharAt(sb.length - 1)
    }
}

/**
 * 1. string permutations of chars
 */
fun permutations(input: String): List<String> {
    var permutations = mutableListOf<String>()
    var used = BooleanArray(input.length) { i -> false }
    var chars = input.toCharArray()
    var sb = StringBuilder()
    backtrack(chars, used, permutations, sb)

    return permutations
}

fun backtrack(chars: CharArray, used: BooleanArray, permutations: MutableList<String>, sb: StringBuilder) {
    if (sb.length == chars.size) {
        permutations.add(sb.toString())
    }
    for (i in 0 .. chars.size - 1) {
        if (used[i]) {
            continue
        }
        sb.append(chars[i])
        used[i] = true
        backtrack(chars, used, permutations, sb)
        sb.deleteCharAt(sb.length - 1)
        used[i] = false
    }
}

















fun permutations0(input: String): List<String> {
    var results = mutableListOf<String>()
    var sb = StringBuilder()
    var chars = input.toCharArray()
    var used = BooleanArray(chars.size)

    backtrack0(sb, input, chars, used, results)

    return results
}

fun backtrack0(sb: StringBuilder, input: String, chars: CharArray, used: BooleanArray, results: MutableList<String>) {
    if (sb.length == input.length) {
        results.add(sb.toString())
        return
    }

    for (i in chars.indices) {
        if (used[i]) continue

        sb.append(chars[i])
        used[i] = true

        backtrack0(sb, input, chars, used, results)

        sb.deleteCharAt(sb.length - 1)
        used[i] = false
    }
}


//    for (i in 0 .. chars.size) {
//        sb.append(chars[i])
//
//    }
//
//    sb.append(chars[0])
//    sb.append(chars[1])
//    sb.append(chars[2])
//    sb.append(chars[3])
//    if (sb.length == input.length) {
//        results.add(sb.toString())
//    }
//
//    sb.append(chars[0])
//    sb.append(chars[1])
//    sb.append(chars[3]) //sb.append(chars[2])
//    sb.append(chars[4])
//    results.add(sb.toString())
//    //sb.append(chars[3])