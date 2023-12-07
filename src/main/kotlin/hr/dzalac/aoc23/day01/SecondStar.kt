package hr.dzalac.aoc23.day01

import hr.dzalac.aoc23.FileReader

fun main() {
    val input = FileReader(1)

    var result = 0
    input.forEachLine { line ->
        val first = getFirstNumeric(line)
        val last = getFirstNumeric(line, true)
        result += "$first$last".toInt()
    }
    print(result)
}

private val numbers =
    mapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9',
    )

private fun getFirstNumeric(
    line: String,
    reversed: Boolean = false,
): Char {
    var buffer = ""
    val range = if (reversed) line.indices.reversed() else line.indices
    for (i in range) {
        val char = line[i]
        if (line[i].isDigit()) return line[i]
        buffer = if (reversed) char + buffer else buffer + char
        numbers.forEach { if (buffer.contains(it.key)) return it.value }
    }
    return '0'
}
