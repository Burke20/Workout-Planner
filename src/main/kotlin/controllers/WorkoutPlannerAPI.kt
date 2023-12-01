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
                listOfWorkoutPlans += "${i}: ${workoutPlans[i]} \n"
            }
            listOfWorkoutPlans
        }
    }

}