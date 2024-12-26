// Advent of Code
// Day 02
// https://adventofcode.com/2024/day/2

import java.io.File

fun isSafeReportMine(report: List<Int>): Boolean {
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
                isSafe = canBeMadeSafe(report, index)
            }
        } else {
            val previous = report[index - 1]
            if (increasing && value < previous) {
                isSafe = canBeMadeSafe(report, index)
            }
            if (!increasing && value > previous) {
                isSafe = canBeMadeSafe(report, index)
            }
            if (Math.abs(value - previous) > 3 || (value - previous) == 0) {
                isSafe = canBeMadeSafe(report, index)
            }
        }
    }

    return isSafe
}

fun isSafeReportGemini(report: String): Boolean {
    val levels = report.split(" ").map { it.toInt() }
    if (levels.size < 2) return true

    val isIncreasing = levels[0] < levels[1]

    for (i in 1 until levels.size - 1) {
        val diff = levels[i + 1] - levels[i]
        if ((isIncreasing && diff < 1) || (!isIncreasing && diff > -1) || Math.abs(diff) > 3) {
            // If the report is unsafe, check if removing a level makes it safe
            return canBeMadeSafe(levels, i)
        }
    }

    return true
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

fun isSafeReportChatGPT(report: String): Boolean {
    // Convert the report to a list of integers
    val levels = report.split(" ").map { it.toInt() }

    return isSafeReport(levels)
}

fun isSafeReport(levels: List<Int>): Boolean {
    // Check if the levels are either all increasing or all decreasing
    val isIncreasing = levels.zipWithNext().all { (a, b) -> b >= a }
    val isDecreasing = levels.zipWithNext().all { (a, b) -> b <= a }

    // Check if the adjacent differences are between 1 and 3 (inclusive)
    val hasValidDifferences = levels.zipWithNext().all { (a, b) ->
        val diff = kotlin.math.abs(b - a)
        diff in 1..3
    }

    // The report is safe if it's either all increasing or all decreasing, and has valid differences
    return (isIncreasing || isDecreasing) && hasValidDifferences
}

fun isSafeReportWithDampener(report: String): Boolean {
    // Convert the report to a list of integers
    val levels = report.split(" ").map { it.toInt() }

    // Check if the levels are already safe
    if (isSafeReport(levels)) {
        return true
    }

    // Try removing each level once and check if it becomes safe
    for (i in levels.indices) {
        val modifiedLevels = levels.toMutableList().apply { removeAt(i) }
        if (isSafeReport(modifiedLevels)) {
            return true
        }
    }

    // If no valid safe report is found, return false
    return false
}

fun part1(filename: String) {
    var totalSafeCount = 0

    File(filename).forEachLine {
        val numbers = it.split(" ").map { it.toInt() }

        if (isSafeReportMine(numbers)) {
            totalSafeCount++
        }
    }

    println(totalSafeCount)
}

fun part1ChatGPT(filename: String) {
    var totalSafeCount = 0

    File(filename).forEachLine {
        if (isSafeReportChatGPT(it)) {
            totalSafeCount++
        }
    }

    println(totalSafeCount)
}

fun part2(filename: String) {
    var totalSafeCount = 0

    File(filename).forEachLine {
        if (isSafeReportWithDampener(it)) {
            totalSafeCount++
        }
    }

    println(totalSafeCount)

}

fun main(args: Array<String>) {
//    part1("input")
//    part1ChatGPT("input")
    part2("input")
}
