package day1

import commons.Days
import commons.FileReader.Companion.read
import kotlin.test.assertTrue

class Day1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            checkAssertions()
            val masses: List<String> = read(Days.DAY1)
            println(masses.calculate { fuel(it) })
            println(masses.calculate { totalFuel(it) })
        }

        private fun List<String>.calculate(calculation: (Int) -> Int) = this.stream()
            .mapToInt { calculation(it.toInt()) }
            .sum()

        private fun fuel(mass: Int): Int = Math.floorDiv(mass, 3) - 2

        private fun totalFuel(mass: Int): Int {
            val requiredFuel = fuel(mass)
            return if (requiredFuel <= 0) 0 else requiredFuel + totalFuel(requiredFuel)
        }

        private fun checkAssertions() {
            assertTrue(fuel(100756) == 33583)
            assertTrue(totalFuel(100756) == 50346)
        }
    }
}