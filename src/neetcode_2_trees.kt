import kotlin.math.abs
import kotlin.math.max

fun main() {
//    val root = TreeNode(5).apply {
//        left = TreeNode(3).apply {
//            left = TreeNode(1).apply {
//                right = TreeNode(2)
//            }
//            right = TreeNode(4)
//        }
//        right = TreeNode(8).apply {
//            left = TreeNode(7)
//            right = TreeNode(9)
//        }
//    }
//    printTree(root)
//    println("depth = ${maxDepth(root)}")
//    res = 0
//    println("d = ${diameterOfBinaryTree(root)}")
//    println(isBalanced(root))

//    printTree(root)
//    println(lowestCommonAncestor(root, root.left?.left?.right, root.left?.right)?.`val`)

//    val root = TreeNode(1).apply {
//        left = TreeNode(2).apply {
//            left = TreeNode(4)
//            right = TreeNode(5)
//        }
//        right = TreeNode(3).apply {
//            left = TreeNode(6)
//            right = TreeNode(7)
//        }
//    }
//    println(kthSmallest(root, 3))

    val root = TreeNode(4).apply {
        left = TreeNode(3).apply {
            left = TreeNode(2)
        }
        right = TreeNode(5)
    }

    val root2 = TreeNode(1).apply {
        left = TreeNode(2).apply {
            left = TreeNode(6)
            right = TreeNode(7)
        }
        right = TreeNode(3).apply {
            left = TreeNode(5).apply {
                left = TreeNode(8)
            }
            right = TreeNode(4).apply {
                right = TreeNode(9)
            }
        }
    }
//    val preorder = preorderTree(root2)
//    val inorder = inorderTree(root2)
//    println("preorder - ${preorder.contentToString()}")
//    println("inorder  - ${inorder.contentToString()}")
//
//    val constructedRoot = constructTree(preorder, inorder)
//    val preorder3 = preorderTree(constructedRoot)
//    val inorder3 = inorderTree(constructedRoot)
//    println("preorder3 - ${preorder3.contentToString()}")
//    println("inorder3  - ${inorder3.contentToString()}")

    val root3 = TreeNode(1)

    val ser = serialize(root2)
    println(ser)
    var des = deserialize(ser)
    println(preorderTree(des).contentToString())
}

/**
 * https://neetcode.io/problems/serialize-and-deserialize-binary-tree/question?list=neetcode150
 *
 */

fun serialize(root: TreeNode?): String {
    val res = mutableListOf<String>()

    fun dfs(node: TreeNode?) {
        if (node == null) {
            res.add("N")
            return
        }
        res.add(node.`val`.toString())
        dfs(node.left)
        dfs(node.right)
    }

    dfs(root)
    return res.joinToString(",")
}

// Decodes your encoded data to tree.
fun deserialize(data: String): TreeNode? {
    val vals = data.split(",")
    var i = 0

    fun dfs(): TreeNode? {
        if (vals[i] == "N") {
            i++
            return null
        }
        val node = TreeNode(vals[i].toInt())
        i++
        node.left = dfs()
        node.right = dfs()
        return node
    }

    return dfs()
}


fun serialize000(root: TreeNode?): String {
    val preorder = preorderTree(root)
    val inorder = inorderTree(root)
    return StringBuilder()
        .append(preorder.contentToString())
        .append(inorder.contentToString())
        .toString()
}

// Decodes your encoded data to tree.
fun deserialize000(data: String): TreeNode? {
    val preorderList = mutableListOf<Int>()
    val inorderList = mutableListOf<Int>()
    var preorderString = data.substring(data.indexOf("[") + 1, data.indexOf("]"))
    val inorderString = data.substring(data.indexOf("]") + 2, data.length - 1)
    var i = 0
    while (i < preorderString.length) {
        var currentVal = 0
        if (preorderString[i] == ' ') {
            i++
        }
        while (i < preorderString.length && preorderString[i] != ',') {
            currentVal = currentVal * 10 + preorderString[i].digitToInt()
            i++
        }
        preorderList.add(currentVal)
        i++
    }

    i = 0
    while (i < inorderString.length) {
        var currentVal = 0
        if (inorderString[i] == ' ') {
            i++
        }
        while (i < inorderString.length && inorderString[i] != ',') {
            currentVal = currentVal * 10 + inorderString[i].digitToInt()
            i++
        }
        inorderList.add(currentVal)
        i++
    }

    return constructTree(preorderList.toIntArray(), inorderList.toIntArray())
}

