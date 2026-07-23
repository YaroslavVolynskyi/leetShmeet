
fun main() {
//    println(strStr("12345sad5555", "sad"))
    println(strStr("mississippi", "issipi"))
}

fun strStr(haystack: String, needle: String): Int {
    if (needle.length > haystack.length) {
        return -1
    }
    var i = 0
    while (i < haystack.length) {
        if (haystack[i] == needle[0]) {
            var found = true
            for (j in i + 1 .. i + needle.length - 1) {
                if (j >= haystack.length || j - i >= needle.length) {
                    found = false
                    continue
                }
                if (haystack[j] != needle[j - i]) {
                    found = false
                    continue
                }
            }
            if (found) {
                return i
            }
        }
        i++
    }

    return -1
}

