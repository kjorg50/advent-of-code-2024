// Advent of Code
// Day 03
// https://adventofcode.com/2024/day/3

import java.io.File

fun part1(filename: String) {
    val mulRegexPattern = "mul\\(\\d{1,3},\\d{1,3}\\)".toRegex()
    var sum = 0

    File(filename).forEachLine {
        val matches = mulRegexPattern.findAll(it)
        matches.forEach {
            val values = it.value.split("(")[1].split(")")[0].split(",")
            val a = values[0].toInt()
            val b = values[1].toInt()
            sum += a * b
        }
    }
    println(sum)
}

fun part2(filename: String) {
    val mulRegexPattern = "mul\\(\\d{1,3},\\d{1,3}\\)|do\\(\\)|don't\\(\\)".toRegex()
    var sum = 0
    var mulEnabled = true

    File(filename).forEachLine {
        val matches = mulRegexPattern.findAll(it)
        for (match in matches) {
            val value = match.value
            if (value == "do()") {
                mulEnabled = true
            } else if (value == "don't()") {
                mulEnabled = false
            } else {
                val values = value.split("(")[1].split(")")[0].split(",")
                val a = values[0].toInt()
                val b = values[1].toInt()
                if (mulEnabled) {
                    sum += a * b
                }
            }
        }
    }
    println(sum)
}

fun main(args: Array<String>) {
//    part1("input")
    part2("input")
}
