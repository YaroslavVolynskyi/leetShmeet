
fun main() {
    var arr = intArrayOf(2,0,2,1,1,0)
    println(arr.contentToString())
    sortColors(arr)
    println(arr.contentToString())
}

fun sortColors(nums: IntArray): Unit {
    var pointerZero = 0
    var pointerTwo = nums.size - 1
    var current = 0
    while(current <= pointerTwo) {
        if (nums[current] == 2) {
            swap(nums, current, pointerTwo)
            pointerTwo--
        } else if (nums[current] == 1) {
            current++
        } else if (nums[current] == 0) {
            swap(nums, current, pointerZero)
            pointerZero++
            current++
        }
    }
}

fun swap(nums: IntArray, i: Int, j: Int) {
    var temp = nums[i]
    nums[i] = nums[j]
    nums[j] = temp
}

