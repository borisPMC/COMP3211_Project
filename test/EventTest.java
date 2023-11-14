import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class EventTest {

    @Test
    void testConstructorWithValues() {
        String testFileName = "testEvent";
        String description = "Test Event Description";
        String start = "01/01/2023";
        String alarm = "31/12/2022";

        Event event = new Event(testFileName, description, start, alarm);

        assertEquals(testFileName, event.getFN());
        assertEquals(description, event.getDescription());
        assertEquals(start, new SimpleDateFormat("dd/MM/yyyy").format(event.getStart()));
        assertEquals(alarm, new SimpleDateFormat("dd/MM/yyyy").format(event.getAlarm()));
    }

    @Test
    void testConstructorWithoutValues() {
        String testFileName = "testEvent";

        Event event = new Event(testFileName);

        assertEquals(testFileName, event.getFN());
        assertEquals(null, event.getDescription()); // Assuming getDescription() returns null for an uninitialized event
        assertEquals(null, event.getStart()); // Assuming getStart() returns null for an uninitialized event
        assertEquals(null, event.getAlarm()); // Assuming getAlarm() returns null for an uninitialized event
    }

    @Test
    void testSetAndGetDescription() {
        Event event = new Event("testEvent");
        event.setDescription("Updated Event Description");
        assertEquals("Updated Event Description", event.getDescription());
    }

    @Test
    void testSetAndGetStart() {
        Event event = new Event("testEvent");
        event.setStart("01/01/2023");
        assertEquals("01/01/2023", new SimpleDateFormat("dd/MM/yyyy").format(event.getStart()));
    }

    @Test
    void testSetAndGetAlarm() {
        Event event = new Event("testEvent");
        event.setAlarm("01/01/2023");
        assertEquals("01/01/2023", new SimpleDateFormat("dd/MM/yyyy").format(event.getAlarm()));
    }

    @Test
    void testReadAndWriteFile() {
        // Create a temporary file for testing
        String testFileName = "testEventReadWrite";
        String originalDescription = "Test Read Write Event Description";
        String originalStart = "01/01/2023";
        String originalAlarm = "31/12/2022";
        Event eventWrite = new Event(testFileName, originalDescription, originalStart, originalAlarm);

        // Perform the write operation
        eventWrite.writeFile();

        // Read the file to check if content is written correctly
        Event eventRead = new Event(testFileName);
        eventRead.readFile(testFileName);

        // Verify the content
        assertEquals(originalDescription, eventRead.getDescription());
        assertEquals(originalStart, new SimpleDateFormat("dd/MM/yyyy").format(eventRead.getStart()));
        assertEquals(originalAlarm, new SimpleDateFormat("dd/MM/yyyy").format(eventRead.getAlarm()));

        // Clean up: Delete the temporary file
        File file = new File("repository\\" + testFileName + ".pim");
        if (!file.delete()) {
            fail("Failed to delete the test file.");
        }
    }

    @Test
    void testToString() {
        String testFileName = "testEventToString";
        String description = "Test Event Description";
        String start = "01/01/2023";
        String alarm = "20/12/2022";
        Event event = new Event(testFileName, description, start, alarm);

        String expected = "Title: testEventToString\nDescription: Test Event Description\nStart Time: 01/01/2023\nAlarm Time: 20/12/2022";
        assertEquals(expected, event.toString());
    }

    @Test
    void testIsDescription() {
        String testFileName = "testIsDescriptionEvent";
        String description = "Test IsDescription Event Description";
        Event event = new Event(testFileName, description, "01/01/2023", "31/12/2022");

        assertTrue(event.isDescription("Event"));
        assertFalse(event.isDescription("Nonexistent"));
    }

    @Test
    void testIsStart() {
        String testFileName = "testIsStartEvent";
        String description = "Test IsStart Event Description";
        String start = "01/01/2023";
        Event event = new Event(testFileName, description, start, "31/12/2022");

        // Test is before
        assertTrue(event.isStart('<', "02/01/2023"));
        assertFalse(event.isStart('<', "01/01/2023"));

        // Test is equals
        assertTrue(event.isStart('=', start));
        assertFalse(event.isStart('=', "02/01/2023"));

        // Test is after
        assertTrue(event.isStart('>', "31/12/2022"));
        assertFalse(event.isStart('>', "02/01/2023"));
    }

    @Test
    void testIsAlarm() {
        String testFileName = "testIsAlarmEvent";
        String description = "Test IsAlarm Event Description";
        String start = "01/01/2023";
        String alarm = "31/12/2022";
        Event event = new Event(testFileName, description, start, alarm);

        // Test is before
        assertTrue(event.isAlarm('<', "02/01/2023"));
        assertFalse(event.isAlarm('<', "11/11/2022"));

        // Test is equals
        assertTrue(event.isAlarm('=', alarm));
        assertFalse(event.isAlarm('=', "02/01/2023"));

        // Test is after
        assertTrue(event.isAlarm('>', "30/12/2022"));
        assertFalse(event.isAlarm('>', "02/01/2023"));
    }
}