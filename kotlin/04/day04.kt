// Advent of Code
// Day 04
// https://adventofcode.com/2024/day/4


import java.io.File

fun part1(filename: String) {
    var grid: ArrayList<String> = ArrayList()
    // build the grid
    File(filename).forEachLine {
        grid.add(it)
    }

    // count XMAS
    val word = "XMAS"
    val wordLength = word.length
    val rows = grid.size
    val cols = grid[0].length
    var count = 0

    // Directions: (deltaRow, deltaCol) for 8 directions
    val directions = arrayOf(
        Pair(0, 1),  // Right
        Pair(0, -1), // Left
        Pair(1, 0),  // Down
        Pair(-1, 0), // Up
        Pair(1, 1),  // Down-right (Diagonal)
        Pair(-1, -1),// Up-left (Diagonal)
        Pair(1, -1), // Down-left (Diagonal)
        Pair(-1, 1)  // Up-right (Diagonal)
    )

    // Helper function to check if "XMAS" can be found from (row, col) in a specific direction
    fun canFindWord(row: Int, col: Int, dRow: Int, dCol: Int): Boolean {
        for (i in 0 until wordLength) {
            val newRow = row + i * dRow
            val newCol = col + i * dCol
            if (newRow !in 0 until rows || newCol !in 0 until cols || grid[newRow][newCol] != word[i]) {
                return false
            }
        }
        return true
    }

    // Iterate through each cell of the grid
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            // For each direction, check if "XMAS" is present starting from (row, col)
            for (direction in directions) {
                val (dRow, dCol) = direction
                if (canFindWord(row, col, dRow, dCol)) {
                    count++
                }
            }
        }
    }

    println(count)
}

fun part2(filename: String) {
    var grid: ArrayList<String> = ArrayList()
    // build the grid
    File(filename).forEachLine {
        grid.add(it)
    }
    val rows = grid.size
    val cols = grid[0].length
    var count = 0

    fun isMas(a: Char, b: Char): Boolean {
        return (a == 'M' && b == 'S') || (a == 'S' && b == 'M')
    }

    // Helper function to check if the 'A' in "MAS" can be found from (row, col) in any direction
    fun canFindX(row: Int, col: Int): Boolean {
        var masCount = 0
        if (grid[row][col] != 'A') {
            return false
        }

        // check upper left and lower right diagonal
        val ulrow = row - 1
        val ulcol = col - 1
        val lrrow = row + 1
        val lrcol = col + 1
        if (ulrow >= 0 && ulcol >= 0 && lrrow < rows && lrcol < cols) {
            if (isMas(grid[ulrow][ulcol], grid[lrrow][lrcol])) {
                masCount++
            }
        }

        // check upper right and lower left diagonal
        val urrow = row - 1
        val urcol = col + 1
        val llrow = row + 1
        val llcol = col - 1
        if (urrow >= 0 && urcol < cols && llrow < rows && llcol >= 0) {
            if (isMas(grid[urrow][urcol], grid[llrow][llcol])) {
                masCount++
            }
        }

        return masCount == 2
    }

    // Iterate through each cell of the grid
    for (row in 0 until rows) {
        for (col in 0 until cols) {
            // Check if we can find an X-"MAS" starting from 'A' as (row, col)
            if (canFindX(row, col)) {
                count++
            }
        }
    }

    println(count)
}

fun main(args: Array<String>) {
//    part1("input")
    part2("input")
}
