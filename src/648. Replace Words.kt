package l648

/**
 * https://leetcode.com/problems/replace-words/description/
 */

fun main() {
    println(
        replaceWords(
            dictionary = listOf("cat", "bat", "rat"),
            sentence = "the cattle was rattled by the battery"
        )
    )
    println()
}

fun replaceWords(dictionary: List<String>, sentence: String): String {
    val root = Trie()
    dictionary.forEach { word ->
        root.add(word)
    }

    return sentence.split(" ").map { word ->
        var node: Trie? = root
        var replacement: String? = null
        for (char in word) {
            node = node?.children[char]
            if (node?.word != null) {
                replacement = node.word
                break
            }
        }
        replacement ?: word
    }.joinToString(" ")
}

class Trie {
    val children = mutableMapOf<Char, Trie>()
    var word: String? = null

    fun add(newWord: String) {
        var root = this
        for (c in newWord) {
            root = root.children.getOrPut(c) { Trie() }
        }
        root.word = newWord
    }
}