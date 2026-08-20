package trees104

fun main() {

    val root = TreeNode(3).apply {
        left = TreeNode(9)
        right = TreeNode(20).apply {
            left = TreeNode(15)
            right = TreeNode(7)
        }
    }

    println(
        depth(root)
    )

    println()
}

//fun balanced(root: TreeNode?): Boolean {
//
//}

fun depth(node: TreeNode?): Int {
    if (node == null) {
        return 0
    }

    val left = depth(node.left)
    val right = depth(node.right)

    return Math.max(
        left,
        right
    ) + 1
}

fun isBalanced(root: TreeNode?): Boolean {

    fun depth(node: TreeNode?): Int {
        if (node == null) {
            return 0
        }
        return Math.max(
            depth(node.left),
            depth(node.right)
        ) + 1
    }

    fun isBalancedNode(node: TreeNode?): Boolean {
        if (node == null) {
            return true
        }

        val heightLeft = depth(node.left)
        val heightRight = depth(node.right)
        return Math.abs(heightLeft - heightRight) <= 1 && isBalancedNode(node.left) && isBalancedNode(node.right)
    }

    return isBalancedNode(root)
}

/**
 * https://leetcode.com/problems/maximum-depth-of-binary-tree/description/
 */
fun maxDepth(root: TreeNode?): Int {
    fun depth(node: TreeNode?): Int {
        if (node == null) {
            return 0
        }
        return Math.max(
            depth(node.left),
            depth(node.right)
        ) + 1
    }

    return depth(root)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}