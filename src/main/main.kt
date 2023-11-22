private val noteAPI = NoteAPI(YAMLSerialiser(File("notes.YAML")))
private val logger = KotlinLogging.logger {}

fun main(args: Array<String>) {
    runMenu()
}