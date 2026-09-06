package l94

/**
 * https://leetcode.com/problems/binary-tree-inorder-traversal/description/
 */

fun main() {
//    val root = TreeNode(1).apply {
//        right = TreeNode(2).apply {
//            left = TreeNode(3)
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
        inorderTraversal(root = root)
    )
    println()
}



fun inorderTraversal(root: TreeNode?): List<Int> {
    var list = mutableListOf<Int>()
    var currentNode = root
    val queue = ArrayDeque<TreeNode>()
    while (currentNode != null || queue.isNotEmpty()) {
        if (currentNode != null) {
            queue.addLast(currentNode)
            currentNode = currentNode.left
        } else {
            currentNode = queue.removeLast()
            list.add(currentNode.`val`)
            currentNode = currentNode.right
        }
    }

    return list
}
//[4, 2, 6, 5, 7, 1, 3, 9, 8]









//
//
//fun inorderTraversal(root: TreeNode?): List<Int> {
//    val queue = ArrayDeque<TreeNode>()
//    val list = mutableListOf<Int>()
//    var node = root
//    while (node != null || queue.isNotEmpty()) {
//        if (node != null) {
//            queue.addLast(node)
//            node = node.left
//        } else {
//            node = queue.removeLast()
//            list.add(node.`val`)
//            node = node.right
//        }
//    }
//
//    return list
//}

class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}
