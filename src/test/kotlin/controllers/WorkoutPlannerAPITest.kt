package controllers

import models.WorkoutPlan
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertFalse
import kotlin.test.assertEquals
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

    @Nested
    inner class ListWorkoutPlans {

        @Test
        fun `listAllWorkoutPlans returns No WorkoutPlans Stored message when ArrayList is empty`() {
            Assertions.assertEquals(0, emptyWorkoutPlans!!.numberOfWorkoutPlans())
            assertTrue(emptyWorkoutPlans!!.listAllWorkoutPlans().lowercase().contains("no workout plans"))
        }

        @Test
        fun `listAllWorkoutPlans returns WorkoutPlans when ArrayList has Workout Plans Stored stored`() {
            Assertions.assertEquals(5, populatedWorkoutPlans!!.numberOfWorkoutPlans())
            val workoutPlansString = populatedWorkoutPlans!!.listAllWorkoutPlans().lowercase()
            assertTrue(workoutPlansString.contains("full body"))
            assertTrue(workoutPlansString.contains("leg day"))
            assertTrue(workoutPlansString.contains("strength"))
            assertTrue(workoutPlansString.contains("cardio"))
            assertTrue(workoutPlansString.contains("muscle growth"))
        }
    }

    @Nested
    inner class DeleteWorkoutPlans {
        @Test
        fun `deleting a Workout Plan that does not exist, returns null`() {
            Assertions.assertNull(emptyWorkoutPlans!!.deleteWorkoutPlan(0))
            Assertions.assertNull(populatedWorkoutPlans!!.deleteWorkoutPlan(-1))
            Assertions.assertNull(populatedWorkoutPlans!!.deleteWorkoutPlan(5))
        }

        @Test
        fun `deleting a Workout Plan that exists delete and returns object`() {
            Assertions.assertEquals(5, populatedWorkoutPlans!!.numberOfWorkoutPlans())
            assertEquals(muscleGrowthWorkoutPlan, populatedWorkoutPlans!!.deleteWorkoutPlan(4))
            Assertions.assertEquals(4, populatedWorkoutPlans!!.numberOfWorkoutPlans())
            assertEquals(fullBodyWorkoutPlan, populatedWorkoutPlans!!.deleteWorkoutPlan(0))
            Assertions.assertEquals(3, populatedWorkoutPlans!!.numberOfWorkoutPlans())
        }
    }

    @Nested
    inner class UpdateWorkoutPlan {
        @Test
        fun `updating a note that does not exist returns false`() {
            assertFalse(
                populatedWorkoutPlans!!.update(
                    6,
                    WorkoutPlan("Updating Workout Plan", "Updating Workout Plan", 1, 1)
                )
            )
            assertFalse(
                populatedWorkoutPlans!!.update(
                    -1,
                    WorkoutPlan("Updating Workout Plan", "Updating Workout Plan", 1, 1)
                )
            )
            assertFalse(
                emptyWorkoutPlans!!.update(
                    0,
                    WorkoutPlan("Updating Workout Plan", "Updating Workout Plan", 1, 1)
                )
            )
        }

        @Test
        fun `updating a note that exists returns true and updates`() {

            assertEquals(muscleGrowthWorkoutPlan, populatedWorkoutPlans!!.findWorkoutPlan(4))
            Assertions.assertEquals("Muscle Growth Workout", populatedWorkoutPlans!!.findWorkoutPlan(4)!!.workoutTitle)
            Assertions.assertEquals(
                "A workout Based around Building muscle ",
                populatedWorkoutPlans!!.findWorkoutPlan(4)!!.workoutDescription
            )
            Assertions.assertEquals(2, populatedWorkoutPlans!!.findWorkoutPlan(4)!!.workoutDuration)

            //update note 5 with new information and ensure contents updated successfully
            assertTrue(
                populatedWorkoutPlans!!.update(
                    4,
                    WorkoutPlan("Muscle Growth Workout", "A workout Based around Building muscle ", 2, 5)
                )
            )
            Assertions.assertEquals("Muscle Growth Workout", populatedWorkoutPlans!!.findWorkoutPlan(4)!!.workoutTitle)
            Assertions.assertEquals(
                "A workout Based around Building muscle ",
                populatedWorkoutPlans!!.findWorkoutPlan(4)!!.workoutDescription
            )
            Assertions.assertEquals(2, populatedWorkoutPlans!!.findWorkoutPlan(4)!!.workoutDuration)
        }
    }

    @Nested
    inner class CountingMethods {

        @Test
        fun numberOfWorkoutPlansCalculatedCorrectly() {
            Assertions.assertEquals(5, populatedWorkoutPlans!!.numberOfWorkoutPlans())
            Assertions.assertEquals(0, emptyWorkoutPlans!!.numberOfWorkoutPlans())
        }
    }

    @Nested
    inner class SearchMethods {

        @Test
        fun `search Workout Plans by title returns no Workout Plans when no Workout Plans with that title exist`() {

            Assertions.assertEquals(5, populatedWorkoutPlans!!.numberOfWorkoutPlans())
            val searchResults = populatedWorkoutPlans!!.searchByTitle("no results expected")
            assertTrue(searchResults.isEmpty())

            Assertions.assertEquals(0, emptyWorkoutPlans!!.numberOfWorkoutPlans())
            assertTrue(emptyWorkoutPlans!!.searchByTitle("").isEmpty())
        }

        @Test
        fun `search Workout Plans by title returns Workout Plans when Workout Plans with that title exist`() {
            Assertions.assertEquals(5, populatedWorkoutPlans!!.numberOfWorkoutPlans())


            var searchResults = populatedWorkoutPlans!!.searchByTitle("Strength Workout")
            assertTrue(searchResults.contains("Strength Workout"))
            assertFalse(searchResults.contains("Cardio Workout"))


            searchResults = populatedWorkoutPlans!!.searchByTitle("Strength")
            assertTrue(searchResults.contains("Strength"))
            assertTrue(searchResults.contains("Strength Workout"))
            assertFalse(searchResults.contains("Muscle Growth Workout"))


            searchResults = populatedWorkoutPlans!!.searchByTitle("wOrKout")
            assertTrue(searchResults.contains("Strength Workout"))
            assertTrue(searchResults.contains("Cardio Workout"))
            assertFalse(searchResults.contains("Muscle Strength"))
        }
    }
    @Test
    fun `search Workout Plans by Description returns no Workout Plans when no Workout Plans with that Description exist`() {

        Assertions.assertEquals(5, populatedWorkoutPlans!!.numberOfWorkoutPlans())
        val searchResults = populatedWorkoutPlans!!.searchByDescription("no results expected")
        assertTrue(searchResults.isEmpty())

        Assertions.assertEquals(0, emptyWorkoutPlans!!.numberOfWorkoutPlans())
        assertTrue(emptyWorkoutPlans!!.searchByDescription("").isEmpty())
    }

    @Test
    fun `search Workout Plans by Description returns Workout Plans when Workout Plans with that Description exist`() {
        Assertions.assertEquals(5, populatedWorkoutPlans!!.numberOfWorkoutPlans())


        var searchResults = populatedWorkoutPlans!!.searchByDescription("Workout Aimed to Build Strength")
        assertTrue(searchResults.contains("Workout Aimed to Build Strength"))
        assertFalse(searchResults.contains("Cardio Workout"))


        searchResults = populatedWorkoutPlans!!.searchByDescription("Build Strength")
        assertTrue(searchResults.contains("Build Strength"))
        assertTrue(searchResults.contains("Workout Aimed to Build Strength"))
        assertFalse(searchResults.contains("Muscle Growth Workout"))


        searchResults = populatedWorkoutPlans!!.searchByDescription("bUilD")
        assertTrue(searchResults.contains("Build Strength"))
        assertTrue(searchResults.contains("Workout Aimed to Build Strength"))
        assertFalse(searchResults.contains("Muscle build "))
    }
}
