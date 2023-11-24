import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
    void testSetAndGetContent() {
        Note note = new Note("testNote", "test content");
        note.setContent("Updated content");
        assertEquals("Updated content", note.getContent());
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