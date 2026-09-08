package l863

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/description/
 */

fun main() {
    val node5 = TreeNode(5)
    val root = TreeNode(3).apply {
        left = node5.apply {
            left = TreeNode(6)
            right = TreeNode(2).apply {
                left = TreeNode(7)
                right = TreeNode(4)
            }
        }
        right = TreeNode(1).apply {
            left = TreeNode(0)
            right = TreeNode(8)
        }
    }
    println(
        distanceK(root = root, target = node5, k = 2)
    )
    println()
}

fun distanceK(root: TreeNode?, target: TreeNode?, k: Int): List<Int> {
    return emptyList()
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
