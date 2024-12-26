// Advent of Code
// Day XX
// https://adventofcode.com/2024/day/1

import java.io.File


fun part1(filename: String) {
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()
    var totalSum = 0

    File(filename).forEachLine {
        val numbers = it.split("\\s+".toRegex())
        leftList.add(numbers[0].toInt())
        rightList.add(numbers[1].toInt())
    }

    val sortedLeft = leftList.sorted()
    val sortedRight = rightList.sorted()

    for (i in 0 until sortedLeft.size) {
        totalSum += Math.abs(sortedLeft[i] - sortedRight[i])
    }

    println(totalSum)
}

fun part2(filename: String) {
    val leftList = mutableListOf<Int>()
    val rightList = mutableListOf<Int>()
    var similarityScore = 0

    File(filename).forEachLine {
        val numbers = it.split("\\s+".toRegex())
        leftList.add(numbers[0].toInt())
        rightList.add(numbers[1].toInt())
    }

    leftList.forEach { le ->
        similarityScore += (le * rightList.count { it == le })
    }

    println(similarityScore)
}

fun main(args: Array<String>) {
//    part1("input")
    part2("input")
}
