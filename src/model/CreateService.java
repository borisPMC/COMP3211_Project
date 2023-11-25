package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CreateService {

    public static void importFile(Scanner input, ArrayList<Note> ArrForNote, ArrayList<Task> ArrForTask, ArrayList<Event> ArrForEvent, ArrayList<Contact> ArrForContact) {
        System.out.println("Please input the name of the pim file");
        String fileName = input.nextLine();
        String path = "repository\\" + fileName + ".pim";
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String classifier = myReader.nextLine();
                if (classifier.equals("N")) {
                    Note note = new Note("", "");
                    note.setFN(myReader.nextLine());
                    note.setContent(myReader.nextLine());
                    ArrForNote.add(note);
                } else if (classifier.equals("T")) {
                    Task task = new Task("", "", "");
                    task.setFN(myReader.nextLine());
                    task.setDescription(myReader.nextLine());
                    task.setDeadline(myReader.nextLine());
                    ArrForTask.add(task);
                } else if (classifier.equals("E")) {
                    Event event = new Event("", "", "", "");
                    event.setFN(myReader.nextLine());
                    event.setDescription(myReader.nextLine());
                    event.setStart(myReader.nextLine());
                    event.setAlarm(myReader.nextLine());
                    ArrForEvent.add(event);
                } else if (classifier.equals("C")) {
                    Contact contact = new Contact("", "", "");
                    String name = myReader.nextLine();
                    contact.setFN(name);
                    contact.setName(name);
                    contact.setAddress(myReader.nextLine());
                    contact.setPhone(myReader.nextLine());
                    ArrForContact.add(contact);
                }
            }
            System.out.println("Successfully added PIR");
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
        }
    }

    public static void createNote(Scanner input, ArrayList<Note> ArrForNote) {
        String fileNameForNote = "";
        System.out.println("Please input the title of note");
        boolean sameTitleNote = true;
        while (sameTitleNote) {
            fileNameForNote = input.nextLine();
            if (Note.checkSameTitle(fileNameForNote, ArrForNote)) {
                System.out.println("Same title already created, please type again!");
                sameTitleNote = true;
            } else {
                sameTitleNote = false;
            }
        }
        System.out.println("Please input the texts to the note");
        String contentForContent = input.nextLine();
        Note note = new Note(fileNameForNote, contentForContent);
        ArrForNote.add(note);
        System.out.println("Successfully added PIR");
    }

    public static void createTask(Scanner input, ArrayList<Task> ArrForTask) {
        String fileNameForTask = "";
        String deadlineForTask = "";
        System.out.println("Please input the title of task");
        boolean sameTitleTask = true;
        while (sameTitleTask) {
            fileNameForTask = input.nextLine();
            if (Task.checkSameTitle(fileNameForTask, ArrForTask)) {
                System.out.println("Same title already created, please type again!");
                sameTitleTask = true;
            } else {
                sameTitleTask = false;
            }
        }
        System.out.println("Please input the descriptions of task");
        String descriptionsForTask = input.nextLine();
        System.out.println("Please input the deadline of tasks in format dd/MM/yyyy");
        boolean wrongDateTask = true;
        while (wrongDateTask) {
            deadlineForTask = input.nextLine();
            if (Task.checkDate(deadlineForTask)) {
                System.out.println("Wrong format for deadline, please type again!");
                wrongDateTask = true;
            } else {
                wrongDateTask = false;
            }
        }
        Task task = new Task(fileNameForTask, descriptionsForTask, deadlineForTask);
        ArrForTask.add(task);
        System.out.println("Successfully added PIR");
    }

    public static void createEvent(Scanner input, ArrayList<Event> ArrForEvent) {
        String fileNameForEvent = "";
        String startingTimeForEvent = "";
        String alarmForEvent = "";
        System.out.println("Please input the title of event");
        boolean sameTitleEvent = true;
        while (sameTitleEvent) {
            fileNameForEvent = input.nextLine();
            if (Event.checkSameTitle(fileNameForEvent, ArrForEvent)) {
                System.out.println("Same title already created, please type again!");
                sameTitleEvent = true;
            } else {
                sameTitleEvent = false;
            }
        }
        System.out.println("Please input the descriptions of event");
        String descriptionsForEvent = input.nextLine();
        System.out.println("Please input the starting times of event in format dd/MM/yyyy");
        boolean wrongStartingTime = true;
        while (wrongStartingTime) {
            startingTimeForEvent = input.nextLine();
            if (Event.checkDate(startingTimeForEvent)) {
                System.out.println("Wrong format for starting times, please type again!");
                wrongStartingTime = true;
            } else {
                wrongStartingTime= false;
            }
        }
        System.out.println("Please input the alarm of event in format dd/MM/yyyy");
        boolean wrongAlarmTime = true;
        while (wrongAlarmTime) {
            alarmForEvent = input.nextLine();
            if (Event.checkDate(alarmForEvent)) {
                System.out.println("Wrong format for alarm, please type again!");
                wrongAlarmTime = true;
            } else {
                wrongAlarmTime= false;
            }
        }
        Event event = new Event(fileNameForEvent, descriptionsForEvent, startingTimeForEvent, alarmForEvent);
        ArrForEvent.add(event);
        System.out.println("Successfully added PIR");
    }

    public static void createContact(Scanner input, ArrayList<Contact> ArrForContact) {
        String nameForContact = "";
        System.out.println("Please input the name of contact");
        boolean sameTitleContact = true;
        while (sameTitleContact) {
            nameForContact = input.nextLine();
            if (Contact.checkSameTitle(nameForContact, ArrForContact)) {
                System.out.println("Same contact person already created, please type again!");
                sameTitleContact = true;
            } else {
                sameTitleContact= false;
            }
        }
        System.out.println("Please input the address of contact");
        String addressForContact = input.nextLine();
        System.out.println("Please input the mobile number of contact");
        String mobileNumberForContact = input.nextLine();
        Contact contact = new Contact(nameForContact, addressForContact, mobileNumberForContact);
        ArrForContact.add(contact);
        System.out.println("Successfully added PIR");
    }
}