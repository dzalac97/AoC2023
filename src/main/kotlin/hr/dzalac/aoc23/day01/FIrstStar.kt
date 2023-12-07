package hr.dzalac.aoc23.day01

import hr.dzalac.aoc23.FileReader

fun main() {
    val input = FileReader(1)

    var result = 0
    input.forEachLine { line ->
        val digits = line.filter(Char::isDigit)
        val first = digits.first()
        val last = digits.last()
        result += "$first$last".toInt()
    }

    print(result)
}
