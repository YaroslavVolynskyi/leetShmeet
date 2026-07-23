import kotlin.math.max

fun main() {
    //println(isAnagram("racecar","carrace"))
//    println(twoSum(intArrayOf(3, 4,5,6), target = 7).contentToString())
//    println(twoSum(intArrayOf(3, 2, 3), target = 6).contentToString())
//    var s = "tea"
//    val s2 = s.toSortedSet().toString()
//    println(s2)
//    var input = arrayOf("act","pots","tops","cat","stop","hat")
//    val input2 = arrayOf("ddddddddddg", "dgggggggggg")
//    groupAnagrams(input2).forEach { println(it) }
//    println(topKFrequent(intArrayOf(3,3,3, 3, 2, 2, 1), k = 2).contentToString())
//    println(topKFrequent(intArrayOf(1, 2), k = 2).contentToString())

//    var res = decode(encode(listOf("first", "second", "third")))
//    var res = decode(encode(listOf("")))
//    println(res)

//    println(longestConsecutive(intArrayOf(2, 3, 4, 6, 9, 11, 12, 13, 14, 15)))
//    println(longestConsecutive(intArrayOf(2,20,4,7,6,10,3,4,5)))

//    val board = arrayOf(
//        charArrayOf('1','2','.','.','3','.','.','.','.'),
//        charArrayOf('4','.','.','5','.','.','.','.','.'),
//        charArrayOf('.','9','8','.','.','.','.','.','3'),
//        charArrayOf('5','.','.','.','6','.','.','.','4'),
//        charArrayOf('.','.','.','8','.','3','.','.','5'),
//        charArrayOf('7','.','.','.','2','.','.','.','6'),
//        charArrayOf('.','.','.','.','.','.','2','.','.'),
//        charArrayOf('.','.','.','4','1','9','.','.','8'),
//        charArrayOf('.','.','.','.','8','.','.','7','9')
//    )
//    println(isValidSudoku(board))
}

/**
 * https://neetcode.io/problems/valid-sudoku/question?list=neetcode150
 */
fun isValidSudoku(board: Array<CharArray>): Boolean {
    // check rows
    for (i in 0 .. board[0].size - 1) {
        val rowList = mutableListOf<Int>()
        board[i].indices.forEach { j ->
            if (board[i][j].isDigit()) {
                val current = board[i][j].digitToInt()
                if (current < 1 && current > 9) {
                    return false
                }
                rowList.add(current)
            }
        }
        val rowSet = rowList.toSet()
        if (rowSet.size != rowList.size) {
            return false
        }
    }
    // check cols
    for (i in 0 .. board.size - 1) {
        val colList = mutableListOf<Int>()
        board.indices.forEach { j ->
            if (board[j][i].isDigit()) {
                val current = board[j][i].digitToInt()
                if (current < 1 && current > 9) {
                    return false
                }
                colList.add(current)
            }
        }
        val colSet = colList.toSet()
        if (colSet.size != colList.size) {
            return false
        }
    }
    // check 3*3 blocks
    var startI = 0
    var startJ = 0
    while (startI <= board.size - 3) {
        while (startJ <= board[startI].size - 3) {
            val blockList = mutableListOf<Int>()
            for (i in startI .. startI + 2) {
                for (j in startJ .. startJ + 2) {
                    if (board[i][j].isDigit()) {
                        val current = board[i][j].digitToInt()
                        if (current < 1 && current > 9) {
                            return false
                        }
                        blockList.add(current)
                    }
                }
            }
            val blockSet = blockList.toSet()
            if (blockSet.size != blockList.size) {
                return false
            }
            startJ += 3
        }
        startJ = 0
        startI += 3
    }
    return true
}

/**
 * https://neetcode.io/problems/longest-consecutive-sequence/question?list=neetcode150
 */
