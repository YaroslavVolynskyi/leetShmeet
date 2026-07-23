package wordledder

import java.util.PriorityQueue

fun main() {
    println(
        ladderLength2(beginWord = "cat", endWord = "sag", wordList = mutableListOf("bat","bag","sag","dag","dot"))
    )
}

fun ladderLength2(beginWord: String, endWord: String, wordList: MutableList<String>): Int {
    if (endWord !in wordList) {
        return 0
    }

    val patternMap = mutableMapOf<String, MutableList<String>>()

    for (word in wordList + beginWord) {
        for (i in word.indices) {
            val pattern = word.substring(0, i) + "*" + word.substring(i + 1, word.length)
            patternMap.getOrPut(pattern) { mutableListOf() }.add(word)
        }
    }
    var level = 1
    val queue = ArrayDeque<String>()
    queue.add(beginWord)
    val visited = hashSetOf<String>()
    while (queue.isNotEmpty()) {
        repeat(queue.size) {
            val currentWord = queue.removeFirst()
            if (currentWord == endWord) {
                return level
            }
            for (i in currentWord.indices) {
                val currentPattern = currentWord.substring(0, i) + "*" + currentWord.substring(i + 1)
                patternMap[currentPattern]?.forEach { neighbor ->
                    if (!visited.contains(neighbor)) {
                        visited.add(neighbor)
                        queue.add(neighbor)
                    }
                }
            }
        }
        level++
    }

    return 0
}

/**
 * https://neetcode.io/problems/word-ladder/question?list=neetcode150
 * "cat" -> "bat" -> "bag" -> "sag"
 */
fun ladderLength(beginWord: String, endWord: String, wordList: MutableList<String>): Int {
    val adjacencyMap = mutableMapOf<String, MutableList<String>>()
    val completeList = wordList + beginWord + endWord

    for (i in 0 until completeList.size) {
        val currentWord = completeList[i]
        completeList.forEach { wordFromList ->
            if (wordFromList == currentWord) {
                return@forEach
            }
            for (currentLetterIndex in 0 .. wordFromList.length - 1) {
                if (wordFromList.substring(0, currentLetterIndex) == currentWord.substring(0, currentLetterIndex)
                    && wordFromList.substring(currentLetterIndex + 1, wordFromList.length) == currentWord.substring(currentLetterIndex + 1, wordFromList.length)) {
                    val list = adjacencyMap.getOrPut(currentWord) { mutableListOf() }
                    if (!list.contains(wordFromList)) {
                        list.add(wordFromList)
                    }
                }
            }
        }
    }
    val queue = ArrayDeque<Pair<Int, String>>() // level to current word
    queue.add(1 to beginWord)
    val visited = hashSetOf<String>()
    while (queue.isNotEmpty()) {
        val (level, word) = queue.removeFirst()
        if (visited.contains(word)) {
            continue
        }
        visited.add(word)
        if (word == endWord) {
            return level
        }
        adjacencyMap[word]?.forEach { neighbor ->
            queue.add(level + 1 to neighbor)
        }
    }

    return 0
}