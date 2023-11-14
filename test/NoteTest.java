import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

class NoteTest {

    @Test
    void testConstructorWithContent() {
        Note note = new Note("testNote", "This is a test note.");
        assertEquals("testNote", note.getFN());
        assertEquals("This is a test note.", note.getContent());
    }

    @Test
    void testConstructorWithoutContent() {
        Note note = new Note("testNote");
        assertEquals("testNote", note.getFN());
        assertEquals(null, note.getContent()); // Assuming getContent() returns null for an uninitialized note
    }

    @Test
    void testGetPirType() {
        // Create a temporary file for testing
        String testFileName = "testReadWriteFile";
        String originalContent = "Test Read Write File Content";
        Note note = new Note(testFileName, originalContent);
        note.writeFile();

        assertEquals("N", Note.getPirType("testReadWriteFile"));

        // Clean up: Delete the temporary file
        java.io.File file = new java.io.File("repository\\" + testFileName + ".pim");
        if (!file.delete()) {
            fail("Failed to delete the test file.");
        }
    }

    @Test
    void testSetAndGetContent() {
        Note note = new Note("testNote", "test content");
        note.setContent("Updated content");
        assertEquals("Updated content", note.getContent());
    }

    @Test
    void testReadWriteFile() {
        // Create a temporary file for testing
        String testFileName = "testReadWriteFile";
        String originalContent = "Test Read Write File Content";
        Note noteWrite = new Note(testFileName, originalContent);

        // Perform the write operation
        noteWrite.writeFile();

        // Read the file to check if content is written correctly
        Note noteRead = new Note(testFileName);
        noteRead.readFile(testFileName);

        // Verify the content
        assertEquals(originalContent, noteRead.getContent());

        // Clean up: Delete the temporary file
        java.io.File file = new java.io.File("repository\\" + testFileName + ".pim");
        if (!file.delete()) {
            fail("Failed to delete the test file.");
        }
    }

    @Test
    void testToString() {
        Note note = new Note("testNote", "Test ToString Content");
        String expected = "Title: testNote\nContent: Test ToString Content";
        assertEquals(expected, note.toString());
    }

    @Test
    void testIsContent() {
        Note note = new Note("testNote", "Test ToString Content");
        assertTrue(note.isContent("Test"));
        assertTrue(!note.isContent("Nope"));
    }
}