// 2 3 4 6 9 11 12 13 14
fun longestConsecutive(nums: IntArray): Int {
    if (nums.isEmpty()) {
        return 0
    }
    val set = nums.toSet()
    var maxStreak = 0
    nums.forEach { num ->
        if (set.contains(num)) {
            var length = 1
            while (set.contains(num + length)) {
                length++
            }
            maxStreak = max(length, maxStreak)
        }
    }
    return maxStreak
}

fun longestConsecutive0(nums: IntArray): Int {
    if (nums.size == 0) {
        return 0
    }
    nums.sort()
    var maxStreak = 0
    var currentStreak = 0
    for (i in 0 .. nums.size - 2) {
        if (nums[i + 1] - nums[i] == 1) {
            currentStreak++
            if (currentStreak > maxStreak) {
                maxStreak = currentStreak
            }
        } else if (nums[i + 1] == nums[i]) {
            continue
        } else {
            currentStreak = 0
        }
    }
    return maxStreak + 1
}

/**
 * https://neetcode.io/problems/products-of-array-discluding-self/question?list=neetcode150
 *
 */
fun productExceptSelf(nums: IntArray): IntArray {
    val pref = IntArray(nums.size)
    val suf = IntArray(nums.size)
    val res = IntArray(nums.size)

    pref[0] = 1
    for (i in 1 .. nums.size - 1) {
        pref[i] = pref[i - 1] * nums[i - 1]
    }

    suf[nums.size - 1] = 1
    for (i in nums.size - 2 downTo 0) {
        suf[i] = suf[i + 1] * nums[i + 1]
    }
    for (i in res.indices) {
        res[i] = pref[i] * suf[i]
    }
    return res
}

/**
 * https://neetcode.io/problems/string-encode-and-decode/history?submissionIndex=11
 */
fun encode(strs: List<String>): String {
    val sb = StringBuilder()
    strs.forEach { s ->
        sb.append(s.length)
        sb.append(",")
    }
    sb.append("#")
    strs.forEach { s ->
        sb.append(s)
    }
    return sb.toString()
}

fun decode(str: String): List<String> {
    val list = mutableListOf<String>()
    val sizesList = mutableListOf<Int>()
    var i = 0
    var currentSize = 0
    while (str[i] != '#') {
        if (str[i] != ',') {
            currentSize = currentSize * 10 + str[i].digitToInt()
        } else {
            sizesList.add(currentSize)
            currentSize = 0
        }
        i++
    }
    i++
    for (size in sizesList) {
        list.add(str.substring(i, i + size))
        i += size
    }

    return list
}

fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    nums.forEach {
        if (map.contains(it)) {
            map[it] = map[it]!! + 1
        } else {
            map[it] = 1
        }
    }

    return map.keys.sortedByDescending { map[it] }.take(k).toIntArray()
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val resultList = mutableListOf<List<String>>()
    val map = mutableMapOf<String, List<String>>()
    strs.forEach { s ->
        val sortedString = s.toCharArray().sorted().joinToString("")
        if (!map.contains(sortedString)) {
            map[sortedString] = mutableListOf(s)
        } else {
            (map[sortedString] as MutableList<String>).add(s)
        }
    }
    for ((key, list) in map) {
        resultList.add(list)
    }
    return resultList
}

fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) {
        return false
    }
    var schars = s.toCharArray().apply { sort() }
    var tchars = t.toCharArray().apply { sort() }
    for (i in schars.indices) {
        if (schars[i] != tchars[i]) {
            return false
        }
    }
    return true
}

fun twoSum(nums: IntArray, target: Int): IntArray {
//    for (i in nums.indices) {
//        val first = nums[i]
//        for (j in i + 1 .. nums.size - 1) {
//            if (first + nums[j] == target) {
//                return intArrayOf(i, j)
//            }
//        }
//    }
//    return IntArray(2)

    val map = mutableMapOf<Int, Int>()
    nums.forEachIndexed { index, num ->
        map[num] = index
    }
    nums.forEachIndexed { index, num ->
        if (map.contains(target - num) && index != map[target - num]) {
            return intArrayOf(index, map[target - num]!!)
        }
    }
    return IntArray(2)
}