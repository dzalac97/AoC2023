package hr.dzalac.aoc23.day03

import hr.dzalac.aoc23.FileReader

private val threeLines: MutableList<String?> = mutableListOf(null, null, null)
private const val PREV = 0
private const val CURR = 1
private const val NEXT = 2

fun main() {
    var result1 = 0
    var result2 = 0
    val input = FileReader(3)
    val directions = listOf(-1, 0, 1)
    threeLines[CURR] = input.nextLine()
    threeLines[NEXT] = input.nextLine()
    val maxIdx = threeLines[CURR]?.indices?.last!!

    while (threeLines[CURR] != null) {
        threeLines[CURR]?.forEachIndexed { idx, c ->
            if (c.isSymbol()) {
                var tmpCounter = 0
                var tmpResult = 1
                directions.forEach { dir ->
                    val tIdx = idx + dir
                    if (tIdx in 0..maxIdx) {
                        threeLines.forEach { line ->
                            if (line != null) {
                                val skip = calculateSkip(dir, line, idx)
                                if (!skip && line[tIdx].isDigit()) {
                                    val number = getNumber(line, tIdx)
                                    tmpResult *= number
                                    tmpCounter += 1
                                    result1 += number
                                }
                            }
                        }
                    }
                }
                if (tmpCounter == 2) {
                    result2 += tmpResult
                }
            }
        }
        nextBatch(input)
    }
    println(result1)
    println(result2)
}

private fun calculateSkip(
    direction: Int,
    line: String,
    index: Int,
): Boolean {
    val left = index - 1
    val right = index + 1
    val maxIndex = line.length - 1
    if (line[index].isDigit() && direction >= 0) {
        if (left >= 0 && line[left].isDigit()) return true
        if (direction == 0 && right <= maxIndex && line[right].isDigit()) return true
    }
    return false
}

private fun nextBatch(input: FileReader) {
    threeLines[PREV] = threeLines[CURR]
    threeLines[CURR] = threeLines[NEXT]
    threeLines[NEXT] = input.nextLine()
}

private fun getNumber(
    line: String,
    index: Int,
): Int {
    var number = parseNumber(line, index - 1 downTo 0)
    number = number.reversed()
    number += parseNumber(line, index..<line.length)
    return number.toInt()
}

private fun parseNumber(
    line: String,
    range: IntProgression,
): String {
    var number = ""
    for (i in range) {
        val char = line[i]
        if (!char.isDigit()) break
        number += line[i]
    }
    return number
}

private fun Char.isSymbol(): Boolean = !this.isDigit() && this != '.'
