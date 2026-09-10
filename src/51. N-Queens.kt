package l51

/**
 * https://leetcode.com/problems/n-queens/description/
 */

fun main() {
    println(
        solveNQueens(n = 4)
    )
    println()
}

fun solveNQueens(n: Int): List<List<String>> {
    val result = mutableListOf<List<String>>()
    val board = mutableListOf<String>()
    for (i in 0 .. n - 1) {
        val sb = StringBuilder()
        for (j in 0 .. n - 1) {
            sb.append(".")
        }
        board.add(sb.toString())
    }
    solveRow( 0, board, result, hashSetOf(), hashSetOf(), hashSetOf())
    return result
}

fun solveRow(rowNumber: Int, board: MutableList<String>, result: MutableList<List<String>>,
             takenColumns: MutableSet<Int>, diagonal1: MutableSet<Int>, diagonal2: MutableSet<Int>) {
    if (rowNumber == board.size) {
        result.add(board.toList())
        return
    }
    for (c in 0 .. board[rowNumber].length - 1) { // c column
        val diagonal1Value = rowNumber - c
        val diagonal2Value = rowNumber + c
        if (c !in takenColumns && diagonal1Value !in diagonal1 && diagonal2Value !in diagonal2) {
            // place queen
            board[rowNumber] = board[rowNumber].substring(0, c) + "Q" + board[rowNumber].substring(c + 1)
            takenColumns.add(c)
            diagonal1.add(diagonal1Value)
            diagonal2.add(diagonal2Value)

            // solve next row
            solveRow(rowNumber + 1, board, result, takenColumns, diagonal1, diagonal2)

            // remove queen
            board[rowNumber] = board[rowNumber].substring(0, c) + "." + board[rowNumber].substring(c + 1)
            takenColumns.remove(c)
            diagonal1.remove(diagonal1Value)
            diagonal2.remove(diagonal2Value)
        }
    }
}
