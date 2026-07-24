package binarysearchtree

fun main() {
    var root = BinarySearchTree(5)
//    root.apply {
//        insert(9)
//        insert(7)
//        insert(6)
//        insert(1)
//        insert(4)
//        insert(10)
//    }

//    val leaf = root.search(7)
//    root.insert(9)
//    root.insert(7)

//    root.insert(7)
//    root.insert(6)
//    root.insert(9)
//    root.insert(1)
//    root.insert(3)
//    root.insert(2)
//    root.insert(4)

    root.apply {
        insert(5)
        insert(7)
        insert(9)
        insert(6)
    }
    val r = root.delete(7)
    println()
}

class BinarySearchTree<T: Comparable<T>>(
    var value: T
): Comparable<BinarySearchTree<T>> {
    var left: BinarySearchTree<T>? = null
    var right: BinarySearchTree<T>? = null

    fun insert(newValue: T): Boolean {
        if (newValue == value) {
            return false
        }
        val newNode = BinarySearchTree(newValue)

        if (newValue > value) {
            if (right != null) {
                return right!!.insert(newValue)
            } else {
                right = newNode
                return true
            }
        }
        if (newValue < value) {
            if (left != null) {
                return left!!.insert(newValue)
            } else {
                left = newNode
                return true
            }
        }
        return false
    }

    fun search(searchedValue: T): BinarySearchTree<T>? {
        if (searchedValue == value) {
            return this
        } else if (searchedValue > value && right != null) {
            return right?.search(searchedValue)
        } else if (searchedValue < value && left != null) {
            return left?.search(searchedValue)
        } else {
            return null
        }
    }

    fun delete(valueToDelete: T): BinarySearchTree<T>? {
        if (valueToDelete == value) {
            if (right != null && left != null) {
                val min = right!!.min()
                value = min
                right = right!!.delete(min)
                return this
            }
            return left ?: right
        } else if (valueToDelete > value && right != null) {
            right = right!!.delete(valueToDelete)
            return this
        } else if (valueToDelete < value && left != null) {
            left = left!!.delete(valueToDelete)
            return this
        }
        return this
    }

    private fun min(): T {
        var node = this
        while(node.left != null) {
            node = node.left!!
        }
        return node.value
    }

    override fun compareTo(other: BinarySearchTree<T>): Int {
        return value.compareTo(other.value)
    }
}
