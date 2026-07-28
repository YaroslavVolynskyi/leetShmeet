package clonegraph

fun main() {
    val n1 = Node(1)
    val n2 = Node(2)
    val n3 = Node(3)
    val n4 = Node(4)
    n1.neighbors = arrayListOf(n2, n4)
    n2.neighbors = arrayListOf(n1, n3)
    n3.neighbors = arrayListOf(n2, n4)
    n4.neighbors = arrayListOf(n1, n3)

    val s = Solution()
    val cloned = s.cloneGraph(n1)
    println()
}
class Solution {

    fun cloneGraph(node: Node?): Node? {
        val cloned = hashMapOf<Node, Node>() // original, clone
        return dfs(node, cloned)
    }

    fun dfs(node: Node?, alreadyCloned: MutableMap<Node, Node>): Node? {
        if (alreadyCloned.contains(node)) {
            return alreadyCloned[node]
        } else if (node != null) {
            val clone = Node(node.`val`)
            alreadyCloned[node] = clone
            node.neighbors.forEach { neighbor ->
                neighbor?.let {
                    val neighborClone = dfs(it, alreadyCloned)
                    if (neighborClone != null) {
                        alreadyCloned[neighbor] = neighborClone
                        clone.neighbors.add(neighborClone)
                    }
                }
            }
            return clone
        }
        return node
    }
}

class Node(var `val`: Int) {
    var neighbors: ArrayList<Node?> = ArrayList<Node?>()
}