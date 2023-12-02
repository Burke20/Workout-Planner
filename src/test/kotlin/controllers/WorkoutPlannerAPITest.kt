package controllers

import models.WorkoutPlan
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class WorkoutPlannerAPITest {

    private var fullBodyWorkoutPlan: WorkoutPlan? = null
    private var legDayWorkoutPlan: WorkoutPlan? = null
    private var strengthWorkoutPlan: WorkoutPlan? = null
    private var cardioWorkoutPlan: WorkoutPlan? = null
    private var muscleGrowthWorkoutPlan: WorkoutPlan? = null
    private var populatedWorkoutPlans: WorkoutPlannerAPI? = WorkoutPlannerAPI()
    private var emptyWorkoutPlans: WorkoutPlannerAPI? = WorkoutPlannerAPI()

    @BeforeEach
    fun setup() {
        fullBodyWorkoutPlan = WorkoutPlan("Full Body Workout", "Full Body Workout with Light Weights", 2, 1)
        legDayWorkoutPlan = WorkoutPlan("Leg Day Workout", "Leg Workout With kettle bells", 2, 2)
        strengthWorkoutPlan = WorkoutPlan("Strength Workout", "Workout Aimed to Build Strength", 3, 3)
        cardioWorkoutPlan = WorkoutPlan("Cardio Workout", "A cardio Based Workout", 3, 4)
        muscleGrowthWorkoutPlan = WorkoutPlan("Muscle Growth Workout", "A workout Based around Building muscle ", 2, 5)


        populatedWorkoutPlans!!.create(fullBodyWorkoutPlan!!)
        populatedWorkoutPlans!!.create(legDayWorkoutPlan!!)
        populatedWorkoutPlans!!.create(strengthWorkoutPlan!!)
        populatedWorkoutPlans!!.create(cardioWorkoutPlan!!)
        populatedWorkoutPlans!!.create(muscleGrowthWorkoutPlan!!)
    }

    @AfterEach
    fun tearDown() {
        fullBodyWorkoutPlan = null
        legDayWorkoutPlan = null
        strengthWorkoutPlan = null
        cardioWorkoutPlan = null
        muscleGrowthWorkoutPlan = null
        populatedWorkoutPlans = null
        emptyWorkoutPlans = null
    }
    @Test
    fun `adding a Workout Plan to a populated list adds to ArrayList`() {
        val newWorkoutPlan = WorkoutPlan("Low Equipment Workout", "Workout using minimal amount of equipment", 2, 6)
        assertTrue(populatedWorkoutPlans!!.create(newWorkoutPlan))
    }

    @Test
    fun `adding a Workout Plan to an empty list adds to ArrayList`() {
        val newWorkoutPlan = WorkoutPlan("Low Equipment Workout", "Workout using minimal amount of equipment", 2, 6)
        assertTrue(emptyWorkoutPlans!!.create(newWorkoutPlan))
    }

}
