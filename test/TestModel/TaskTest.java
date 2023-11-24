package TestModel;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

import model.*;

class TaskTest {

    @Test
    void testConstructorWithValues() {
        String testFileName = "testTask";
        String description = "Test Task Description";
        String deadline = "01/01/2023";

        Task task = new Task(testFileName, description, deadline);

        assertEquals(testFileName, task.getFN());
        assertEquals(description, task.getDescription());
        assertEquals(deadline, new SimpleDateFormat("dd/MM/yyyy").format(task.getDeadline()));
    }

    @Test
    void testConstructorWithoutValues() {
        String testFileName = "testTask";

        Task task = new Task(testFileName);

        assertEquals(testFileName, task.getFN());
        assertNull(task.getDescription()); // Assuming getDescription() returns null for an uninitialized task
        assertNull(task.getDeadline()); // Assuming getDeadline() returns null for an uninitialized task
    }

    @Test
    void testSetAndGetDescription() {
        Task task = new Task("testTask");
        task.setDescription("Updated Description");
        assertEquals("Updated Description", task.getDescription());
    }

    @Test
    void testSetAndGetDeadline() {
        Task task = new Task("testTask");
        task.setDeadline("01/01/2023");
        assertEquals("01/01/2023", new SimpleDateFormat("dd/MM/yyyy").format(task.getDeadline()));
    }

    @Test
    void testToString() {
        String testFileName = "testTaskToString";
        String description = "Test Task Description";
        String deadline = "01/01/2023";
        Task task = new Task(testFileName, description, deadline);

        String expected = "Title: testTaskToString\nDescription: Test Task Description\nDeadline: 01/01/2023";
        assertEquals(expected, task.toString());
    }

    @Test
    void testIsDescription() {
        String testFileName = "testIsDescription";
        String description = "Test IsDescription Task Description";
        Task task = new Task(testFileName, description, "01/01/2023");

        assertTrue(task.isDescription("Task"));
        assertFalse(task.isDescription("Nonexistent"));
    }

    @Test
    void testIsDeadline() {
        String testFileName = "testIsDeadline";
        String description = "Test IsDeadline Task Description";
        String deadline = "01/01/2023";
        Task task = new Task(testFileName, description, deadline);

        // Test is before
        assertTrue(task.isDeadline('<', "02/01/2023"));
        assertFalse(task.isDeadline('<', "31/12/2022"));
        assertFalse(task.isDeadline('<', "01/01/2023"));

        // Test is equals
        assertTrue(task.isDeadline('=', deadline));
        assertFalse(task.isDeadline('=', "02/01/2023"));

        // Test is after
        assertTrue(task.isDeadline('>', "01/10/2022"));
        assertFalse(task.isDeadline('>', "02/01/2023"));
        assertFalse(task.isDeadline('>', "01/01/2023"));
    }
}