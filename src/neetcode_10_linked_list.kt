package linkedlist

import java.awt.List

fun main() {
    val head = ListNode(0).apply {
        next = ListNode(4).apply {
            next = ListNode(6).apply {
                next = ListNode(10)
            }
        }
    }
    val head2 = ListNode(1).apply {
        next = ListNode(3).apply {
            next = ListNode(5).apply {
                next = ListNode(7)
            }
        }
    }
//    val reversed = reverseListPointers(head)
//    printList(reversed)
//    printList(reversed)

//    val nums1 = intArrayOf(1,2,3,0,0,0)
//    merge(
//        nums1, m = 3, nums2 = intArrayOf(2,5,6), n = 3
//    )
//    println(nums1.contentToString())

    val l = mergeTwoLists(head, head2)
    printList(l)
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


private fun printList(head: ListNode?) {
    head?.let {
        print("${it.`val`} ")
        printList(it.next)
    }
    println()
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}