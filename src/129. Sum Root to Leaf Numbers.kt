package l129

/**
 * https://leetcode.com/problems/sum-root-to-leaf-numbers/description/
 */

fun main() {
    val root = TreeNode(4).apply {
        left = TreeNode(9).apply {
            left = TreeNode(5)
            right = TreeNode(1)
        }
        right = TreeNode(0)
    }
    println(
        sumNumbers(root = root)
    )
    println()
}

fun sumNumbers(root: TreeNode?): Int {

    fun sum(node: TreeNode?, prefix: Int): Int {
        if (node == null) {
            return 0
        }
        val value = prefix * 10 + node.`val`
        if (node.left == null && node.right == null) {
            return value
        }
        val left = sum(node.left, value)
        val right = sum(node.right, value)
        return left + right
    }

    return sum(root, 0)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
