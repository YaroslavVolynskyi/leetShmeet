fun main() {
    var ar = intArrayOf(-1,0,1,2,-1,-4)
    var res = threeSum(ar)
    println(res)
}

fun threeSum(nums: IntArray): List<List<Int>> {
//    var resultList = mutableListOf<List<Int>>()
//    var currentList = mutableListOf<Int>()
//    nums.sort()
    //backtrack3sum(nums, resultList, currentList, 0)

    val resultList = mutableListOf<List<Int>>()
    nums.sort()
    for (i in 0 .. nums.size - 2) {
        if (i > 0 && nums[i] == nums[i - 1]) {
            continue
        }
        val target = -nums[i]
        var left = i + 1
        var right = nums.size - 1
        while (left < right) {
            val sum = nums[left] + nums[right]
            if (sum < target) {
                left++
            } else if (sum > target) {
                right --
            } else if (sum == target) {
                resultList.add(listOf(nums[i], nums[left], nums[right]))
                left++
                right--
            }
        }
    }

    return resultList
}

fun backtrack3sum(nums: IntArray, resultList: MutableList<List<Int>>, currentList: MutableList<Int>, currentEl: Int) {
    if (currentList.size == 3) {
        if (currentList.sum() == 0) {
            resultList.add(currentList.toList())
        }
        return
    }

    for (i in currentEl .. nums.size - 1) {
        if (i > currentEl && nums[i] == nums[i - 1]) {
            continue
        }
        currentList.add(nums[i])
        backtrack3sum(nums, resultList, currentList, i + 1)
        currentList.removeLast()
    }
}