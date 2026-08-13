package twoddp

fun main() {
//    println(
//        uniquePathsDP(3, 6)
//    )

//    println(
//        longestCommonSubsequence("cat", "crabt")
//    )

//    println(
//        maxProfit(intArrayOf(1,3,4,0,4))
//    )

//    println(
//        change(4, intArrayOf(1, 2, 3))
//    )

    println(
        findTargetSumWays(intArrayOf(2,2,2), 2)
    )

    println("qqq")
}

/**
 * https://neetcode.io/problems/target-sum/question?list=neetcode150
 */
fun findTargetSumWays(nums: IntArray, target: Int): Int {
    val map = mutableMapOf<Pair<Int, Int>, Int>()
    return targetSum(nums, target, 0, 0, map)
}

private fun targetSum(nums: IntArray, target: Int, currentSum: Int, index: Int, map: MutableMap<Pair<Int, Int>, Int>): Int {
    if (index == nums.size) {
        if (currentSum == target) {
            return 1
        } else {
            return 0
        }
    }
    if (map.contains(index to currentSum)) {
        return map[index to currentSum] ?: 0
    }

    val res = targetSum(nums, target, currentSum + nums[index], index + 1, map) +
            targetSum(nums, target, currentSum - nums[index], index + 1, map)
    map[index to currentSum] = res
    return res
}

/**
 * https://neetcode.io/problems/coin-change-ii/question?list=neetcode150
 */
fun change(amount: Int, coins: IntArray): Int {
    val map = mutableMapOf<Pair<Int, Int>, Int>()
    return changeRec(amount, coins, 0, amount, map)
}

fun changeRec(amount: Int, coins: IntArray, start: Int, remaining: Int, map: MutableMap<Pair<Int, Int>, Int>): Int {
    if (remaining == 0) {
        return 1
    }
    if (remaining < 0) {
        return 0
    }
    if (start == coins.size) {
        return 0
    }
    if (map.contains(start to remaining)) {
        return map[start to remaining] ?: 0
    }
    val res = changeRec(amount, coins, start, remaining - coins[start], map) +
            changeRec(amount, coins, start + 1, remaining, map)
    map[start to remaining] = res
    return res
}

fun change0(amount: Int, coins: IntArray): Int {
    val combinationsList = mutableListOf<List<Int>>()
    val currentCombination = mutableListOf<Int>()
    combinationsForChange(amount, coins, 0, combinationsList, currentCombination)
    return combinationsList.size
}

fun combinationsForChange(amount: Int, coins: IntArray, start: Int, combinationsList: MutableList<List<Int>>, currentCombination: MutableList<Int>) {
    val sum = currentCombination.sum()
    if (amount == sum) {
        combinationsList.add(currentCombination.toList())
        return
    } else if (sum > amount) {
        return
    }
    for (i in start .. coins.size - 1) {
        currentCombination.add(coins[i])
        combinationsForChange(amount, coins, i, combinationsList, currentCombination)
        currentCombination.removeLast()
    }
}

/**
 * https://neetcode.io/problems/buy-and-sell-crypto-with-cooldown/question?list=neetcode150
 */
fun maxProfit(prices: IntArray): Int {
//    return profit(prices, 0, false, 0, 0)
    val cache = mutableMapOf<Pair<Int, Boolean>, Int>() // key - Pair(index, canSell); value - profit
    return profitWithCache(prices, 0, false, cache)
}

fun profitWithCache(prices: IntArray, index: Int, canSell: Boolean,
                    cache: MutableMap<Pair<Int, Boolean>, Int>): Int {
    if (index >= prices.size) {
        return 0
    }

    val key = index to canSell
    cache[key]?.let {
        return it
    }

    val result: Int
    if (!canSell) {
        val buy = -prices[index] + profitWithCache(prices, index + 1, true, cache)
        val skip = profitWithCache(prices, index + 1, false, cache)
        result = Math.max(buy, skip)
    } else {
        val sell = prices[index] + profitWithCache(prices, index + 2, false, cache)
        val hold = profitWithCache(prices, index + 1, true, cache)
        result = Math.max(sell, hold)
    }
    cache[key] = result

    return result
}