fun constructTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty() || inorder.isEmpty()) {
        return null
    }
    val root = TreeNode(preorder[0])

    val rootVal = preorder[0]
    val rootIndexInorder = inorder.indexOf(rootVal)

    val leftInorder = inorder.slice(0 .. rootIndexInorder - 1).toIntArray()
    val leftPreorder = preorder.slice(1 .. rootIndexInorder).toIntArray()

    val rightInorder = inorder.slice(rootIndexInorder + 1 .. inorder.size - 1).toIntArray()
    val rightPreorder = preorder.slice(rootIndexInorder + 1 .. preorder.size - 1).toIntArray()

    root.left = constructTree(leftPreorder, leftInorder)
    root.right = constructTree(rightPreorder, rightInorder)
    return root
}

fun preorderTree(root: TreeNode?): IntArray {
    val preorderArray = mutableListOf<Int>()
    preorder(root, preorderArray)
    return preorderArray.toIntArray()
}
fun preorder(root: TreeNode?, list: MutableList<Int>) {
    if (root == null) {
        return
    }
    list.add(root.`val`)
    preorder(root.left, list)
    preorder(root.right, list)
}

fun inorderTree(root: TreeNode?): IntArray {
    val inorderArray = mutableListOf<Int>()
    inorder(root, inorderArray)
    return inorderArray.toIntArray()
}
fun inorder(root: TreeNode?, list: MutableList<Int>) {
    if (root == null) {
        return
    }
    inorder(root.left, list)
    list.add(root.`val`)
    inorder(root.right, list)
}

/**
 * https://neetcode.io/problems/binary-tree-from-preorder-and-inorder-traversal/question?list=neetcode150
 * preorder = [1,2,3,4], inorder = [2,1,3,4]
 */
fun buildTree(preorder: IntArray, inorder: IntArray): TreeNode? {
    if (preorder.isEmpty() || inorder.isEmpty()) {
        return null
    }
    val root = TreeNode(preorder[0])
    val mid = inorder.indexOf(preorder[0])
    //left part of tree: 0 .. mid - 1; right part of tree: mid + 1 .. inorder.size - 1

    root.left = buildTree(
        preorder = preorder.slice(1 .. mid).toIntArray(),
        inorder = inorder.slice(0 .. mid - 1).toIntArray()
        )

    root.right = buildTree(
        preorder = preorder.slice(1 + mid .. preorder.size - 1).toIntArray(),
        inorder = inorder.slice(mid + 1 .. inorder.size - 1).toIntArray()
    )

    return root
}

/**
 * https://neetcode.io/problems/kth-smallest-integer-in-bst/question?list=neetcode150
 *
 */
private var count: Int = 0
private var result: Int = 0

fun kthSmallest(root: TreeNode?, k: Int): Int {
    count = k
    result = 0
    traverse(root, k)
    return result
}

fun traverse(node: TreeNode?, k: Int) {
    if (node == null) {
        return
    }
    traverse(node.left, k)
    count--
    if (count == 0) {
        result = node.`val`
        return
    }
    traverse(node.right, k)
}

/**
 * https://neetcode.io/problems/valid-binary-search-tree/question?list=neetcode150
 */
fun isValidBST(root: TreeNode?): Boolean {
    return isValidNode(root)
}

fun isValidNode(root: TreeNode?, max: Int = Int.MAX_VALUE, min: Int = Int.MIN_VALUE): Boolean {
    if (root == null) {
        return true
    }
    return root.`val` < max && root.`val` > min
            && isValidNode(root?.left, root.`val`, min)
            && isValidNode(root?.right, max, root.`val`)
}

/**
 * https://neetcode.io/problems/count-good-nodes-in-binary-tree/solution
 */
private var goodCount = 0

fun goodNodes(root: TreeNode?): Int {
    goodCount = 0
    lookForGood(root, Int.MIN_VALUE)
    return goodCount
}

