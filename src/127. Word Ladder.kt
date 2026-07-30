package wordledder

fun main() {
    println(
//        ladderLength(beginWord = "hit", endWord = "cog", wordList = listOf("hot","dot","dog","lot","log","cog"))
        ladderLength(beginWord = "hot", endWord = "dog", wordList = listOf("hot","dog"))
    )
}

/**
 * https://leetcode.com/problems/word-ladder/description/
 */
fun ladderLength(beginWord: String, endWord: String, wordList: List<String>): Int {
    val map = hashMapOf<String, MutableSet<String>>()
    for (word in wordList.toSet() + beginWord + endWord) {
        for (i in word.indices) {
            val pattern = word.substring(0, i) + "*" + word.substring(i + 1)
            map.getOrPut(pattern) { mutableSetOf() }.add(word)
        }
    }
    val queue = ArrayDeque<String>()
    queue.add(beginWord)
    var level = 1
    val visited = hashSetOf<String>()
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val currentWord = queue.removeFirst()
            if (currentWord == endWord) {
                return level
            }
            for (i in currentWord.indices) {
                val pattern = currentWord.substring(0, i) + "*" + currentWord.substring(i + 1)
                map[pattern]?.forEach { neighbor ->
                    if (neighbor !in visited) {
                        visited.add(neighbor)
                        queue.add(neighbor)
                    }
                }
            }
        }
        level++
    }

    return level
}