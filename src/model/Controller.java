package model;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class

public class Controller {

    public static void makeDir() {
        final String directoryName = "repository";
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
        }
    }

    // for tests
    static void reset() {
        // Delete the generated file if it exists
        Controller.ArrForNote.clear();
        Controller.ArrForTask.clear();
        Controller.ArrForEvent.clear();
        Controller.ArrForContact.clear();
    }


    static ArrayList<Note> ArrForNote = new ArrayList<Note>();
    static ArrayList<Task> ArrForTask = new ArrayList<Task>();
    static ArrayList<Event> ArrForEvent = new ArrayList<Event>();
    static ArrayList<Contact> ArrForContact = new ArrayList<Contact>();


    public static void mainInterface(Scanner input) {

        String option = "";

        boolean endFlag = false;
        while (!endFlag) {
            System.out.println("Welcome to PIM. Please choose your operation:");
            System.out.println("1: Create new PIR");
            System.out.println("2: Search PIR");
            System.out.println("3: Print all PIR");
            System.out.println("4: Exit");

            option = input.nextLine();
            switch (option) {
                case ("1"):
                    Controller.createInterface(input);
                    break;
                case ("2"):
                    Controller.searchInterface(input);
                    break;
                case ("3"):
                    Controller.printAllInterface(input);
                    break;
                case ("4"):
                    System.out.println("Goodbye!");
                    endFlag = true;
                    break;
                default:
                    System.out.println("Wrong value inputted, please type again!");
                    break;
            }
        }
        reset();
        return;
    }

    public static void createInterface(Scanner input) {
        String option = "";

        System.out.println("Please choose the input format of PIR");
        System.out.println("1: Input pim files");
        System.out.println("2: Create a new PIR");
        boolean wrongInput = true;
        while (wrongInput) {
            option = input.nextLine();
            switch (option) {
                case ("1"):
                    CreateService.importFile(input, ArrForNote, ArrForTask, ArrForEvent, ArrForContact);
                    wrongInput = false;
                    break;
                case ("2"):
                    System.out.println("Please choose the type of PIR");
                    System.out.println("1: Notes");
                    System.out.println("2: Tasks");
                    System.out.println("3: Events");
                    System.out.println("4: Contacts");
                    boolean wrongInputInside = true;
                    while (wrongInputInside) {
                        String optionInside = input.nextLine();
                        switch (optionInside) {
                            case ("1"):
                                CreateService.createNote(input, ArrForNote);
                                wrongInputInside = false;
                                break;
                            case ("2"):
                                CreateService.createTask(input, ArrForTask);
                                wrongInputInside = false;
                                break;
                            case ("3"):
                                CreateService.createEvent(input, ArrForEvent);
                                wrongInputInside = false;
                                break;
                            case ("4"):
                                CreateService.createContact(input, ArrForContact);
                                wrongInputInside = false;
                                break;
                            default:
                                System.out.println("Wrong value inputted, please type again!");
                                wrongInputInside = true;
                                break;
                        }
                    }
                    wrongInput = false;
                    break;
                default:
                    System.out.println("Wrong value inputted, please type again!");
                    wrongInput = true;
            }
        }
        System.out.println("Successfully added PIR");
        return;
    }

    public static void searchInterface(Scanner input) {
        System.out.println("Please choose the type of PIR:");
        System.out.println("1: Note");
        System.out.println("2: Task");
        System.out.println("3: Event");
        System.out.println("4: Contact");
        boolean wrongInput = true;
        while (wrongInput) {
            String option = input.nextLine();
            switch (option) {
                case ("1"):
                    ArrayList<Note> SelectedArrForNote = SearchService.searchNote(input, ArrForNote);
                    ModifyService.processNoteSearchResults(SelectedArrForNote, ArrForNote, input);
                    wrongInput = false;
                    break;
                case ("2"):
                    ArrayList<Task> SelectedArrForTask = SearchService.searchTask(input, ArrForTask);
                    ModifyService.processTaskSearchResults(SelectedArrForTask, ArrForTask, input);
                    wrongInput = false;
                    break;
                case ("3"):
                    ArrayList<Event> SelectedArrForEvent = SearchService.searchEvent(input, ArrForEvent);
                    ModifyService.processEventSearchResults(SelectedArrForEvent, ArrForEvent, input);
                    wrongInput = false;
                    break;
                case ("4"):
                    ArrayList<Contact> SelectedArrForContact = SearchService.searchContact(input, ArrForContact);
                    ModifyService.processContactSearchResults(SelectedArrForContact, ArrForContact, input);
                    wrongInput = false;
                    break;
                default :
                    System.out.println("Wrong value inputted, please type again!");
                    wrongInput = true;
                    break;
            }
        }
        return;
    }

    public static void printAllInterface(Scanner input) {
        System.out.println("Please input the file name for outputted pim file");
        String filename = input.nextLine();
        final String path = "repository\\" + filename + ".pim";

        try {
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

                FileWriter myWriter = new FileWriter(path);
                for (int i = 0;i < ArrForNote.size(); i++) {
                    myWriter.write("N\n");
                    myWriter.write(ArrForNote.get(i).getFN() + "\n");
                    myWriter.write(ArrForNote.get(i).getContent() + "\n");
                }
                for (int i = 0;i < ArrForTask.size(); i++) {
                    myWriter.write("T\n");
                    myWriter.write(ArrForTask.get(i).getFN() + "\n");
                    myWriter.write(ArrForTask.get(i).getDescription() + "\n");
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    myWriter.write(df.format(ArrForTask.get(i).getDeadline()) + "\n");
                }
                for (int i = 0;i < ArrForEvent.size(); i++) {
                    myWriter.write("E\n");
                    myWriter.write(ArrForEvent.get(i).getFN() + "\n");
                    myWriter.write(ArrForEvent.get(i).getDescription() + "\n");
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    myWriter.write(df.format((ArrForEvent.get(i).getStart())) + "\n");
                    myWriter.write(df.format((ArrForEvent.get(i).getAlarm())) + "\n");
                }
                for (int i = 0;i < ArrForContact.size(); i++) {
                    myWriter.write("C\n");
                    myWriter.write(ArrForContact.get(i).getName()+"\n");
                    myWriter.write(ArrForContact.get(i).getAddress()+"\n");
                    myWriter.write(ArrForContact.get(i).getPhone()+"\n");
                }
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("File already exists.");
        }
        return;
    }
}
