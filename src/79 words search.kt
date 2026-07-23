fun main() {
//    val arr = arrayOf(
//        charArrayOf('A', 'B', 'C', 'E'),
//        charArrayOf('S', 'F', 'C', 'S'),
//        charArrayOf('A', 'K', 'A', 'E')
//    )
    val board = arrayOf(
        charArrayOf('K', 'A', 'E', 'E'),
        charArrayOf('S', 'F', 'S', 'E'),
        charArrayOf('S', 'E', 'A', 'K')
    )
    var exist = exist(board, "SEAK")
    for (i in 0 .. exist.size - 1) {
        val visited = Array(board.size) {
                i -> BooleanArray(board[i].size) { false }
        }
        val coord = exist[i]
        coord.coordinates.forEach {
            visited[it.first][it.second] = true
        }
        printArray(board, -1, -1, visited)
    }
    println(exist)
}

fun exist(board: Array<CharArray>, word: String): List<Coordinates> {
    val coords = mutableListOf<Coordinates>()
    val visited = Array(board.size) {
        i -> BooleanArray(board[i].size) { false }
    }
    for (i in 0 .. board.size - 1) {
        for (j in 0 .. board[i].size - 1) {
            track(board, word, i, j, coords, Coordinates(mutableListOf()), StringBuilder(), visited, 0)
        }
    }

    return coords
}

fun track(board: Array<CharArray>, word: String, currentI: Int, currentJ: Int, coordinates: MutableList<Coordinates>, currentCoord: Coordinates,
          sb: StringBuilder, visited: Array<BooleanArray>, currentIndex: Int) {
    if (currentI < 0 || currentI >= board.size || currentJ < 0 || currentJ >= board[currentI].size) {
        return
    }
    if (visited[currentI][currentJ]) {
        return
    }
    if (board[currentI][currentJ] != word[currentIndex]) {
        return
    }

    visited[currentI][currentJ] = true
    sb.append(board[currentI][currentJ])
    currentCoord.coordinates.add((currentI to currentJ))
    if (sb.toString() == word) {
        coordinates.add(Coordinates(
            coordinates = currentCoord.coordinates.toMutableList()
        ))
    } else {
        track(board, word, currentI, currentJ + 1, coordinates, currentCoord, sb, visited, currentIndex + 1)
        track(board, word, currentI + 1, currentJ, coordinates, currentCoord, sb, visited, currentIndex + 1)
        track(board, word, currentI, currentJ - 1, coordinates, currentCoord, sb, visited, currentIndex + 1)
        track(board, word, currentI - 1, currentJ, coordinates, currentCoord, sb, visited, currentIndex + 1)
    }
    visited[currentI][currentJ] = false
    sb.deleteCharAt(sb.length - 1)
    currentCoord.coordinates.removeLast()
}

data class Coordinates(val coordinates: MutableList<Pair<Int, Int>>)











//fun exist(board: Array<CharArray>, word: String): Boolean {
//    val visited = Array(board.size) { i ->
//        BooleanArray(board[i].size) { false }
//    }
////    for (i in 0 .. board.size - 1) {
////        for (j in 0 .. board[i].size - 1) {
//            if (track(board, word, visited, 1, 2, 0, StringBuilder())) {
//                return true
//            }
////        }
////    }
//    return false
//}
//
//fun track(board: Array<CharArray>, word: String, visited: Array<BooleanArray>,
//          currentI: Int, currentJ: Int, wordIndex: Int, sb: StringBuilder): Boolean {
//    if (currentI < 0 || currentJ < 0 || currentI >= board.size || currentJ >= board[currentI].size) {
//        return false
//    }
//    printArray(board, currentI, currentJ, visited)
//    println(sb.toString())
//    println()
//    if (visited[currentI][currentJ]) {
//        return false
//    }
//    if (board[currentI][currentJ] != word[wordIndex]) {
//        return false
//    }
////    if (wordIndex == word.length - 1) {
////        println(sb.toString())
////        println()
////        return true
////    }
//    visited[currentI][currentJ] = true
//    sb.append(board[currentI][currentJ])
//    if (sb.toString() == word) {
//        printArray(board, currentI, currentJ, visited)
//        println(sb.toString())
//        return true
//    }
//    val isFound = track(board, word, visited, currentI,     currentJ + 1,  wordIndex + 1, sb)
//               || track(board, word, visited, currentI + 1, currentJ,      wordIndex + 1, sb)
//               || track(board, word, visited, currentI,     currentJ - 1,  wordIndex + 1, sb)
//               || track(board, word, visited, currentI - 1, currentJ,      wordIndex + 1, sb)
//    visited[currentI][currentJ] = false
//    sb.deleteAt(sb.length - 1)
//    return isFound
//}


fun printArray(board: Array<CharArray>, currentI: Int, currentJ: Int, visited: Array<BooleanArray>) {
    for (i in 0..board.size - 1) {
        for (j in 0..board[i].size - 1) {
            if (visited[i][j]) {
                print(highlighted(board[i][j], 42))
            } else if (i == currentI && j == currentJ) {
                print(highlighted(board[i][j]))
            } else {
                print(board[i][j])
            }
            print("   ")
        }
        println()
    }
    println()
}

fun highlighted(c: Char, bg: Int = 43): String = "\u001B[${bg}m$c\u001B[0m"

/**
 * https://leetcode.com/problems/word-search/
 * 79. Word Search
 *
 */

