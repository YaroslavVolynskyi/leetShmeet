fun main() {

//    val tree = PrefixTree()
//    tree.insert("apple")
//    tree.insert("appricot")
//    tree.insert("base")
//    tree.insert("bag")
//
//    println("search " + tree.search("ba"))
//    println("startsWith " + tree.startsWith("ba"))
//    val wordDictionary = WordDictionary()
//    wordDictionary.addWord("d1p")
//    wordDictionary.addWord("d2l")
//    wordDictionary.addWord("d3y")
//
//    println(wordDictionary.search("d.l"))

//    wordDictionary.addWord("day")
//    wordDictionary.addWord("bay")
//    wordDictionary.addWord("may")
//    wordDictionary.addWord("ma")

//    println(wordDictionary.find("may", wordDictionary, 0))
//    wordDictionary.search("say") // return false
//    wordDictionary.search("day") // return true
//    wordDictionary.search(".ay") // return true
//    wordDictionary.search("b..") // return true

    val board = arrayOf(
        charArrayOf('a','b','c','d'),
        charArrayOf('s','a','a','t'),
        charArrayOf('a','c','k','e'),
        charArrayOf('a','c','d','n')
    )
    val words = arrayOf("bat","cat","back","backend","stack")
    findWords(board, words).forEach { println(it) }
}

/**
 * https://neetcode.io/problems/search-for-word-ii/question?list=neetcode150
 */
fun findWords(board: Array<CharArray>, words: Array<String>): List<String> {
    var result = arrayListOf<String>()
    val tree = PrefixTree()
    words.forEach { tree.insert(it) }
    val visited = Array(board.size) { i -> BooleanArray(board[i].size) }
    for (i in 0 .. board.size - 1) {
        for (j in 0..board[i].size - 1) {
            backtrack3(board, visited, tree, result, i, j)
        }
    }
    return result
}

fun backtrack3(board: Array<CharArray>, visited: Array<BooleanArray>, tree: PrefixTree,
               result: MutableList<String>, currentI: Int, currentJ: Int) {
    if (currentI < 0 || currentJ < 0 || currentI >= board.size || currentJ >= board[currentI].size) {
        return
    }
    val c = board[currentI][currentJ]
    val child = tree.map[c]
    if (visited[currentI][currentJ] || child == null) {
        return
    }
    if (child?.isLast == true ) {
        child.word?.let {
            result.add(it)
        }
        child.word = null
    }
    visited[currentI][currentJ] = true
    backtrack3(board, visited, child, result, currentI, currentJ + 1)
    backtrack3(board, visited, child, result, currentI + 1, currentJ)
    backtrack3(board, visited, child, result, currentI, currentJ - 1)
    backtrack3(board, visited, child, result, currentI - 1, currentJ)
    visited[currentI][currentJ] = false

}

/**
 * https://neetcode.io/problems/design-word-search-data-structure/question?list=neetcode150
 */
class WordDictionary {

    val map = mutableMapOf<Char, WordDictionary>()
    var isLast = false

    fun addWord(word: String) {
        var currentNode = this
        for (c in word) {
            if (!currentNode.map.contains(c)) {
                currentNode.map[c] = WordDictionary()
            }
            currentNode = currentNode.map[c]!!
        }
        currentNode.isLast = true
    }

    fun find(word: String, node: WordDictionary, i: Int): Boolean {
        if (i == word.length) {
            return node.isLast
        } else if (word[i] == '.') {
            var isFound = false
            node.map.keys.forEach { key ->
                isFound = isFound || find(word, node.map[key]!!, i + 1)
            }
            return isFound
        } else if (!node.map.contains(word[i])) {
            return false
        } else {
            return find(word, node.map[word[i]]!!, i + 1)
        }
    }

    fun search(word: String): Boolean {
        return find(word, this, 0)
    }
}


/**
 * https://neetcode.io/problems/implement-prefix-tree/question?list=neetcode150
 */
class PrefixTree {

    val map = mutableMapOf<Char, PrefixTree>()
    var isLast = false
    var word: String? = null

    fun insert(word: String) {
        var currentNode = this
        for (i in 0 .. word.length - 1) {
            if (!currentNode.map.contains(word[i])) {
                currentNode.map[word[i]] = PrefixTree()
            }
            currentNode = currentNode.map[word[i]]!!
        }
        currentNode.isLast = true
        currentNode.word = word
    }

    fun search(word: String): Boolean {
        return find(word)?.isLast ?: false
    }

    fun startsWith(prefix: String): Boolean {
        return find(prefix) != null
    }

    fun find(word: String): PrefixTree? {
        var currentNode = this
        for (c in word) {
            var containsCurrentChar = currentNode.map.contains(c)
            if (containsCurrentChar) {
                currentNode = currentNode.map[c]!!
            } else {
                return null
            }
        }
        return currentNode
    }
}
