package TestModel;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

import model.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CreateServiceTest {

    static private Scanner input;
    static private ArrayList<Note> ArrForNote;
    static private ArrayList<Task> ArrForTask;
    static private ArrayList<Event> ArrForEvent;
    static private ArrayList<Contact> ArrForContact;

    @BeforeAll
    static void startUp() throws IOException {
        // Initialize the Scanner with some input. You'll need to replace this with appropriate input for your tests.
        List<String> inputSetLines = Files.readAllLines(Paths.get("test_case/CreateServiceTest.txt"));
        String inputSet = String.join("\n", inputSetLines);
        input = new Scanner(inputSet);
    }

    @BeforeEach
    void setUp(){
        ArrForNote = new ArrayList<>();
        ArrForTask = new ArrayList<>();
        ArrForEvent = new ArrayList<>();
        ArrForContact = new ArrayList<>();
    }

    @Order(1)
    @Test
    void importFile() {
        CreateService.importFile(input, ArrForNote, ArrForTask, ArrForEvent, ArrForContact);
        assertFalse(ArrForNote.isEmpty());
        assertFalse(ArrForTask.isEmpty());
        assertFalse(ArrForEvent.isEmpty());
        assertFalse(ArrForContact.isEmpty());
    }

    @Order(2)
    @Test
    void createNote() {
        CreateService.createNote(input, ArrForNote);
        CreateService.createNote(input, ArrForNote);
        // Add assertions here to verify the behavior of createNote.
        assertFalse(ArrForNote.isEmpty(), "The note list should not be empty after creation");
    }

    @Order(3)
    @Test
    void createTask() {
        CreateService.createTask(input, ArrForTask);
        CreateService.createTask(input, ArrForTask);
        // Add assertions here to verify the behavior of createTask.
        assertFalse(ArrForTask.isEmpty(), "The task list should not be empty after creation");
    }

    @Order(4)
    @Test
    void createEvent() {
        CreateService.createEvent(input, ArrForEvent);
        CreateService.createEvent(input, ArrForEvent);
        // Add assertions here to verify the behavior of createEvent.
        assertFalse(ArrForEvent.isEmpty(), "The event list should not be empty after creation");
    }

    @Order(5)
    @Test
    void createContact() {
        CreateService.createContact(input, ArrForContact);
        CreateService.createContact(input, ArrForContact);
        // Add assertions here to verify the behavior of createContact.
        assertFalse(ArrForContact.isEmpty(), "The contact list should not be empty after creation");
    }
}