import mu.KotlinLogging
import utils.ScannerInput
import java.lang.System.exit


private val logger = KotlinLogging.logger {}
fun main(args: Array<String>) {
    runMenu()
}
fun runMenu() {

    fun mainMenu() : Int {
        return ScannerInput.readNextInt("""
         > ----------------------------------
         > |        NOTE KEEPER APP         |
         > ----------------------------------
         > | NOTE MENU                      |
         > |   1) Create a Workout Plan     |
         > |   2) List all Workout Plans    |
         > |   3) Update a Workout Plan     |
         > |   4) Delete a Workout Plan     |
         > ----------------------------------
         > |   0) Exit                      |
         > ----------------------------------
         > ==>> """.trimMargin(">"))
    }

    do {
        val option = mainMenu()
        when (option) {
            1  -> createWorkoutPlan()
            2  -> listWorkoutPlans()
            3  -> updateWorkoutPlans()
            4  -> deleteWorkoutPlans()
            0  -> exitApp()
            else -> println("Invalid option entered: $option")
        }
    } while (true)
}
fun createWorkoutPlan(){
    logger.info { "createWorkoutPlan() function invoked" }
}

fun listWorkoutPlans(){
    logger.info { "listWorkoutPlans() function invoked" }
}

fun updateWorkoutPlans(){
    logger.info { "updateWorkoutPlans() function invoked" }
}

fun deleteWorkoutPlans(){
    logger.info { "deleteWorkoutPlans() function invoked" }
}

fun exitApp(){
    println("Exiting...bye")
    exit(0)
}
