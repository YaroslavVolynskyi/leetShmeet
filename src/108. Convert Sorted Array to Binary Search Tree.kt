package l108

/**
 * https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/description/
 */

fun main() {

    val t = sortedArrayToBST(nums = intArrayOf(-10, -3, 0, 5, 9))
    println()
}

fun sortedArrayToBST(nums: IntArray): TreeNode? {
    if (nums.size == 0) {
        return null
    }

    return createNodeIndexed(nums, 0, nums.size - 1)
}

fun createNodeIndexed(nums: IntArray, start: Int, end: Int): TreeNode? {
    if (start > end) {
        return null
    }
    val mid = start + (end - start) / 2
    val root = TreeNode(nums[mid])
    if (nums.size > 1) {
        root.left = createNodeIndexed(nums, start, mid - 1)
        root.right = createNodeIndexed(nums, mid + 1, end)
    }
    return root
}

fun createNode(nums: IntArray): TreeNode? {
    if (nums.size == 0) {
        return null
    }
    val root = TreeNode(nums[nums.size / 2])
    if (nums.size > 1) {
        root.left = createNode(nums.sliceArray(0..nums.size / 2 - 1))
        root.right = createNode(nums.sliceArray(nums.size / 2 + 1..nums.size - 1))
    }
    return root
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
