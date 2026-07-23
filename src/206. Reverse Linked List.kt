
fun main() {
    var head = ListNode(5).apply {
        next = ListNode(4).apply {
            next = ListNode(3).apply {
                next = ListNode(2).apply {
                    next = ListNode(1)
                }
            }
        }
    }
    printList(head)
    var reversedHead = reverseListRecursive(head)
    println()
    printList(reversedHead)
}

fun reverseList(head: ListNode?): ListNode? {
//    var temp = head
//    var cur = head?.next
//    var next = cur?.next
//    head?.next = null
//
//    while(cur != null) {
//        cur?.next = temp
//        temp = cur
//        cur = next
//        next = next?.next
//    }
//
//    return temp
    var prev: ListNode? = null
    var cur = head
    var next = cur?.next
    while(cur != null) {
        cur?.next = prev
        prev = cur
        cur = next
        next = next?.next
    }

    return prev
}


fun reverseListRecursive(head: ListNode?): ListNode? {
    if (head == null || head.next == null) return head

    val newHead = reverseListRecursive(head.next)
    head.next?.next = head
    head.next = null

    return newHead
}

fun printList(head: ListNode?) {
    if (head != null) {
        print("${head.`val`}")
        if (head.next != null) {
            print(" -> ")
        }
        printList(head.next)
    }
}

class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

