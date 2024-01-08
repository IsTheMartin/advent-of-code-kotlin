import year2023.println
import year2023.readInput

fun main() {
    val digitsMap = hashMapOf(
        "one" to 1,
        "1" to 1,
        "2" to 2,
        "two" to 2,
        "3" to 3,
        "three" to 3,
        "4" to 4,
        "four" to 4,
        "5" to 5,
        "five" to 5,
        "6" to 6,
        "six" to 6,
        "7" to 7,
        "seven" to 7,
        "8" to 8,
        "eight" to 8,
        "9" to 9,
        "nine" to 9
    )

    fun part1(input: List<String>): Int {
        return input.sumOf { line ->
            val firstDigit = line.first { it.isDigit() }.toString()
            val lastDigit = line.last { it.isDigit() }.toString()
            (firstDigit + lastDigit).toInt()
        }
    }

    fun part2(input: List<String>): Int {
        return input.sumOf { line ->
            val firstDigit = line.findAnyOf(digitsMap.keys)?.second
            val lastDigit = line.findLastAnyOf(digitsMap.keys)?.second
            (digitsMap[firstDigit].toString() + digitsMap[lastDigit]?.toString()).toInt()
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 142)

    val testInput2 = readInput("Day01_test2")
    check(part2(testInput2) == 281)

    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
