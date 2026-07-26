package linkedlist

import java.awt.List
import java.util.PriorityQueue

fun main() {
//    val head = ListNode(0).apply {
//        next = ListNode(4).apply {
//            next = ListNode(6).apply {
//                next = ListNode(10)
//            }
//        }
//    }
//    val head2 = ListNode(1).apply {
//        next = ListNode(3).apply {
//            next = ListNode(5).apply {
//                next = ListNode(7)
//            }
//        }
//    }
//    val reversed = reverseListPointers(head)
//    printList(reversed)
//    printList(reversed)

//    val nums1 = intArrayOf(1,2,3,0,0,0)
//    merge(
//        nums1, m = 3, nums2 = intArrayOf(2,5,6), n = 3
//    )
//    println(nums1.contentToString())

//    val l = mergeTwoLists(head, head2)
//    printList(l)

    val head = ListNode(0).apply {
        val n4 = ListNode(4)
        next = n4.apply {
            next = ListNode(6).apply {
                next = ListNode(9).apply {
//                    next = n4
                }
            }
        }
    }

    val head2 = ListNode(1).apply {
        val n4 = ListNode(2)
        next = n4.apply {
            next = ListNode(3).apply {
                next = ListNode(5).apply {
                    next = ListNode(7)
                }
            }
        }
    }
    // 75321 + 9640
//    println(hasCycle(head))

//    removeNthFromEnd(head, 4).printList()

//    addTwoNumbers(head, head2).printList()
//    mergeKLists(
//        arrayOf(
//            intArrayOf(1, 2, 4).toListNode(),
//            intArrayOf(1, 3, 5).toListNode(),
//            intArrayOf(3, 6).toListNode(),
//        )
//    ).printList()
//    head.printList()

//    reverseKGroup(
//        intArrayOf(1, 2, 3, 4, 5, 6).toListNode(), 3
//    ).printList()
//
//    println()
}

/**
 * https://neetcode.io/problems/reverse-nodes-in-k-group/question?list=neetcode150
 *        prev  current next
 *  1      2       3     4    5 6
 */
fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    return null
}

/**
 * https://neetcode.io/problems/merge-k-sorted-linked-lists/question?list=neetcode150
 */
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val newHead = ListNode(-1)
    var currentPointer: ListNode? = newHead
    val queue = PriorityQueue<ListNode> (compareBy { it.`val` })
    lists.forEach {
        queue.add(it)
    }
    while (queue.isNotEmpty()) {
        val currentNode = queue.poll()
        currentPointer?.next = currentNode
        currentPointer = currentNode
        if (currentNode.next != null) {
            queue.add(currentNode.next)
        }
        newHead.next.printList()
        println()
    }
    return newHead.next
}

private fun IntArray.toListNode(): ListNode? {
    val head = ListNode(-1)
    var headPointer: ListNode? = head
    for (i in indices) {
        headPointer?.next = ListNode(this[i])
        headPointer = headPointer?.next
    }
    return head.next
}

/**
 * https://neetcode.io/problems/add-two-numbers/question?list=neetcode150
 */
fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
    var head1 = l1
    var head2 = l2
    val newHead = ListNode(0)
    var newHeadPointer: ListNode? = newHead
    var carryOver = 0
    while (head1 != null || head2 != null) {
        var res = 0
        head1?.let { res += it.`val` }
        head2?.let { res += it.`val` }
        if (carryOver > 0) {
            res += carryOver
            carryOver = 0
        }
        if (res >= 10) {
            carryOver = 1
            res -= 10
        }
        newHeadPointer?.next = ListNode(res)
        newHeadPointer = newHeadPointer?.next
        head1 = head1?.next
        head2 = head2?.next
    }
    if (carryOver != 0) {
        newHeadPointer?.next = ListNode(carryOver)
    }

    return newHead.next
}

private fun add(l1: ListNode?, l2: ListNode?, prev: Int): Pair<Int, Int> {
    val res = (l1?.`val` ?:0) + (l2?.`val` ?:0) + prev
    return if (res >= 10) {
//        ListNode(res - 10) to 1
        (res - 10) to 1
    } else {
//        ListNode(res) to 0
        res to 0
    }
}

/**
 * https://neetcode.io/problems/remove-node-from-end-of-linked-list/question?list=neetcode150
 */
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
    // n from the end == (size - n) from start

    var fast = head
    repeat(n) {
        fast = fast?.next
    }
    var nThFromEnd: ListNode? = ListNode(0).apply { next = head }
    if (fast == null) {
        return head?.next
    }
    while (fast != null) {
        fast = fast.next
        nThFromEnd = nThFromEnd?.next
    }
    nThFromEnd?.next = nThFromEnd.next?.next

    return head
}

/**
 * https://neetcode.io/problems/linked-list-cycle-detection/question?list=neetcode150
 */
fun hasCycle(head: ListNode?): Boolean {
    var slow = head
    var fast = head?.next
    while (slow != fast || fast != null) {
        if (fast == null) {
            return false
        }
        if (slow == fast) {
            return true
        }
        slow = slow?.next
        fast = fast.next?.next
    }
    return false
}

/**
 * https://neetcode.io/problems/merge-two-sorted-linked-lists/question?list=neetcode150
 *
 * 0  4  6  null
 * 1  3  5   7   null
 */
fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    var head1 = list1
    var head2 = list2
    val newHead: ListNode = ListNode(0)
    var currentHead: ListNode? = newHead
    while (head2 != null && head1 != null) {
        if ((head1?.`val` ?: 0) < (head2?.`val` ?: 0)) {
            currentHead?.next = head1
            head1 = head1?.next
        } else {
            currentHead?.next = head2
            head2 = head2?.next
        }
        currentHead = currentHead?.next
    }

    currentHead?.next = head1 ?: head2

    return newHead.next
}

fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
    var currentIndex = m + n - 1
    var currentNum2Index = n - 1
    var currentNum1Index = m - 1
    while (currentIndex > 0) {
        if (nums2[currentNum2Index] > nums1[currentNum1Index]) {
            nums1[currentIndex] = nums2[currentNum2Index]
            currentNum2Index--
        } else {
            nums1[currentIndex] = nums1[currentNum1Index]
            currentNum1Index--
        }
        currentIndex--
    }
}

/**
 * https://neetcode.io/problems/reverse-a-linked-list/question?list=neetcode150
 *
 *  node(0) -> node(1) -> node(2) -> node(3) -> null
 *
 *               node(2)     node(2).next = newHead(3).next = node(2)
 *               node(2).next.next = node(2)
 *               node(2).next = null
 */

fun reverseListPointers(head: ListNode?): ListNode? {

///      prev   current  next
    //   0      1         2      3
    var prev: ListNode? = null
    var current = head
    var next = current?.next
    while(current != null) {
        current.next = prev
        prev = current
        current = next
        next = next?.next
    }
    return prev
}

fun reverseList(head: ListNode?): ListNode? {
    if (head == null || head.next == null) {
        return head
    }
    val newHead = reverseList(head.next)
    head.next?.next = head
    head.next = null

    return newHead
}


private fun printList0(head: ListNode?) {
    head?.let {
        print("${it.`val`} ")
        printList0(it.next)
    }
    println()
}

private fun ListNode?.printList() {
    this?.let {
        print("${it.`val`} ")
        it.next.printList()
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}