package lrucache2

fun main() {

    val cache = LruCache2<Int>(3)
    for (i in 0 .. 2) {
        cache.addElement(i, i + 10)
    }
    cache.addElement(0, 23)
    cache.addElement(5, 25)
    println()
}

class LruCache2<T>(val capacity: Int = 4) {

    private val map = mutableMapOf<Int, Node<T>>()
    private val head: Node<T> = Node(0, null)
    private val tail: Node<T> = Node(0, null)

    init {
        head.next = tail
        tail.prev = head
    }

    /**
     *     newValue - (1, 25)
     * head      ->   (1, 17) -> tail
     */
    fun addElement(key: Int, value: T) {
        map[key]?.let {
            removeFromList(it)
        }
        val newNode = Node(key, value)
        map[key] = newNode
        addToTop(newNode)
        if (map.size > capacity) {
            tail.prev?.let {
                map.remove(it.key)
                removeFromList(it)
            }
        }
    }

    fun getElement(key: Int): T? {
        val node = map[key]
        node?.let {
            removeFromList(it)
            addToTop(it)
            return it.value
        }
        return null
    }

    /**
     *                           nodeToRemove
     *    nodeToRemove.prev ->                  -> nodeToRemove.next
     */
    private fun removeFromList(nodeToRemove: Node<T>) {
        nodeToRemove.prev?.next = nodeToRemove.next
        nodeToRemove.next?.prev = nodeToRemove.prev
    }

    /**
     *              nodeToAdd
     * head   ->                 -> head.next
     */
    private fun addToTop(nodeToAdd: Node<T>) {
        nodeToAdd.prev = head
        nodeToAdd.next = head.next
        head.next?.prev = nodeToAdd
        head.next = nodeToAdd
    }

    class Node<T>(
        val key: Int,
        val value: T?
    ) {
        var prev: Node<T>? = null
        var next: Node<T>? = null
    }
}