fun lookForGood(node: TreeNode?, maxSoFar: Int) {
    if (node == null) {
        return
    }
    if (node.`val` >= maxSoFar) {
        goodCount++
    }
    val newMax = max(maxSoFar, node.`val`)
    lookForGood(node?.left, newMax)
    lookForGood(node?.right, newMax)
}

/**
 * https://neetcode.io/problems/binary-tree-right-side-view/question?list=neetcode150
 */
fun rightSideView(root: TreeNode?): List<Int> {
    return levelOrder(root).map { list -> list.last() }.toList()
}

/**
 * https://neetcode.io/problems/level-order-traversal-of-binary-tree/history?submissionIndex=3
 */
fun levelOrder(root: TreeNode?): List<List<Int>> {
    val map = mutableMapOf<Int, MutableList<Int>>()
    traverse(root, map, 0)
    return map.values.toList()
}

fun traverse(root: TreeNode?, map: MutableMap<Int, MutableList<Int>>, currentLevel: Int) {
    if (root == null) {
        return
    } else {
        val currentLevelList = map[currentLevel] ?: mutableListOf()
        currentLevelList.add(root.`val`)
        map[currentLevel] = currentLevelList
        traverse(root.left, map, currentLevel + 1)
        traverse(root.right, map, currentLevel + 1)
    }
}

/**
 * https://neetcode.io/problems/lowest-common-ancestor-in-binary-search-tree/solution
 */
fun lowestCommonAncestor(root: TreeNode?, p: TreeNode?, q: TreeNode?): TreeNode? {
    if (root == null || p == null || q == null) {
        return null
    }
    if (root.`val` > p.`val` && root.`val` > q.`val`) {
        return lowestCommonAncestor(root?.left, p, q)
    } else if (root.`val` < p.`val` && root.`val` < q.`val`) {
        return lowestCommonAncestor(root.right, p, q)
    } else {
        return root
    }
}

/**
 * https://neetcode.io/problems/subtree-of-a-binary-tree/question?list=neetcode150
 */
fun isSubtree(root: TreeNode?, subRoot: TreeNode?): Boolean {
    if (root == null) {
        return false
    }
    if (subRoot == null) {
        return true
    }
    return isSameTree(root, subRoot)
            || isSubtree(root?.left, subRoot)
            || isSubtree(root?.right, subRoot)
}

/**
 * https://neetcode.io/problems/same-binary-tree/history?submissionIndex=4
 */
fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
    if (q == null && p == null) {
        return true
    }
    if ((p != null && q == null)
        ||(p == null && q!= null) ) {
        return false
    } else return (p?.`val` == q?.`val`)
            && isSameTree(p?.left, q?.left)
            && isSameTree(p?.right, q?.right)
}

/**
 * https://neetcode.io/problems/balanced-binary-tree/question?list=neetcode150
 */
fun isBalanced(root: TreeNode?): Boolean {
    if (root == null) {
        return true
    }
    var leftDepth = depth(root?.left)
    var rightDepth = depth(root?.right)
    return abs(leftDepth - rightDepth) <= 1
            && isBalanced(root.left)
            && isBalanced(root.right)
}

/**
 * https://neetcode.io/problems/binary-tree-diameter/solution
 *
 */
var res = 0
fun diameterOfBinaryTree(root: TreeNode?): Int {
    depth(root)
    return res
}

fun depth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    var left = depth(root.left)
    var right = depth(root.right)
    res = max(res, left + right)

    return 1 + max(left, right)
}

/**
 * https://neetcode.io/problems/depth-of-binary-tree/question?list=neetcode150
 */
fun maxDepth(root: TreeNode?): Int {
    if (root == null) {
        return 0
    }
    return 1 + max(maxDepth(root?.left), maxDepth(root?.right))
}

/**
 * https://neetcode.io/problems/invert-a-binary-tree/question?list=neetcode150
 *
 */
fun invertTree(root: TreeNode?): TreeNode? {
    var temp = root?.left
    root?.left = root.right
    root?.right = temp
    root?.left?.let {
        invertTree(root.left)
    }
    root?.right?.let {
        invertTree(root.right)
    }
    return root
}

fun printTree(treeNode: TreeNode?) {
    treeNode?.left?.let {
        printTree(treeNode.left)
    }
    println(treeNode?.`val`)
    treeNode?.right?.let {
        printTree(treeNode.right)
    }
}

class TreeNode(val `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}