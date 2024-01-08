import year2023.println
import year2023.readInput

fun main() {
    fun part1(input: List<String>): Int {
        val (numbers, symbols) = parseInput(input)
        return numbers
            .filter { number -> number.isAdjacentToAny(symbols) }
            .sumOf { it.toInt() }
    }

    fun part2(input: List<String>): Int {
        val (numbers, symbols) = parseInput(input) { it == '*' }
        return symbols.sumOf { gearPosition ->
            val neighbors = numbers.filter { it.isAdjacentTo(gearPosition) }
            if (neighbors.size == 2) {
                neighbors.first().toInt() * neighbors.last().toInt()
            } else 0
        }
    }

    val testInput = readInput("Day03_test1")
    check(part1(testInput) == 4361)

    val testInput2 = readInput("Day03_test1")
    check(part2(testInput2) == 467835)

    val input = readInput("Day03")
    part1(input).println()
    part2(input).println()
}

private fun parseInput(
    input: List<String>,
    takeSymbol: (Char) -> Boolean = { it != '.' }
):Pair<Set<NumberLocation>, Set<Point2d>> {
    val numbers = mutableSetOf<NumberLocation>()
    val symbols = mutableSetOf<Point2d>()
    var workingNumber = NumberLocation()

    input.forEachIndexed { y, line ->
        line.forEachIndexed { x, char ->
            if (char.isDigit()) {
                workingNumber.add(char, Point2d(x, y))
            } else {
                if(workingNumber.isNotEmpty()) {
                    numbers.add(workingNumber)
                    workingNumber = NumberLocation()
                }
                if(takeSymbol(char)) {
                    symbols.add(Point2d(x, y))
                }
            }
        }
        if (workingNumber.isNotEmpty()) {
            numbers.add(workingNumber)
            workingNumber = NumberLocation()
        }
    }
    return numbers to symbols
}

data class Point2d(
    val x: Int,
    val y: Int
) {
    fun neighbors(): Set<Point2d> =
        setOf(
            Point2d(x - 1, y - 1),
            Point2d(x, y - 1),
            Point2d(x + 1, y - 1),
            Point2d(x - 1, y),
            Point2d(x + 1, y),
            Point2d(x - 1, y + 1),
            Point2d(x, y + 1),
            Point2d(x + 1, y + 1)
        )
}
private class NumberLocation {
    val number = mutableListOf<Char>()
    val locations = mutableListOf<Point2d>()

    fun add(c: Char, location: Point2d) {
        number.add(c)
        locations.addAll(location.neighbors())
    }

    fun isNotEmpty() = number.isNotEmpty()

    fun isAdjacentToAny(points: Set<Point2d>): Boolean = locations.intersect(points).isNotEmpty()

    fun isAdjacentTo(point2d: Point2d): Boolean = point2d in locations

    fun toInt(): Int = number.joinToString("").toInt()
}