fun profit(prices: IntArray, index: Int, canSell: Boolean, boughtFor: Int, profitSum: Int): Int {
    if (index >= prices.size) {
        return 0
    }
    if (!canSell) {
        return Math.max(
            profit(prices, index + 1, true, prices[index], profitSum),
            profit(prices, index + 1, false, 0, profitSum),
        )
    } else {
        val currentProfit = prices[index] - boughtFor
        if (currentProfit < 0) {
            return profit(prices, index + 1, true, boughtFor, profitSum)
        } else {
            val max = Math.max(
                currentProfit + profit(prices, index + 2, false, 0, profitSum + currentProfit),
                profit(prices, index + 1, true, boughtFor, profitSum),
            )
            return max
        }
    }
}

/**
 * https://neetcode.io/problems/longest-common-subsequence/question?list=neetcode150
 */
fun longestCommonSubsequence(text1: String, text2: String): Int {

    val m = text1.length
    val n = text2.length
    val cache = Array(m + 1) {
        IntArray(n + 1) { -1 }
    }

    fun dfs(i: Int, j: Int): Int {
        if (i == text1.length || j == text2.length) {
            return 0
        }
        if (cache[i][j] != -1) {
            return cache[i][j]
        }
        if (text1[i] == text2[j]) {
            cache[i][j] = 1 + dfs(i + 1, j + 1)
        } else {
            cache[i][j] = Math.max(
                dfs(i + 1, j),
                dfs(i, j + 1)
            )
        }
        return cache[i][j]
    }

    return dfs(0, 0)
}

/**
 * https://neetcode.io/problems/count-paths/question?list=neetcode150
 */
fun uniquePathsDP(m: Int, n: Int): Int {
    val cache = Array(m) {
        IntArray(n) { -1 }
    }
    cache[m - 1][n - 1] = 1
    for (i in m - 1 downTo 0) {
        for (j in n - 1 downTo 0) {
            if (i == m - 1 && j == n - 1) {
                continue
            }
            val down = if (i + 1 in 0 .. m - 1) {
                cache[i + 1][j]
            } else {
                0
            }
            val right = if (j + 1 in 0 .. n - 1) {
                cache[i][j + 1]
            } else {
                0
            }

            cache[i][j] = down + right
        }
    }
    return cache[0][0]
}

fun uniquePaths(m: Int, n: Int): Int {
    var startM = 0
    var startN = 0
    val cache = Array(m) {
        IntArray(n) { -1 }
    }
    return uniquePathsRec(m, n, startM, startN, cache)
}

fun uniquePathsRec(m: Int, n: Int, curM: Int, curN: Int, cache: Array<IntArray>): Int {
    if (curM == m - 1 && curN == n - 1) {
        return 1
    }
    if (curM < 0 || curM >= m || curN < 0 || curN >= n) {
        return 0
    }
    val downByOne = if (curM + 1 !in 0 .. m - 1 || curN !in 0 .. n - 1) {
        0
    } else if (cache[curM + 1][curN] != -1) {
        cache[curM + 1][curN]
    } else {
        cache[curM + 1][curN] = uniquePathsRec(m, n, curM + 1, curN, cache)
        cache[curM + 1][curN]
    }

    val rightByOne = if (curM !in 0 .. m - 1 || curN + 1 !in 0 .. n - 1 ) {
        0
    } else if (cache[curM][curN + 1] != -1) {
        cache[curM][curN + 1]
    } else {
        cache[curM][curN + 1] = uniquePathsRec(m, n, curM, curN + 1, cache)
        cache[curM][curN + 1]
    }

    return downByOne + rightByOne
}
