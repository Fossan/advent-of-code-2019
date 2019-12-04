package day2

import commons.Days
import commons.FileReader
import kotlin.test.assertTrue

class Day2 {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val codes: List<Int> = FileReader.read(Days.DAY2).first()
                .split(",")
                .map { it.toInt() }

            checkAssertions()
            println(calculateInput(codes.toMutableList()))
            println(calculateNounAndVerb(codes))
        }

        private fun calculateInput(codes: MutableList<Int>, noun: Int = 12, verb: Int = 2): Int {
            codes.normalizeSize()
            codes[1] = noun
            codes[2] = verb
            var index = 0
            do {
                val chunk = Command(codes.subList(index, index + 4))
                when (chunk.opcode) {
                    1 -> {
                        val result = codes[chunk.val1] + codes[chunk.val2]
                        codes[chunk.position] = result
                    }
                    2 -> {
                        val result = codes[chunk.val1] * codes[chunk.val2]
                        codes[chunk.position] = result
                    }
                }
                index += 4
            } while (chunk.opcode != 99)
            return codes.first()
        }

        private fun calculateNounAndVerb(codes: List<Int>): Int {
            var noun = 0
            var verb = 0
            val result = 19690720

            do {
                val res = calculateInput(codes.toMutableList(), noun, verb)
                if (res != result) {
                    if (noun < 99) {
                        noun++
                    } else {
                        verb++
                        noun = 0
                    }
                }
            } while (res != result)
            return 100 * noun + verb
        }

        private fun MutableList<Int>.normalizeSize() {
            repeat(this.size - this.size % 4) {
                this.add(0)
            }
        }

        private fun checkAssertions() {
            assertTrue(calculateInput(mutableListOf(1, 1, 1, 4, 99, 5, 6, 0, 99), 0, 0) == 30)
            assertTrue(calculateInput(mutableListOf(2, 4, 4, 5, 99, 0), 0, 0) == 2)
        }

        data class Command(val codes: List<Int>) {
            val opcode: Int = codes[0]
            val val1: Int = codes[1]
            val val2: Int = codes[2]
            val position: Int = codes[3]
        }
    }
}