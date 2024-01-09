package year2023

fun main() {
    fun part1(input: List<String>): Int {
        val raceDurations = input.first()
            .substringAfter(":")
            .cleanData()
        val distances = input.last()
            .substringAfter(":")
            .cleanData()

        return raceDurations.mapIndexed { index, duration ->
            val distanceToBeat = distances[index].toInt()
            (0..duration.toInt())
                .toList()
                .count { result ->
                    ((duration.toInt() - result) * result) > distanceToBeat
                }
        }.reduce { acc, i -> acc * i }
    }

    fun part2(input: List<String>): Int {
        val raceDurations = input.first()
            .substringAfter(":")
            .cleanData()
            .joinToString("")
            .toLong()
        val distances = input.last()
            .substringAfter(":")
            .cleanData()
            .joinToString("")
            .toLong()
        return (0..raceDurations)
                .count { result ->
                    ((raceDurations.toInt() - result) * result) > distances
        }
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test1")
    check(part1(testInput) == 288)

    val testInput2 = readInput("Day06_test1")
    check(part2(testInput2) == 71503)

    val input = readInput("Day06")
    part1(input).println()
    part2(input).println()
}

fun String.cleanData(): List<String> = this
    .trim()
    .split(" ")
    .filter { it.isNotEmpty() || it == " " }