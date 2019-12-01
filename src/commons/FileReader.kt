package commons

import java.io.File
import java.nio.file.Paths

class FileReader {
    companion object {
        fun read(day: Days): List<String> = File(resolvePath(day)).bufferedReader().readLines()

        private fun resolvePath(day: Days): String =
            Paths.get("").toAbsolutePath().toString() + "/src/${day.name}/${day.inputName}"
    }
}