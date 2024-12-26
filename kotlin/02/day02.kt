// Advent of Code
// Day 02
// https://adventofcode.com/2024/day/2

import java.io.File

fun isSafeReport(report: List<Int>): Boolean {
    var increasing = true
    var isSafe = true

    report.forEachIndexed { index, value ->
        if (index == 0) {
            // do nothing
        } else if (index == 1) {
            val previous = report[index - 1]
            if (value < previous) {
                increasing = false
            }
            if (Math.abs(value - previous) > 3 || (value - previous) == 0) {
                isSafe = false
            }
        } else {
            val previous = report[index - 1]
            if (increasing && value < previous) {
                isSafe = false
            }
            if (!increasing && value > previous) {
                isSafe = false
            }
            if (Math.abs(value - previous) > 3 || (value - previous) == 0) {
                isSafe = false
            }
        }
    }

    return isSafe
}

fun part1(filename: String) {
    var totalSafeCount = 0

    File(filename).forEachLine {
        val numbers = it.split(" ").map { it.toInt() }

        if (isSafeReport(numbers)) {
            totalSafeCount++
        }
    }

    println(totalSafeCount)
}

fun canBeMadeSafe(levels: List<Int>, skipIndex: Int): Boolean {
    val modifiedLevels = levels.toMutableList()
    modifiedLevels.removeAt(skipIndex)

    if (modifiedLevels.size < 2) return true

    val isIncreasing = modifiedLevels[0] < modifiedLevels[1]

    for (i in 1 until modifiedLevels.size - 1) {
        val diff = modifiedLevels[i + 1] - modifiedLevels[i]
        if ((isIncreasing && diff < 1) || (!isIncreasing && diff > -1) || Math.abs(diff) > 3) {
            return false
        }
    }

    return true
}

fun part2(filename: String) {
    var totalSafeCount = 0

    File(filename).forEachLine {
        val numbers = it.split(" ").map { it.toInt() }

        if (isSafeReport(numbers)) {
            totalSafeCount++
        }
    }

    println(totalSafeCount)

}

fun main(args: Array<String>) {
    part1("input")
//    part2("input")
}
