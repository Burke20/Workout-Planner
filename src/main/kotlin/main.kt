import utils.ScannerInput
import java.lang.System.exit



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
    println("You chose Add Note")
}

fun listWorkoutPlans(){
    println("You chose List Notes")
}

fun updateWorkoutPlans(){
    println("You chose Update Note")
}

fun deleteWorkoutPlans(){
    println("You chose Delete Note")
}

fun exitApp(){
    println("Exiting...bye")
    exit(0)
}
