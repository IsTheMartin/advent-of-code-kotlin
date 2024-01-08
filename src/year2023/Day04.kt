import year2023.println
import year2023.readInput
import kotlin.math.pow

fun main() {
    fun part1(input: List<String>): Int {
        return input.map { line ->
            val winningNumbers = line.substringAfter(":")
                .substringBefore("|")
                .split(" ")
                .filter { it.isNotEmpty() || it == " " }
            val myNumbers = line.substringAfter("|")
                .split(" ")
                .filter { it.isNotEmpty() || it == " " }
            winningNumbers.intersect(myNumbers).size
        }.sumOf {
            2.0.pow(it - 1).toInt()
        }
    }

    fun part2(input: List<String>): Int {
        val cardMatches = input.map { line ->
            val winningNumbers = line.substringAfter(":")
                .substringBefore("|")
                .split(" ")
                .filter { it.isNotEmpty() || it == " " }
            val myNumbers = line.substringAfter("|")
                .split(" ")
                .filter { it.isNotEmpty() || it == " " }
            winningNumbers.intersect(myNumbers).size
        }

        val cards = IntArray(cardMatches.size) { 1 }
        cardMatches.forEachIndexed { index, matches ->
            repeat(matches) {
                val pos = index + it + 1
                cards[index + it + 1] += cards[index]
            }
        }
        return cards.sum()
    }

    val testInput = readInput("Day04_test1")
    check(part1(testInput) == 13)

    val testInput2 = readInput("Day04_test2")
    check(part2(testInput2) == 30)

    val input = readInput("Day04")
    part1(input).println()
    part2(input).println()
}


fun getNumbersInCard(input: String): List<String> {
    return input.trim()
        .replace("\\s+".toRegex(), " ")
        .split(" ")
}