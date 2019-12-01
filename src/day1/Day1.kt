package day1

import commons.Days
import commons.FileReader.Companion.read
import kotlin.test.assertTrue

class Day1 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            checkAssertions()
            val fuelList: List<String> = read(Days.DAY1)
            println(fuelList.calculate { fuel(it) })
            println(fuelList.calculate { totalFuel(it) })
        }

        private fun List<String>.calculate(calculate: (Int) -> Int) = this.stream()
            .mapToInt { calculate(it.toInt()) }
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