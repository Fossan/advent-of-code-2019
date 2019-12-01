package commons

import java.io.File
import java.nio.file.Paths

class FileReader {
    companion object {
        fun read(day: Days): List<String> = File(day.pathToInput()).bufferedReader().readLines()

        private fun Days.pathToInput(): String =
            Paths.get("").toAbsolutePath().toString() + "/src/${this.name}/${this.inputName}"
    }
}