import year2023.println
import year2023.readInput

fun main() {
    fun part1(input: List<String>): Int {
        var sum = 0
            input.forEachIndexed { index, line ->
                val cubes = getCubesFromLine(line)
                val blueMax = cubes.last { it.second == "blue" }.first
                val redMax = cubes.last { it.second == "red" }.first
                val greenMax = cubes.last { it.second == "green" }.first
                sum += if (redMax <= 12 && greenMax <= 13 && blueMax <= 14) index + 1 else 0
            }
        return sum
    }

    fun part2(input: List<String>): Int {
        var power = 0
        input.forEach { line ->
            val cubes = getCubesFromLine(line)
            val blueMax = cubes.last { it.second == "blue" }.first
            val redMax = cubes.last { it.second == "red" }.first
            val greenMax = cubes.last { it.second == "green" }.first
            power += (blueMax * redMax * greenMax)
        }
        return power
    }

    val testInput = readInput("Day02_test1")
    check(part1(testInput) == 8)

    val testInput2 = readInput("Day02_test1")
    check(part2(testInput2) == 2286)

    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}

fun getCubesFromLine(line: String): List<Pair<Int, String>> {
    return line
        .substringAfter(": ")
        .split(", ", "; ")
        .map { cube ->
            val (quantity, color) = cube.split(' ')
            quantity.toInt() to color
        }
        .sortedBy { it.first }
}