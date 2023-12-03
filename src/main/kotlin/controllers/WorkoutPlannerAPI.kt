package controllers

import models.WorkoutPlan

class WorkoutPlannerAPI {
    private var workoutPlans = ArrayList<WorkoutPlan>()

    fun create(workoutPlan: WorkoutPlan): Boolean {
        return workoutPlans.add(workoutPlan)
    }

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

    fun numberOfWorkoutPlans(): Int {
        return workoutPlans.size
    }

    fun findWorkoutPlan(index: Int): WorkoutPlan? {
        return if (isValidListIndex(index, workoutPlans)) {
            workoutPlans[index]
        } else {
            null
        }
    }

    fun isValidListIndex(index: Int, list: List<Any>): Boolean {
        return (index >= 0 && index < list.size)
    }

    fun isValidIndex(index: Int): Boolean {
        return isValidListIndex(index, workoutPlans)
    }

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

    fun deleteWorkoutPlan(indexToDelete: Int): WorkoutPlan? {
        return if (isValidListIndex(indexToDelete, workoutPlans)) {
            workoutPlans.removeAt(indexToDelete)
        } else {
            null
        }
    }

    private fun formatListString(workoutPlansToFormat: List<WorkoutPlan>): String =
        workoutPlansToFormat
            .joinToString(separator = "\n") { workoutPlan ->
                workoutPlans.indexOf(workoutPlan).toString() + ": " + workoutPlan.toString()
            }

    fun searchByTitle(searchString: String): String {
        return formatListString(
            workoutPlans.filter { workoutPlan -> workoutPlan.workoutTitle.contains(searchString, ignoreCase = true) }
        )
    }

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
    fun listAllWorkoutTitles(): String {
        return if (workoutPlans.isEmpty()) {
            "No workout Titles "
        } else {
            workoutPlans.joinToString("\n") { it.workoutTitle }
        }
    }
}
