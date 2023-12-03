package controllers

import models.WorkoutPlan
import persistence.Serializer

/**
 * Class responsible for managing workout plans, providing various operations such as creation, listing,
 * updating, and deletion of workout plans.
 *
 * @property serializer The serializer used for data persistence.
 */
class WorkoutPlannerAPI(serializer: Serializer) {

    private var serializer: Serializer = serializer
    private var workoutPlans = ArrayList<WorkoutPlan>()

    /**
     * Creates a new workout plan and adds it to the list of workout plans.
     *
     * @param workoutPlan The workout plan to create.
     * @return `true` if the workout plan is successfully created and added, `false` otherwise.
     */
    fun create(workoutPlan: WorkoutPlan): Boolean {
        return workoutPlans.add(workoutPlan)
    }

    /**
     * Retrieves a formatted string representing all stored workout plans.
     *
     * @return A formatted string containing information about all workout plans.
     */
    fun listAllWorkoutPlans(): String {
        return if (workoutPlans.isEmpty()) {
            "No workout Plans Stored"
        } else {
            var listOfWorkoutPlans = ""
            for (i in workoutPlans.indices) {
                listOfWorkoutPlans += "$i: ${workoutPlans[i]} \n"
            }
            listOfWorkoutPlans
        }
    }

    /**
     * Retrieves the number of stored workout plans.
     *
     * @return The number of stored workout plans.
     */
    fun numberOfWorkoutPlans(): Int {
        return workoutPlans.size
    }

    /**
     * Finds a workout plan by its index in the list.
     *
     * @param index The index of the workout plan.
     * @return The found workout plan or `null` if the index is invalid.
     */
    fun findWorkoutPlan(index: Int): WorkoutPlan? {
        return if (isValidListIndex(index, workoutPlans)) {
            workoutPlans[index]
        } else {
            null
        }
    }

    /**
     * Checks if an index is a valid index in a given list.
     *
     * @param index The index to check.
     * @param list The list to check against.
     * @return `true` if the index is valid, `false` otherwise.
     */
    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    /**
     * Checks if an index is a valid index in the list of workout plans.
     *
     * @param index The index to check.
     * @return `true` if the index is valid, `false` otherwise.
     */
    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, workoutPlans)
    }

    /**
     * Updates an existing workout plan based on its index.
     *
     * @param indexToUpdate The index of the workout plan to update.
     * @param workoutPlan The new workout plan data.
     * @return `true` if the update is successful, `false` otherwise.
     */
    fun update(indexToUpdate: Int, workoutPlan: WorkoutPlan?): Boolean {
        val foundWorkoutPlan = findWorkoutPlan(indexToUpdate)
        if ((foundWorkoutPlan != null) && (workoutPlan != null)) {
            foundWorkoutPlan.workoutTitle = workoutPlan.workoutTitle
            foundWorkoutPlan.workoutDescription = workoutPlan.workoutDescription
            foundWorkoutPlan.workoutType = workoutPlan.workoutType
            return true
        }
        return false
    }

    /**
     * Loads workout plans from the data store.
     *
     * @throws Exception If there is an error during the loading process.
     */
    @Throws(Exception::class)
    fun load() {
        workoutPlans = serializer.read() as ArrayList<WorkoutPlan>
    }

    /**
     * Stores workout plans to the data store.
     *
     * @throws Exception If there is an error during the storing process.
     */
    @Throws(Exception::class)
    fun store() {
        serializer.write(workoutPlans)
    }

    /**
     * Deletes a workout plan based on its index.
     *
     * @param indexToDelete The index of the workout plan to delete.
     * @return The deleted workout plan or `null` if the index is invalid.
     */
    fun deleteWorkoutPlan(indexToDelete: Int): WorkoutPlan? {
        return if (isValidListIndex(indexToDelete, workoutPlans)) {
            workoutPlans.removeAt(indexToDelete)
        } else {
            null
        }
    }

    /**
     * Formats a list of workout plans into a string.
     *
     * @param workoutPlansToFormat The list of workout plans to format.
     * @return A formatted string containing information about the workout plans.
     */
    private fun formatListString(workoutPlansToFormat: List<WorkoutPlan>): String =
        workoutPlansToFormat
            .joinToString(separator = "\n") { workoutPlan ->
                workoutPlans.indexOf(workoutPlan).toString() + ": " + workoutPlan.toString()
            }

    /**
     * Searches for workout plans by title and returns a formatted string with the results.
     *
     * @param searchString The title to search for.
     * @return A formatted string with workout plans matching the search criteria.
     */
    fun searchByTitle(searchString: String): String {
        return formatListString(
            workoutPlans.filter { workoutPlan -> workoutPlan.workoutTitle.contains(searchString, ignoreCase = true) }
        )
    }

    /**
     * Searches for workout plans by description and returns a formatted string with the results.
     *
     * @param searchString The description to search for.
     * @return A formatted string with workout plans matching the search criteria.
     */
    fun searchByDescription(searchString: String): String {
        return formatListString(
            workoutPlans.filter { workoutPlan ->
                workoutPlan.workoutDescription.contains(
                    searchString,
                    ignoreCase = true
                )
            }
        )
    }

    /**
     * Retrieves a formatted string containing all workout titles.
     *
     * @return A formatted string with all workout titles.
     */
    fun listAllWorkoutTitles(): String {
        return if (workoutPlans.isEmpty()) {
            "No workout Titles "
        } else {
            workoutPlans.joinToString("\n") { it.workoutTitle }
        }
    }
}

