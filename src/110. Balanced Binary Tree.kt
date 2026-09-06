package l110

/**
 * https://leetcode.com/problems/balanced-binary-tree/description/
 */

fun main() {
    val root = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
//                .apply { right = TreeNode(44) }
        }
    }
    println(
        isBalanced(root = root)
    )
    println()
}

fun isBalanced(root: TreeNode?): Boolean {

    fun depth(node: TreeNode?): Int {
        if (node == null) {
            return 0
        }
        var depthLeft = depth(node.left)
        if (depthLeft == -1) {
            return -1
        }
        var depthRight = depth(node.right)
        if (depthRight == -1) {
            return -1
        }
        if (Math.abs(depthRight - depthLeft) > 1) {
            return -1
        }
        return Math.max(depthLeft, depthRight) + 1
    }

    return depth(root) != -1
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
