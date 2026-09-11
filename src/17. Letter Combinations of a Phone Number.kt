package l17

/**
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 */

fun main() {
    println(
        letterCombinations(digits = "23")
    )
    println()
}

fun letterCombinations(digits: String): List<String> {
    val map = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )

    val list = mutableListOf<String>()
    backtrack(digits, 0, list, StringBuilder(), map)
    return list
}

fun backtrack(digits: String, index: Int, list: MutableList<String>, currentSB: StringBuilder, map: Map<Char, String>) {
    if (currentSB.length == digits.length) {
        list.add(currentSB.toString())
        return
    }
    val currentNumber = digits[index]
    map[currentNumber]?.forEach { char ->
        currentSB.append(char)
        backtrack(digits, index + 1, list, currentSB, map)
        currentSB.deleteAt(currentSB.lastIndex)
    }
}
