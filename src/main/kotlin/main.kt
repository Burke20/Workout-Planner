
import controllers.WorkoutPlannerAPI
import models.Exercise
import models.WorkoutPlan
import mu.KotlinLogging
import persistence.XMLSerializer
import utils.ScannerInput
import utils.ScannerInput.readNextInt
import utils.ScannerInput.readNextLine
import java.io.File
import java.lang.System.exit

private val workoutPlannerAPI = WorkoutPlannerAPI(XMLSerializer(File("WorkoutPlanner.xml")))
private val logger = KotlinLogging.logger {}
fun main(args: Array<String>) {
    runMenu()
}
fun runMenu() {
    fun mainMenu(): Int {
        return ScannerInput.readNextInt(
            """
         > ----------------------------------
         > |        NOTE KEEPER APP         |
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
fun createWorkoutPlan() {
    val workoutTitle = readNextLine("Enter a title for the Workout: ")
    val workoutDescription = readNextLine("Enter a Description of your Workout: ")
    val workoutDuration = readNextInt("Select Duration of the Workout: (1.Short 2.Medium 3.Long) :  ")
    val workoutType = readNextInt("Enter the Type of workout you want (1.Legs 2.Arms 3.Chest 4.Full Body) : ")
    val exercises = mutableListOf<Exercise>()
    val addExercise = readNextLine("Press Enter to access Exercises ")
    val exerciseName = readNextLine(
        "Enter the name of the exercise: Select Exercise from List\n" +
            " Dumbbell Curls\n" +
            " Sit ups\n" +
            " Press Ups\n" +
            " Chin Ups\n" +
            " Bench Press\n" +
            " Squats\n" +
            " Cable curls\n" +
            " Running\n" +
            " Walking\n"
    )
    val exerciseType = readNextLine(
        "Enter the type of the exercise:" +
            "\n Full Body\n " +
            "Arms\n " +
            "Legs" +
            "\n Cardio" +
            "\n Low Equipment" +
            "\n Machines" +
            "\n Core" +
            "\n Back\n"
    )
    val exerciseDuration = readNextLine(
        "Enter the duration of the exercise: \n Short" +
            "\n Medium" +
            "\n Long\n"
    )
    exercises.add(Exercise(exerciseName, exerciseType, exerciseDuration))

    val isCreated =
        workoutPlannerAPI.create(WorkoutPlan(workoutTitle, workoutDescription, workoutDuration, workoutType, exercises))

    if (isCreated) {
        println("Created Successfully")
    } else {
        println("Creation Failed")
    }
}
fun listWorkoutPlans() {
    println(workoutPlannerAPI.listAllWorkoutPlans())
}
fun updateWorkoutPlans() {
    listWorkoutPlans()
    if (workoutPlannerAPI.numberOfWorkoutPlans() > 0) {
        val indexToUpdate = readNextInt("Enter the index of the workout Plan to update: ")
        if (workoutPlannerAPI.isValidIndex(indexToUpdate)) {
            val workoutTitle = readNextLine("Enter a title for the Workout Plan: ")
            val workoutDescription = readNextLine("Enter a Description for the Workout Plan: ")
            val workoutDuration = readNextInt("Enter a Duration for the Workout (1.Short 2.Medium 3.Long): ")
            val workoutType = readNextInt("Enter a Workout Type (1.Legs 2.Arms 3.Chest 4.Full Body) ")
            val exercises = mutableListOf<Exercise>()
            val updateExercise = readNextLine("Press Enter to access Exercises ")
            val exerciseName = readNextLine(
                "Enter the name of the exercise: Select Exercise from List\n" +
                    " Dumbbell Curls\n" +
                    " Sit ups\n" +
                    " Press Ups\n" +
                    " Chin Ups\n" +
                    " Bench Press\n" +
                    " Squats\n" +
                    " Cable curls\n" +
                    " Running\n" +
                    " Walking\n"
            )
            val exerciseType = readNextLine(
                "Enter the type of the exercise:" +
                    "\n Full Body\n " +
                    "Arms\n " +
                    "Legs" +
                    "\n Cardio" +
                    "\n Low Equipment" +
                    "\n Machines" +
                    "\n Core" +
                    "\n Back\n"
            )
            val exerciseDuration = readNextLine(
                "Enter the duration of the exercise: \n Short" +
                    "\n Medium" +
                    "\n Long\n"
            )
            exercises.add(Exercise(exerciseName, exerciseType, exerciseDuration))

            if (workoutPlannerAPI.update(indexToUpdate, WorkoutPlan(workoutTitle, workoutDescription, workoutDuration, workoutType, exercises))) {
                println("Update Successful")
            } else {
                println("Update Failed")
            }
        } else {
            println("There are no Workout Plans for this index number")
        }
    }
}

fun deleteWorkoutPlans() {
    listWorkoutPlans()
    if (workoutPlannerAPI.numberOfWorkoutPlans() > 0) {
        val indexToDelete = readNextInt("Enter the index of the Workout Plan to delete: ")
        var workoutPlanToDelete = workoutPlannerAPI.deleteWorkoutPlan(indexToDelete)
        if (workoutPlanToDelete != null) {
            println("Delete Successfull! Deleted Workout Plan: ${workoutPlanToDelete.workoutTitle}")
        } else {
            println("Delete NOT Successful")
        }
    }
}
fun searchWorkoutByTitle(title: String) {
    val titleToSearch = readNextLine("Enter the title of the Workout Plan to search: ")
    val searchResult = workoutPlannerAPI.searchByTitle(titleToSearch)

    if (searchResult.isNotEmpty()) {
        println(searchResult)
    } else {
        println("No search results")
    }
}
fun searchWorkoutByDescription(description: String) {
    val descriptionToSearch = readNextLine("Enter a Description of the Workout Plan to search: ")
    val searchResult = workoutPlannerAPI.searchByDescription(descriptionToSearch)

    if (searchResult.isNotEmpty()) {
        println(searchResult)
    } else {
        println("No search results")
    }
}
fun allWorkoutTitles() {
    val workoutTitles = workoutPlannerAPI.listAllWorkoutTitles()
    println("Workout Titles:\n$workoutTitles")
}
    fun save() {
        try {
            workoutPlannerAPI.store()
        } catch (e: Exception) {
            System.err.println("Error writing to file: $e")
        }
    }

    /**
     * Loads notes from a file.
     */
    fun load() {
        try {
            workoutPlannerAPI.load()
        } catch (e: Exception) {
            System.err.println("Error reading from file: $e")
        }
    }
fun exitApp() {
    println("Exiting...bye")
    exit(0)
}
