//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
//https://leetcode.com/problems/find-the-duplicate-number/description/?envType=company&envId=google&favoriteSlug=google-thirty-days
//287. Find the Duplicate Number
    //                                       0  1  2  3  4  5  6  7  8
    println(findDuplicate(intArrayOf(8, 2, 3, 6, 5, 1, 3, 4, 7)))
}

/**
 * index   0 1 2 3 4 5
 * number  3 1 3 4 2 0
 *
 *
 */
//https://leetcode.com/problems/find-the-duplicate-number/description/?envType=company&envId=google&favoriteSlug=google-thirty-days
//287. Find the Duplicate Number
fun findDuplicate(nums: IntArray): Int {
    var slow = nums[0]
    var fast = nums[0]
    do {
        println("slow=$slow, fast=$fast")
        if (slow < nums.size) {
            slow = nums[slow]
        }
        if (fast < nums.size) {
            fast = nums[nums[fast]]
        }
    } while (slow != fast)
    println("slow=$slow, fast=$fast")
    slow = nums[0]
    println("second phase")
    while (slow != fast) {
        slow = nums[slow]
        fast = nums[fast]
        println("slow=$slow, fast=$fast")
    }

    return slow
}

fun findDuplicateSort(nums: IntArray): Int {
    nums.sort()
    for (i in 1 ..nums.size - 1) {
        if (nums[i] == nums[i - 1]) {
            return nums[i]
        }
    }
    return -1
}

