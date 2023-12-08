package hr.dzalac.aoc23.day02

class CubeGame(line: String) {
    val id: Int
    private val sets: MutableList<Map<COLOR, Int>> = mutableListOf()

    companion object {
        enum class COLOR {
            RED,
            GREEN,
            BLUE,
        }
    }

    init {
        val tmp = line.split(": ")
        id = tmp[0].split(' ')[1].toInt()
        tmp[1].split("; ").forEach { set ->
            val setMap = mutableMapOf<COLOR, Int>()
            set.split(", ").forEach {
                val cube = it.split(' ')
                val count = cube[0].toInt()
                val color = COLOR.valueOf(cube[1].uppercase())
                setMap[color] = count
            }
            sets.add(setMap)
        }
    }

    fun isPossible(limit: Map<COLOR, Int>): Boolean {
        sets.forEach { set -> set.forEach { if (it.value > limit[it.key]!!) return false } }
        return true
    }

    private fun minimumCubes(): Map<COLOR, Int> {
        val min =
            mutableMapOf(
                COLOR.RED to 0,
                COLOR.GREEN to 0,
                COLOR.BLUE to 0,
            )
        sets.forEach { set ->
            set.forEach {
                if (min[it.key]!! < it.value) min[it.key] = it.value
            }
        }
        return min
    }

    fun getMinimumSetPower(): Int {
        var count = 1
        minimumCubes().forEach { count *= it.value }
        return count
    }
}
