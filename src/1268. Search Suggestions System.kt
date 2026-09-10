package l1268

/**
 * https://leetcode.com/problems/search-suggestions-system/
 */

fun main() {
    println(
        suggestedProducts(
            products = arrayOf("mobile", "mouse", "moneypot", "monitor", "mousepad"),
            searchWord = "mouse"
        )
    )
    println()
}

fun suggestedProducts(products: Array<String>, searchWord: String): List<List<String>> {
    val trie = Trie()
    products.forEach {
        trie.add(it)
    }
    trie.search("mon")
    val list = trie.searchByLetters("moni")

    return list
}

class Trie {
    val children = mutableMapOf<Char, Trie>()
    var word: String? = null

    fun add(newWord: String) {
        var node = this
        newWord.forEach { c ->
            if (node.children[c] == null) {
                node.children[c] = Trie()
            }
            node = node.children[c]!!
        }
        node.word = newWord
    }

    fun searchByLetters(searchWord: String): List<List<String>> {
        val resultList = mutableListOf<List<String>>()
        for (i in 1 .. searchWord.length) {
            val list = search(searchWord.substring(0, i))
            resultList.add(list)
        }
        return resultList
    }

    fun search(searchWord: String): List<String> {
        var node = this
        searchWord.forEach { c ->
            if (node.children.contains(c)) {
                node = node.children[c]!!
            } else {
                return emptyList()
            }
        }

        val list = mutableListOf<String>()
        findWord(node, list)

        return list.sorted().take(3)
    }

    fun findWord(node: Trie, list: MutableList<String>) {
        if (node.word != null) {
            list.add(node.word!!)
        }
        node.children.forEach { (c, trie) ->
            findWord(node.children[c]!!, list)
        }
    }
}
