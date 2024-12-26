// Advent of Code
// Day XX
// https://adventofcode.com/2024/day/x

/*
    TEMPLATE
 */

import java.io.File

fun part1(filename: String) {
    var totalSafeCount = 0

    File(filename).forEachLine {
        val numbers = it.split(" ")
        var previous = 0
        var increasing = true
        var isLineSafe = true

        numbers.forEachIndexed { index, value ->
            if (index == 0) {
                previous = value.toInt()
            } else if (index == 1) {
                var current = value.toInt()
                if (current < previous) { // check if increasing
                    increasing = false
                }
                if (Math.abs(current - previous) > 3 || (current - previous) == 0) { // check difference bounds
                    isLineSafe = false
                }
//                println("current: $current, previous: $previous, increasing: $increasing, isLineSafe: $isLineSafe")
                previous = current
            } else {
                val current = value.toInt()
                if (increasing && current < previous) { // check if not still increasing
                    isLineSafe = false
                }
                if (!increasing && current > previous) { // check if not still decreasing
                    isLineSafe = false
                }
                if (Math.abs(current - previous) > 3 || (current - previous) == 0) { // check difference bounds
                    isLineSafe = false
                }
//                println("current: $current, previous: $previous, increasing: $increasing, isLineSafe: $isLineSafe")
                previous = current
            }

            // if we made it to the end of the list, increment the totalSafeCount
            if (index == numbers.size - 1 && isLineSafe) {
                totalSafeCount++
//                println("!!! incrementing totalSafeCount: $totalSafeCount")
            }
        }
    }

    println(totalSafeCount)
}

fun part2(filename: String) {

    File(filename).forEachLine {
        // access each line with `it` (type String)
    }

}

fun main(args: Array<String>) {
    part1("input")
//    part2("input")
}
