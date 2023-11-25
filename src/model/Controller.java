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

    private static boolean requestCriteria(Scanner input) {

        Boolean moreCondition = false;

        System.out.println("Do you need more condition?");
        System.out.println("1. Yes");
        System.out.println("2. No");
        boolean wrongInputInside2 = true;
        while (wrongInputInside2) {
            String option3 = input.nextLine();
            switch (option3) {
                case ("1"):
                    moreCondition = true;
                    wrongInputInside2 = false;
                    break;
                case ("2"):
                    moreCondition = false;
                    wrongInputInside2 = false;
                    break;
                default :
                    System.out.println("Wrong value inputted, please type again!");
                    wrongInputInside2 = true;
            }
        }
        return moreCondition;
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
                        myReader.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File is not found");
                    }
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
                                wrongInputInside = false;
                                break;
                            case ("2"):
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
                                wrongInputInside = false;
                                break;
                            case ("3"):
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
                                wrongInputInside = false;
                                break;
                            case ("4"):
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
        String content = "";
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
                    int result = 0;
                    if (SelectedArrForNote.size() != 0) {
                        int index = 1;
                        System.out.println("Please choose from specify Note");
                        for (int i = 0; i < SelectedArrForNote.size(); i++) {
                            System.out.println(index + "  " + SelectedArrForNote.get(i).toString());
                            index++;
                        }
                        Boolean wrongInputInside3 = true;
                        while (wrongInputInside3) {
                            String option2 = input.nextLine();
                            if (Integer.parseInt(option2) > SelectedArrForNote.size() || Integer.parseInt(option2) - 1 < 0) {
                                System.out.println("Wrong value inputted, please type again!");
                                wrongInputInside3 = true;
                            } else {
                                int require = Integer.parseInt(option2) - 1;
                                for (int i = 0; i < ArrForNote.size(); i++) {
                                    if (SelectedArrForNote.get(require) == ArrForNote.get(i)) {
                                        result = i;
                                        break;
                                    }
                                }
                                wrongInputInside3 = false;
                            }
                        }
                        System.out.println("Please choose the type for operation");
                        System.out.println("1. Modify");
                        System.out.println("2. Delete");
                        System.out.println("3. Cancel");
                        boolean wrongInputInside4 = true;
                        while (wrongInputInside4) {
                            String option3 = input.nextLine();
                            switch (option3) {
                                case ("1"):
                                    System.out.println("Please type the new content for note");
                                    String content2 = input.nextLine();
                                    ArrForNote.get(result).setContent(content2);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("2"):
                                    ArrForNote.remove(result);
                                    System.out.println("Successfully deleted");
                                    wrongInputInside4 = false;
                                    break;
                                case ("3"):
                                    System.out.println("Going back to main menu");
                                    wrongInputInside4 = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside4 = true;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("No notes fulfill you conditions");
                    }
                    wrongInput = false;
                    break;
                case ("2"):
                    ArrayList<Task> SelectedArrForTask = SearchService.searchTask(input, ArrForTask);
                    result = 0;
                    if (SelectedArrForTask.size() != 0) {
                        int index = 1;
                        System.out.println("Please choose from specify Task");
                        for (int i = 0; i < SelectedArrForTask.size(); i++) {
                            System.out.println(index + "  " + SelectedArrForTask.get(i).toString());
                            index++;
                        }
                        boolean wrongInputInside3 = true;
                        while (wrongInputInside3) {
                            String option2 = input.nextLine();
                            if (Integer.parseInt(option2) > SelectedArrForTask.size() || Integer.parseInt(option2) - 1 < 0) {
                                System.out.println("Wrong value inputted, please type again!");
                                wrongInputInside3 = true;
                            } else {
                                int require = Integer.parseInt(option2) - 1;
                                for (int i = 0; i < ArrForTask.size(); i++) {
                                    if (SelectedArrForTask.get(require) == ArrForTask.get(i)) {
                                        result = i;
                                        break;
                                    }
                                }
                                wrongInputInside3 = false;
                            }
                        }
                        System.out.println("Please choose the type for operation");
                        System.out.println("1. Modify Description");
                        System.out.println("2. Modify Deadline");
                        System.out.println("3. Delete");
                        System.out.println("4. Cancel");
                        boolean wrongInputInside4 = true;
                        while (wrongInputInside4) {
                            String option3 = input.nextLine();
                            switch (option3) {
                                case ("1"):
                                    System.out.println("Please type the new description for task");
                                    String content2 = input.nextLine();
                                    ArrForTask.get(result).setDescription(content2);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("2"):
                                    String date = "";
                                    System.out.println("Please type the new deadline for task in format dd/MM/yyyy");
                                    boolean wrongDateTask = true;
                                    while (wrongDateTask) {
                                        date = input.nextLine();
                                        if (Task.checkDate(date)) {
                                            System.out.println("Wrong format for deadline, please type again!");
                                            wrongDateTask = true;
                                        } else {
                                            wrongDateTask = false;
                                        }
                                    }
                                    ArrForTask.get(result).setDeadline(date);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("3"):
                                    ArrForTask.remove(result);
                                    System.out.println("Successfully deleted");
                                    wrongInputInside4 = false;
                                    break;
                                case ("4"):
                                    System.out.println("Going back to main menu");
                                    wrongInputInside4 = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside4 = true;
                            }
                        }
                    } else {
                        System.out.println("No tasks fulfill you conditions");
                    }
                    wrongInput = false;
                    break;
                case ("3"):
                    ArrayList<Event> SelectedArrForEvent = SearchService.searchEvent(input, ArrForEvent);
                    result = 0;
                    if (SelectedArrForEvent.size() != 0) {
                        int index = 1;
                        System.out.println("Please choose from specify Event");
                        for (int i = 0; i < SelectedArrForEvent.size(); i++) {
                            System.out.println(index + "  " + SelectedArrForEvent.get(i).toString());
                            index++;
                        }
                        boolean wrongInputInside3 = true;
                        while (wrongInputInside3) {
                            String option2 = input.nextLine();
                            if (Integer.parseInt(option2) > SelectedArrForEvent.size() || Integer.parseInt(option2) - 1 < 0) {
                                System.out.println("Wrong value inputted, please type again!");
                                wrongInputInside3 = true;
                            } else {
                                int require = Integer.parseInt(option2) - 1;
                                for (int i = 0; i < ArrForEvent.size(); i++) {
                                    if (SelectedArrForEvent.get(require) == ArrForEvent.get(i)) {
                                        result = i;
                                        break;
                                    }
                                }
                                wrongInputInside3 = false;
                            }
                        }
                        System.out.println("Please choose the type for operation");
                        System.out.println("1. Modify Description");
                        System.out.println("2. Modify Starting Time");
                        System.out.println("3. Modify Alarm");
                        System.out.println("4. Delete");
                        System.out.println("5. Cancel");
                        boolean wrongInputInside4 = true;
                        while (wrongInputInside4) {
                            String option3 = input.nextLine();
                            switch (option3) {
                                case ("1"):
                                    System.out.println("Please type the new description for task");
                                    String content2 = input.nextLine();
                                    ArrForEvent.get(result).setDescription(content2);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("2"):
                                    String startTime = "";
                                    System.out.println("Please type the new starting time for task in format dd/MM/yyyy");
                                    boolean wrongStartingTime = true;
                                    while (wrongStartingTime) {
                                        startTime = input.nextLine();
                                        if (Event.checkDate(startTime)) {
                                            System.out.println("Wrong format for starting times, please type again!");
                                            wrongStartingTime = true;
                                        } else {
                                            wrongStartingTime= false;
                                        }
                                    }
                                    ArrForEvent.get(result).setStart(startTime);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("3"):
                                    String alarm = "";
                                    System.out.println("Please type the new alarm for task in format dd/MM/yyyy");
                                    boolean wrongAlarmTime = true;
                                    while (wrongAlarmTime) {
                                        alarm = input.nextLine();
                                        if (Event.checkDate(alarm)) {
                                            System.out.println("Wrong format for alarm, please type again!");
                                            wrongAlarmTime = true;
                                        } else {
                                            wrongAlarmTime= false;
                                        }
                                    }
                                    ArrForEvent.get(result).setAlarm(alarm);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("4"):
                                    ArrForEvent.remove(result);
                                    System.out.println("Successfully deleted");
                                    wrongInputInside4 = false;
                                    break;
                                case ("5"):
                                    System.out.println("Going back to main menu");
                                    wrongInputInside4 = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside4 = true;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("No events fulfill you conditions");
                    }
                    wrongInput = false;
                    break;
                case ("4"):
                    ArrayList<Contact> SelectedArrForContact = SearchService.searchContact(input, ArrForContact);
                    result = 0;
                    if (SelectedArrForContact.size() != 0) {
                        int index = 1;
                        System.out.println("Please choose from specify Event");
                        for (int i = 0; i < SelectedArrForContact.size(); i++) {
                            System.out.println(index + "  " + SelectedArrForContact.get(i).toString());
                            index++;
                        }
                        boolean wrongInputInside3 = true;
                        while (wrongInputInside3) {
                            String option2 = input.nextLine();
                            if (Integer.parseInt(option2) > SelectedArrForContact.size() || Integer.parseInt(option2) - 1 < 0) {
                                System.out.println("Wrong value inputted, please type again!");
                                wrongInputInside3 = true;
                            } else {
                                int require = Integer.parseInt(option2) - 1;
                                for (int i = 0; i < ArrForContact.size(); i++) {
                                    if (SelectedArrForContact.get(require) == ArrForContact.get(i)) {
                                        result = i;
                                        break;
                                    }
                                }
                                wrongInputInside3 = false;
                            }
                        }
                        System.out.println("Please choose the type for operation");
                        System.out.println("1. Modify Name");
                        System.out.println("2. Modify Address");
                        System.out.println("3. Modify Mobile Number");
                        System.out.println("4. Delete");
                        System.out.println("5. Cancel");
                        boolean wrongInputInside4 = true;
                        while (wrongInputInside4) {
                            String option3 = input.nextLine();
                            switch (option3) {
                                case ("1"):
                                    String content2 = "";
                                    System.out.println("Please type the new name for task");
                                    boolean sameTitleContact = true;
                                    while (sameTitleContact) {
                                        content2 = input.nextLine();
                                        if (Contact.checkSameTitle(content2, ArrForContact)) {
                                            System.out.println("Same contact person already created, please type again!");
                                            sameTitleContact = true;
                                        } else {
                                            sameTitleContact= false;
                                        }
                                    }
                                    ArrForContact.get(result).setName(content2);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("2"):
                                    System.out.println("Please type the new address for task");
                                    String startTime = input.nextLine();
                                    ArrForContact.get(result).setAddress(startTime);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("3"):
                                    System.out.println("Please type the new mobile number for task");
                                    String alarm = input.nextLine();
                                    ArrForContact.get(result).setPhone(alarm);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("4"):
                                    ArrForContact.remove(result);
                                    System.out.println("Successfully deleted");
                                    wrongInputInside4 = false;
                                    break;
                                case ("5"):
                                    System.out.println("Going back to main menu");
                                    wrongInputInside4 = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside4 = true;
                                    break;
                            }
                        }
                    } else {
                        System.out.println("No tasks fulfill you conditions");
                    }
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

    public static void modificationInterface(Scanner input) {

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
