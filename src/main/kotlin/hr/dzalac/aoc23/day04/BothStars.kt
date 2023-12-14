package hr.dzalac.aoc23.day04

import hr.dzalac.aoc23.FileReader

fun main() {
    val input = FileReader(4)

    var result1 = 0
    var result2 = 0

    val cardCopyCounter: MutableMap<CardId, Int> = mutableMapOf()

    input.forEachLine {
        val scratchCard = ScratchCard(it)
        val copies = cardCopyCounter.remove(scratchCard.id) ?: 1
        if (scratchCard.score > 0) {
            for (i in 1..copies) {
                for (j in 1..scratchCard.hits) {
                    val nextId = scratchCard.id.next(j)
                    val count = cardCopyCounter.getOrDefault(nextId, 1)
                    cardCopyCounter[nextId] = count + 1
                }
            }
        }
        result1 += scratchCard.score
        result2 += copies
    }

    println(result1)
    println(result2)
}
