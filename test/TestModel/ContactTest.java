package TestModel;
import model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContactTest {

    @Test
    void testSetName() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        contact.setName("Jane Smith");
        assertEquals("Jane Smith", contact.getName());
    }

    @Test
    void testSetAddress() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        contact.setAddress("456 Elm St");
        assertEquals("456 Elm St", contact.getAddress());
    }

    @Test
    void testSetPhone() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        contact.setPhone("555-5678");
        assertEquals("555-5678", contact.getPhone());
    }

    @Test
    void testGetName() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        assertEquals("John Doe", contact.getName());
    }

    @Test
    void testGetAddress() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        assertEquals("123 Main St", contact.getAddress());
    }

    @Test
    void testGetPhone() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        assertEquals("555-1234", contact.getPhone());
    }

    @Test
    void testToString() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        String expected = "Name: John Doe\n" +
                "Address: 123 Main St\n" +
                "Phone number: 555-1234";
        assertEquals(expected, contact.toString());
    }

    @Test
    void testIsName() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        assertTrue(contact.isName("John"));
        assertFalse(contact.isName("Jane"));
    }

    @Test
    void testIsAddress() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        assertTrue(contact.isAddress("Main"));
        assertFalse(contact.isAddress("Elm"));
    }

    @Test
    void testIsPhone() {
        Contact contact = new Contact("John Doe", "123 Main St", "555-1234");
        assertTrue(contact.isPhone("555"));
        assertFalse(contact.isPhone("567"));
    }
}