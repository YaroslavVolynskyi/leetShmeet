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
    val map = mutableMapOf<TreeNode, MutableList<TreeNode>>()
    val queue = ArrayDeque<TreeNode>()
    if (root == null || target == null) {
        return listOf()
    }
    if (k == 0) {
        return listOf(target.`val`)
    }
    queue.add(root)
    while (queue.isNotEmpty()) {
        val node = queue.removeFirst()
        node.left?.let { left ->
            map.getOrPut(node) { mutableListOf() }.add(left)
            map.getOrPut(left) { mutableListOf() }.add(node)
            queue.add(left)
        }
        node.right?.let { right ->
            map.getOrPut(node) { mutableListOf() }.add(right)
            map.getOrPut(right) { mutableListOf() }.add(node)
            queue.add(right)
        }
    }

    val visited = mutableSetOf<TreeNode>()
    queue.clear()
    queue.add(target)
    val nodesList = mutableListOf<Int>()
    var distance = 0
    while (queue.isNotEmpty() && distance != k) {
        repeat(queue.size) {
            val node = queue.removeFirst()
            visited.add(node)
            val neighbors = map[node]
            if (!neighbors.isNullOrEmpty()) {
                queue.addAll(neighbors.filter { it !in visited })
            }
        }
        distance++
        if (distance == k) {
            return queue.toList().map { it.`val` }
        }
    }

    return nodesList
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null

    override fun toString(): String {
        return "$`val`"
    }
}
