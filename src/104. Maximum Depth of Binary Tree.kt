package trees104

fun main() {

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