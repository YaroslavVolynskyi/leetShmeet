package l103

import l94.TreeNode

/**
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 */

fun main() {
//    val root = TreeNode(3).apply {
//        left = TreeNode(9)
//        right = TreeNode(20).apply {
//            left = TreeNode(15)
//            right = TreeNode(7)
//        }
//    }
    val root = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(4)
            right = TreeNode(5).apply {
                left = TreeNode(6)
                right = TreeNode(7)
            }
        }
        right = TreeNode(3).apply {
            right = TreeNode(8).apply {
                left = TreeNode(9)
            }
        }
    }
    println(
        zigzagLevelOrderQueueByLevel(root = root)
    )
    println()
}

// [1] [3, 2] [4, 5, 8] [9 7 6]

fun zigzagLevelOrderQueueByLevel(root: TreeNode?): List<List<Int>> {
    val list = mutableListOf<List<Int>>()
    if (root == null) {
        return list
    }
    val queue = ArrayDeque<TreeNode>()
    queue.add(root)
    var isLeftRight = true
    while (queue.isNotEmpty()) {
        val levelList = mutableListOf<Int>()
        repeat(queue.size) {
            val node = queue.removeFirst()
            levelList.add(node.`val`)
            node.left?.let { queue.addLast(it) }
            node.right?.let { queue.addLast(it) }
        }
        list.add(if (isLeftRight) levelList else levelList.reversed())
        isLeftRight = !isLeftRight
    }

    return list
}



fun zigzagLevelOrderQueueSize(root: TreeNode?): List<List<Int>> {
    val list = mutableListOf<List<Int>>()
    val queue = ArrayDeque<TreeNode>()
    if (root == null) {
        return list
    }
    queue.addLast(root)
    var isLeftRight = true
    while (queue.isNotEmpty()) {
        val levelList = mutableListOf<Int>()
        repeat(queue.size) {
            val node = queue.removeFirst()
            levelList.add(node.`val`)
            node.left?.let {
                queue.addLast(it)
            }
            node.right?.let {
                queue.addLast(it)
            }
        }
        list.add(if (isLeftRight) levelList else levelList.reversed())
        isLeftRight = !isLeftRight
    }

    return list
}

fun zigzagLevelOrderBFS(root: TreeNode?): List<List<Int>> {
    val list = mutableListOf<List<Int>>()
    val map = mutableMapOf<Int, MutableList<Int>>()
    var currentLevel = 0
    var currentNode = root
    val queue = ArrayDeque<Pair<Int, TreeNode>>() // level node
    while (currentNode != null || queue.isNotEmpty()) {
        if (currentNode != null) {
            queue.addLast(currentLevel to currentNode)
            currentNode = currentNode.left
            currentLevel++
        } else {
            val (level, node) = queue.removeLast()
            currentNode = node
            currentLevel = level
            map.getOrPut(currentLevel) { mutableListOf() }.add(currentNode.`val`)
            currentNode = currentNode.right
            currentLevel++
        }
    }
    var isLeftRight = true
    for (i in 0 .. map.size - 1) {
        map[i]?.let { levelList ->
            if (isLeftRight) {
                list.add(levelList)
            } else {
                list.add(levelList.reversed())
            }
        }
        isLeftRight = !isLeftRight
    }
    return list
}

fun zigzagLevelOrder(root: TreeNode?): List<List<Int>> {
    val list = mutableListOf<List<Int>>()
    val map = mutableMapOf<Int, MutableList<Int>>()
    explore(map, 0, root)
    var isLeftRight = true
    for (i in 0 .. map.size - 1) {
        map[i]?.let { levelList ->
            if (isLeftRight) {
                list.add(levelList)
            } else {
                list.add(levelList.reversed())
            }
        }
        isLeftRight = !isLeftRight
    }
    return list
}

private fun explore(map: MutableMap<Int, MutableList<Int>>, currentLevel: Int, currentNode: TreeNode?) {
    if (currentNode == null) {
        return
    }
    map.getOrPut(currentLevel) { mutableListOf() }.add(currentNode.`val`)
    explore(map, currentLevel + 1, currentNode.left)
    explore(map, currentLevel + 1, currentNode.right)
}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
