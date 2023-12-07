package hr.dzalac.aoc23

import java.io.File
import java.nio.file.Paths

class FileReader(day: Short) {
    private var file: File

    init {
        val inputsDir = "src\\main\\resources\\inputs"
        val dayDir = "day" + String.format("%02d", day) + "\\input"
        file = File(Paths.get(inputsDir, dayDir).toAbsolutePath().toString())
    }

    fun readLines() = file.readLines()

    fun forEachLine(action: (String) -> Unit) = file.forEachLine(action = action)
}
