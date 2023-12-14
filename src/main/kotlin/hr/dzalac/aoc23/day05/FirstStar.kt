package hr.dzalac.aoc23.day05

import hr.dzalac.aoc23.FileReader

fun main() {
    val input = FileReader(5)
    val maps: MutableList<MutableMap<LongRange, Long>> = mutableListOf()

    val seeds = input.nextLine()!!.split(": ")[1].split(' ').map { it.toLong() }
    parseMaps(input, maps)

    var min = Long.MAX_VALUE
    seeds.forEach { seed ->
        var source = seed
        maps.forEach { map ->
            var destination = source
            for (range in map.keys) {
                if (destination in range) {
                    destination = map[range]!! + range.indexOf(destination)
                    break
                }
            }
            source = destination
        }
        if (source < min) min = source
    }

    println(min)
}

private fun parseMaps(
    input: FileReader,
    maps: MutableList<MutableMap<LongRange, Long>>,
) {
    var line = input.nextLine()
    var map = -1
    while (line != null) {
        if (line.isEmpty()) {
            input.nextLine()
            line = input.nextLine()!!
            maps.add(mutableMapOf())
            map += 1
        }
        val tmp = line.split(' ')
        val destination = tmp[0].toLong()
        val source = tmp[1].toLong()
        val range = tmp[2].toLong()
        maps[map][LongRange(source, source + range - 1)] = destination
        line = input.nextLine()
    }
}
