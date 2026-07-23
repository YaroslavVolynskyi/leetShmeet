package alien_dictionary

fun main() {
    println(
        foreignDictionary(arrayOf("hrn","hrf","er","enn","rfnn"))
    )
}

fun foreignDictionary(words: Array<String>): String {
    val adjacencyMap = mutableMapOf<Char, MutableSet<Char>>()
    words.forEach { word ->
        word.forEach { letter ->
            adjacencyMap[letter] = mutableSetOf()
        }
    }
    for (i in 0 .. words.size - 2) {
        val word1 = words[i]
        val word2 = words[i + 1]
        var counter = 0
        while (counter < word1.length
            && counter < word2.length
            && word1[counter] == word2[counter]
        ) {
            counter++
        }
        if (counter < word1.length && counter < word2.length) {
            adjacencyMap.getOrPut(word1[counter]) { mutableSetOf() }.add(word2[counter])
        } else if (word1.length > word2.length) {
            return "" // immediately return "" because that means that word2 is prefix of word1, eg w1="abc", w2="ab", and that means that input dictionary was incorrectly sorted
        }
    }

    val visited = mutableSetOf<Char>()
    val order = mutableListOf<Char>()
    fun dfs(current: Char, visited: MutableSet<Char>, order: MutableList<Char>, path: MutableSet<Char>): Boolean {
        if (visited.contains(current)) {
            return true
        }
        if (path.contains(current)) {
            return false
        }
        path.add(current)
        adjacencyMap[current]?.forEach { neighbor ->
            if (!dfs(neighbor, visited, order, path)) {
                return false
            }
        }
        path.remove(current)
        visited.add(current)
        order.add(current)
        return true
    }
    adjacencyMap.keys.forEach {
        if (!dfs(it, visited, order, mutableSetOf())) {
            return ""
        }
    }

    return order.reversed().joinToString("")

//    val indegreesMap = mutableMapOf<Char, Int>()
//    adjacencyMap.forEach { (letter, neighbors) ->
//        indegreesMap[letter] = 0
//    }
//    adjacencyMap.forEach { (letter, neighbors) ->
//        neighbors.forEach { neighbor ->
//            indegreesMap[neighbor] = indegreesMap[neighbor]!! + 1
//        }
//    }
//    val queue = ArrayDeque<Char>()
//    indegreesMap.filter { it.value == 0 }.forEach { (letter, indegree) ->
//        queue.add(letter)
//    }
//    val order = StringBuilder()
//    while(queue.isNotEmpty()) {
//        val letter = queue.removeFirst()
//        order.append(letter)
//        adjacencyMap[letter]?.forEach { neighbor ->
//            indegreesMap[neighbor] = indegreesMap[neighbor]!! - 1
//            if (indegreesMap[neighbor] == 0) {
//                queue.add(neighbor)
//            }
//        }
//    }
//
//
//    return if (order.length == adjacencyMap.size) {
//        order.toString()
//    } else {
//        ""
//    }
}

//fun foreignDictionary(words: Array<String>): String {
//    val adjacencyMap = mutableMapOf<Char, MutableSet<Char>>()
//    words.forEach { word ->
//        word.forEach { letter ->
//            adjacencyMap[letter] = mutableSetOf()
//        }
//    }
//    for (i in 0 .. words.size - 2) {
//        val word1 = words[i]
//        val word2 = words[i + 1]
//        var wordCounter = 0
//
//        while (wordCounter < word1.length
//            && wordCounter < word2.length
//            && word1[wordCounter] == word2[wordCounter]
//        ) {
//            wordCounter++
//        }
//        if (wordCounter < word1.length && wordCounter < word2.length) {
//            adjacencyMap[word1[wordCounter]]?.add(word2[wordCounter])
//        } else if (word1.length > word2.length) {
//            return ""
//        }
//    }
//
//    val indegreeMap = mutableMapOf<Char, Int>()
//    adjacencyMap.keys.forEach { indegreeMap[it] = 0 }
//    adjacencyMap.forEach { letter, neigbors ->
//        neigbors.forEach { neighbor ->
//            indegreeMap[neighbor] = indegreeMap[neighbor]!! + 1
//        }
//    }
//    val queue = ArrayDeque<Char>()
//    val order = StringBuilder()
//    indegreeMap.forEach { letter, indegree ->
//        if (indegree == 0) {
//            queue.add(letter)
//        }
//    }
//    while (queue.isNotEmpty()) {
//        val letter = queue.removeFirst()
//        order.append(letter)
//        adjacencyMap[letter]?.forEach { neighbor ->
//            indegreeMap[neighbor] = indegreeMap[neighbor]!! - 1
//            if (indegreeMap[neighbor] == 0) {
//                queue.addLast(neighbor)
//            }
//        }
//    }
//
//    return if (order.length == adjacencyMap.size) {
//        order.toString()
//    } else {
//        ""
//    }
//}

// h -> e -> -> r -> n -> f
