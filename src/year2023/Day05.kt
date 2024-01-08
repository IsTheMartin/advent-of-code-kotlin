package year2023

fun main() {

    fun part1(input: List<String>): Long {
        val seeds: List<Long> = parseSeeds1(input)
        val ranges: List<Set<RangePair>> = parseRanges(input)

        return seeds.minOf { seed ->
            ranges.fold(seed) { acc, ranges ->
                ranges.firstOrNull { acc in it }?.translate(acc) ?: acc
            }
        }
    }

    fun part2(input: List<String>): Long {
        val seeds: Set<LongRange> = parseSeeds2(input)
        val ranges: List<Set<RangePair>> = parseRanges(input)

        val rangesReversed = ranges.map { range ->
            range.map { it.flip() }
        }.reversed()

        return generateSequence(0L, Long::inc)
            .first { location ->
                val seed = rangesReversed
                    .fold(location) { acc, ranges ->
                        ranges.firstOrNull { acc in it }?.translate(acc) ?: acc
                    }
                seeds.any { seedRange -> seed in seedRange }
            }
    }

    val testInput = readInput("Day05_test1")
    check(part1(testInput) == 35L)

    val testInput2 = readInput("Day05_test1")
    check(part2(testInput2) == 46L)

    val input = readInput("Day05")
    part1(input).println()
    part2(input).println()
}

fun parseSeeds1(input: List<String>): List<Long> {
    return input
        .first()
        .substringAfter(":")
        .trim()
        .split(" ")
        .map { it.toLong() }
}

fun parseSeeds2(input: List<String>): Set<LongRange> {
    return input
        .first()
        .substringAfter(":")
        .trim()
        .split(" ")
        .map { it.toLong() }
        .chunked(2)
        .map { it.first()..<it.first() + it.last() }
        .toSet()
}

fun parseRanges(input: List<String>): List<Set<RangePair>> {
    return input
        .drop(2)
        .joinToString("\n")
        .split("\n\n")
        .map {
            it.split("\n")
                .drop(1)
                .map { line -> RangePair.of(line) }
                .toSet()
        }
}

data class RangePair(
    private val source: LongRange,
    private val destination: LongRange
) {
    fun translate(num: Long): Long = destination.first + (num - source.first)

    fun flip(): RangePair = RangePair(destination, source)

    operator fun contains(num: Long): Boolean = num in source

    companion object {
        fun of(line: String): RangePair {
            val parts = line.split(" ").map { it.toLong() }
            return RangePair(
                parts[1]..<(parts[1] + parts[2]),
                parts[0]..<(parts[0] + parts[2])
            )
        }
    }
}