
import controllers.WorkoutPlannerAPI
import mu.KotlinLogging
import persistence.XMLSerializer
import utils.ScannerInput
import java.io.File
import java.lang.System.exit

// Initialize the WorkoutPlannerAPI and logger
private val workoutPlannerAPI = WorkoutPlannerAPI(XMLSerializer(File("WorkoutPlanner.xml")))
private val logger = KotlinLogging.logger {}

/**
 * Main function to start the workout planner application.
 *
 * @param args Command-line arguments.
 */
fun main(args: Array<String>) {
    runMenu()
}

/**
 * Function to run the main menu loop and handle user input.
 */
fun runMenu() {
    do {
        val option = mainMenu()
        when (option) {
            1 -> createWorkoutPlan()
            2 -> listWorkoutPlans()
            3 -> updateWorkoutPlans()
            4 -> deleteWorkoutPlans()
            5 -> searchWorkoutByTitle("YourSearchTitle")
            6 -> searchWorkoutByDescription("YourSearchDescription")
            7 -> allWorkoutTitles()
            20 -> save()
            21 -> load()
            0 -> exitApp()
            else -> println("Invalid option entered: $option")
        }
    } while (true)
}

/**
 * Displays the main menu and reads the user's menu choice.
 *
 * @return The user's menu choice.
 */
fun mainMenu(): Int {
    return ScannerInput.readNextInt(
        """
         > ----------------------------------
         > |        Workout Planner app     |
         > ----------------------------------
         > | NOTE MENU                      |
         > |   1) Create a Workout Plan     |
         > |   2) List all Workout Plans    |
         > |   3) Update a Workout Plan     |
         > |   4) Delete a Workout Plan     |
         > |   5) Search by Title           |
         > |   6) Search by Description     |
         > |   7) List Workout Plan Titles  |  
         > ----------------------------------
         > |   20) Save notes               |
         > |   21) Load notes               |
         > |   0) Exit                      |
         > ----------------------------------
         > ==>> """.trimMargin(">")
    )
}

/**
 * Function to create a new workout plan based on user input.
 */
fun createWorkoutPlan() {
    // ... (Existing code for creating a workout plan)
}

/**
 * Function to list all existing workout plans.
 */
fun listWorkoutPlans() {
    // ... (Existing code for listing workout plans)
}

/**
 * Function to update an existing workout plan based on user input.
 */
fun updateWorkoutPlans() {
    // ... (Existing code for updating a workout plan)
}

/**
 * Function to delete an existing workout plan based on user input.
 */
fun deleteWorkoutPlans() {
    // ... (Existing code for deleting a workout plan)
}

/**
 * Function to search for a workout plan by title and display the result.
 *
 * @param title The title to search for.
 */
fun searchWorkoutByTitle(title: String) {
    // ... (Existing code for searching workout plans by title)
}

/**
 * Function to search for a workout plan by description and display the result.
 *
 * @param description The description to search for.
 */
fun searchWorkoutByDescription(description: String) {
    // ... (Existing code for searching workout plans by description)
}

/**
 * Function to display all workout titles.
 */
fun allWorkoutTitles() {
    // ... (Existing code for displaying all workout titles)
}

/**
 * Function to save workout plans to a file.
 */
fun save() {
    try {
        workoutPlannerAPI.store()
    } catch (e: Exception) {
        System.err.println("Error writing to file: $e")
    }
}

/**
 * Function to load workout plans from a file.
 */
fun load() {
    try {
        workoutPlannerAPI.load()
    } catch (e: Exception) {
        System.err.println("Error reading from file: $e")
    }
}

/**
 * Function to exit the application.
 */
fun exitApp() {
    println("Exiting...bye")
    exit(0)
}

