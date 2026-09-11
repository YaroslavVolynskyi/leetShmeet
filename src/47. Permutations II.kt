package l47

/**
 * https://leetcode.com/problems/permutations-ii/description/
 */

fun main() {
    println(
        permuteUnique(nums = intArrayOf(1, 1, 2))
    )
    println()
}

fun permuteUnique(nums: IntArray): List<List<Int>> {
    val list = mutableListOf<List<Int>>()
    nums.sort()
    val used = BooleanArray(nums.size) { i -> false }
    permute(list, mutableListOf(), nums,  used)
    return list
}

fun permute(list: MutableList<List<Int>>, currentList: MutableList<Int>, nums: IntArray, used: BooleanArray) {
    if (currentList.size == nums.size) {
        list.add(currentList.toList())
        return
    }
    for (i in 0 .. nums.size - 1) {
        if (used[i] || (i > 0 && nums[i - 1] == nums[i] && !used[i - 1])) {
            continue
        }
        used[i] = true
        currentList.add(nums[i])

        permute(list, currentList, nums, used)

        used[i] = false
        currentList.removeLast()
    }
}
