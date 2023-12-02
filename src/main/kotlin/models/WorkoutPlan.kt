package models

data class WorkoutPlan(
    var workoutTitle: String,
    var workoutDescription: String,
    var workoutDuration: Int,
    var workoutType:Int,

)

data class Exercise(
   var exerciseName: String,
    var exerciseType: Int,
    var exerciseDuration: Int,

)