import Proceso.Model.Activity;
import Proceso.Model.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    private Activity activity;

    @BeforeEach
    void setUp() {
        activity = new Activity("Sample Activity", "This is a sample description", true);
    }

    @Test
    void testCreateTask() {
        activity.createTask("Task 1", "Description of Task 1", true, 5);
        Task task = activity.searchTaskByName("Task 1");
        assertNotNull(task, "The task should be created and found in the activity.");
        assertEquals("Task 1", task.getname(), "The task name should match.");
        assertEquals(5, task.gettime(), "The task time should match.");
    }

    @Test
    void testCalculateTotalTime() {
        activity.createTask("Task 1", "Description of Task 1", false, 5);
        activity.createTask("Task 2", "Description of Task 2", false, 10);
        int totalTime = activity.calculateTotalTime();
        assertEquals(15, totalTime, "The total time should be the sum of non-obligatory tasks.");
    }

    @Test
    void testCalculateMinTime() {
        activity.createTask("Task 1", "Description of Task 1", true, 5);
        activity.createTask("Task 2", "Description of Task 2", false, 10);
        int minTime = activity.calculateMinTime();
        assertEquals(10, minTime, "The minimum time should be the sum of non-obligatory tasks.");
    }

    @Test
    void testDeleteTask() {
        activity.createTask("Task 1", "Description of Task 1", true, 5);
        Task task = activity.searchTaskByName("Task 1");
        assertNotNull(task, "Task should exist before deletion.");

        activity.deleteTask(task);
        Task deletedTask = activity.searchTaskByName("Task 1");
        assertNull(deletedTask, "The task should no longer exist after deletion.");
    }

    @Test
    void testSearchTaskByName() {
        activity.createTask("Task 1", "Description of Task 1", true, 5);
        Task task = activity.searchTaskByName("Task 1");
        assertNotNull(task, "The task should be found by its name.");
    }
}
