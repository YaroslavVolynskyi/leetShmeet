package preparation

fun main() {
    val cache = LRUCache<Int>(5)
    for (i in 0 .. 10) {
        cache.put(i, i+10)
    }

    println(cache.get(8))
    println(cache.get(8))
    cache.put(45, 90)
    println(cache.get(6))
    println(cache.get(6))
}

class LRUCache<T>(
    val capacity: Int
) {
    class Node<T>(val key: Int, var value: T?) {
        var prev: Node<T>? = null
        var next: Node<T>? = null
    }

    private val head: Node<T> = Node(0, null)
    private val tail: Node<T> = Node(0, null)
    private val map = mutableMapOf<Int, Node<T>>()

    init {
        head.next = tail
        tail.prev = head
    }

    fun put(key: Int, value: T) {
        map[key]?.let {
            removeNode(it)
        }
        val newNode = Node(key, value)
        if (map.size == capacity && !map.contains(key)) {
            tail.prev?.let {
                removeNode(it)
                map.remove(it.key)
            }
        }
        map[key] = newNode
        addToTop(newNode)
    }

    fun get(key: Int): T? {
        val node = map[key]
        node?.let {
            removeNode(it)
            addToTop(it)
        }
        return node?.value
    }

    /**
     * nodeToRemove.prev          ->         nodeToRemove.next
     * prev               -> nodeToRemove -> next
     */
    private fun removeNode(nodeToRemove: Node<T>) {
        nodeToRemove.prev?.next = nodeToRemove.next
        nodeToRemove.next?.prev = nodeToRemove.prev
    }

    /**
     *               newTopNode.prev = head
     *               newTopNode.next = head.next
     *               head.next.prev = newTopNode
     *               head.next = newTopNode
     *  head      -> newTopNode              ->  head.next
     */
    private fun addToTop(newTopNode: Node<T>) {
        newTopNode.prev = head
        newTopNode.next = head.next
        head.next?.prev = newTopNode
        head.next = newTopNode
    }

    override fun toString(): String {
        val sb = StringBuilder()
        var current = head.next
        while (current != null && current.value != null) {
            sb.append("${current.value} ")
            current = current.next
        }
        return sb.toString()
    }
}
