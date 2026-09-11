package l93

/**
 * https://leetcode.com/problems/restore-ip-addresses/description/
 *
 * Input: s = "25525511135"
 * Output: ["255.255.11.135","255.255.111.35"]
 */

fun main() {
    println(
        restoreIpAddresses(s = "25525511135")
    )
    println()
}

fun restoreIpAddresses(s: String): List<String> {
    val result = mutableListOf<String>()
    backtrack(s, 0, 3, StringBuilder(), result)
    return result
}

fun backtrack(s: String, startIndex: Int, dotsLeft: Int, sb: StringBuilder, result: MutableList<String>) {
    if (dotsLeft == 0) {
        val remaining = s.substring(startIndex)
        if (isValid(remaining)) {
            result.add(sb.toString() + remaining)
        }
        return
    }

    for (length in 1..3) {
        if (startIndex + length > s.length) {
            break
        }
        val segment = s.substring(startIndex, startIndex + length)
        if (isValid(segment)) {
            sb.append(segment).append(".")
            backtrack(s, startIndex + length, dotsLeft - 1, sb, result)
            sb.delete(sb.length - segment.length - 1, sb.length)
        }
    }
}

fun isValid(segment: String): Boolean {
    return !(segment.isEmpty()
            || (segment.length > 1 && segment[0] == '0')
            || segment.toLong() > 255)
}
