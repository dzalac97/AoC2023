package hr.dzalac.aoc23.day02

import hr.dzalac.aoc23.FileReader
import hr.dzalac.aoc23.day02.CubeGame.Companion.COLOR.BLUE
import hr.dzalac.aoc23.day02.CubeGame.Companion.COLOR.GREEN
import hr.dzalac.aoc23.day02.CubeGame.Companion.COLOR.RED

fun main() {
    val input = FileReader(2)

    val limit =
        mapOf(
            RED to 12,
            GREEN to 13,
            BLUE to 14,
        )

    var result1 = 0
    var result2 = 0
    input.forEachLine { line ->
        val cubeGame = CubeGame(line)
        if (cubeGame.isPossible(limit)) result1 += cubeGame.id
        result2 += cubeGame.getMinimumSetPower()
    }

    println(result1)
    println(result2)
}
