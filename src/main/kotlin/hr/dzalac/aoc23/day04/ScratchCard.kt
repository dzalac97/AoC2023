package hr.dzalac.aoc23.day04

import kotlin.math.log
import kotlin.math.pow

data class CardId(private val id: Int) {
    fun next(value: Int = 1) = CardId(id + value)
}

class ScratchCard(line: String) {
    val id: CardId
    private val winningNumbers: List<Int>
    private val myNumbers: List<Int>
    val score: Int
    val hits: Int

    init {
        val whitespace = Regex(" +")
        val tmp = line.split(": ")
        id = CardId(tmp[0].split(whitespace)[1].toInt())
        val numbers = tmp[1].split("| ").map { nums -> nums.trim().split(whitespace).map { it.toInt() } }
        winningNumbers = numbers[0]
        myNumbers = numbers[1]

        score = calculateScore()
        hits = calculateHits()
    }

    private fun calculateScore(): Int {
        myNumbers.filter { winningNumbers.contains(it) }.size
        val wins = myNumbers.filter { winningNumbers.contains(it) }.size
        return if (wins == 0) 0 else 2.0.pow((wins - 1).toDouble()).toInt()
    }

    private fun calculateHits(): Int {
        return if (score == 0) 0 else log(score.toDouble(), 2.0).toInt() + 1
    }
}
