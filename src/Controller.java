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

    static ArrayList<Note> ArrForNote = new ArrayList<Note>();
    static ArrayList<Task> ArrForTask = new ArrayList<Task>();
    static ArrayList<Event> ArrForEvent = new ArrayList<Event>();
    static ArrayList<Contact> ArrForContact = new ArrayList<Contact>();


    public static void mainInterface() {

        String option = "";
        Scanner input = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Welcome to PIM. Please choose your operation:");
        System.out.println("1: Create new PIR");
        System.out.println("2: Search PIR");
        System.out.println("3: Print all PIR");
        System.out.println("4: Exit");

        boolean wrongInput = true;
        while (wrongInput) {
            option = input.nextLine();
            switch (option) {
                case ("1"):
                    Controller.createInterface();
                    wrongInput = false;
                    break;
                case ("2"):
                    Controller.searchInterface();
                    wrongInput = false;
                    break;
                case ("3"):
                    Controller.printAllInterface();
                    wrongInput = false;
                    break;
                case ("4"):
                    System.out.println("Goodbye!");
                    wrongInput = false;
                    System.exit(0);
                default:
                    System.out.println("Wrong value inputted, please type again!");
                    wrongInput = true;
                    break;
            }
        }
    }

    public static void createInterface() {
        String option = "";
        Scanner input = new Scanner(System.in);  // Create a Scanner object

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
                        mainInterface();
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
                                System.out.println("Please input the title of note");
                                String fileNameForNote = input.nextLine();
                                System.out.println("Please input the texts to the note");
                                String contentForContent = input.nextLine();
                                Note note = new Note(fileNameForNote, contentForContent);
                                ArrForNote.add(note);
                                wrongInputInside = false;
                                break;
                            case ("2"):
                                System.out.println("Please input the title of task");
                                String fileNameForTask = input.nextLine();
                                System.out.println("Please input the descriptions of task");
                                String descriptionsForTask = input.nextLine();
                                System.out.println("Please input the deadline of tasks in format dd/MM/yyyy");
                                String deadlineForTask = input.nextLine();
                                Task task = new Task(fileNameForTask, descriptionsForTask, deadlineForTask);
                                ArrForTask.add(task);
                                wrongInputInside = false;
                                break;
                            case ("3"):
                                System.out.println("Please input the descriptions of event");
                                String fileNameForEvent = input.nextLine();
                                System.out.println("Please input the descriptions of event");
                                String descriptionsForEvent = input.nextLine();
                                System.out.println("Please input the starting times of event in format dd/MM/yyyy");
                                String startingTimeForEvent = input.nextLine();
                                System.out.println("Please input the alarm of event in format dd/MM/yyyy");
                                String alarmForEvent = input.nextLine();
                                Event event = new Event(fileNameForEvent, descriptionsForEvent, startingTimeForEvent, alarmForEvent);
                                ArrForEvent.add(event);
                                wrongInputInside = false;
                                break;
                            case ("4"):
                                System.out.println("Please input the name of contact");
                                String nameForContact = input.nextLine();
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
        mainInterface();
    }

    public static void searchInterface() {
        Scanner input = new Scanner(System.in);
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
                    ArrayList<Note> SelectedArrForNote = ArrForNote;
                    boolean moreConditionForNote = true;
                    while (moreConditionForNote) {
                        System.out.println("Please choose the condition for note:");
                        System.out.println("1. The string contain in content");
                        System.out.println("2. The string not contain in content");
                        String option2 = input.nextLine();
                        boolean wrongInputInside = true;
                        while (wrongInputInside) {
                            switch (option2) {
                                case ("1"):
                                    System.out.println("Please choose the string to be contained in content");
                                    content = input.nextLine();
                                    ArrayList<Note> tempArr = new ArrayList<Note>();
                                    for (int i = 0; i < SelectedArrForNote.size(); i++) {
                                        if (SelectedArrForNote.get(i).isContent(content)) {
                                            tempArr.add(SelectedArrForNote.get(i));
                                        }
                                    }
                                    SelectedArrForNote = tempArr;
                                    wrongInputInside = false;
                                    break;
                                case ("2"):
                                    System.out.println("Please choose the string not contained in content");
                                    content = input.nextLine();
                                    ArrayList<Note> tempArr2 = new ArrayList<Note>();
                                    for (int i = 0; i < SelectedArrForNote.size(); i++) {
                                        if (!(SelectedArrForNote.get(i).isContent(content))) {
                                            tempArr2.add(SelectedArrForNote.get(i));
                                        }
                                    }
                                    SelectedArrForNote = tempArr2;
                                    wrongInputInside = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside = true;
                            }
                        }
                        System.out.println("Do you need more condition?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        boolean wrongInputInside2 = true;
                        while (wrongInputInside2) {
                            String option3 = input.nextLine();
                            switch (option3) {
                                case ("1"):
                                    moreConditionForNote = true;
                                    wrongInputInside2 = false;
                                    break;
                                case ("2"):
                                    moreConditionForNote = false;
                                    wrongInputInside2 = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside2 = true;
                            }
                        }
                    }
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
                    ArrayList<Task> SelectedArrForTask = ArrForTask;
                    boolean moreConditionForTask = true;
                    while (moreConditionForTask) {
                        System.out.println("Please choose the condition for task:");
                        System.out.println("1. The string contain in description");
                        System.out.println("2. The string not contain in description");
                        System.out.println("3. The date before deadline");
                        System.out.println("4. The date after deadline");
                        System.out.println("5. The date equal deadline");
                        boolean wrongInputInside = true;
                        while (wrongInputInside) {
                            String option2 = input.nextLine();
                            switch (option2) {
                                case ("1"):
                                    System.out.println("Please choose the string to be contained in description");
                                    content = input.nextLine();
                                    ArrayList<Task> tempArr = new ArrayList<Task>();
                                    for (int i = 0; i < SelectedArrForTask.size(); i++) {
                                        if (SelectedArrForTask.get(i).isDescription(content)) {
                                            tempArr.add(SelectedArrForTask.get(i));
                                        }
                                    }
                                    SelectedArrForTask = tempArr;
                                    wrongInputInside = false;
                                    break;
                                case ("2"):
                                    System.out.println("Please choose the string not to be contained in description");
                                    content = input.nextLine();
                                    ArrayList<Task> tempArr2 = new ArrayList<Task>();
                                    for (int i = 0; i < SelectedArrForTask.size(); i++) {
                                        if (!(SelectedArrForTask.get(i).isDescription(content))) {
                                            tempArr2.add(SelectedArrForTask.get(i));
                                        }
                                    }
                                    SelectedArrForTask = tempArr2;
                                    wrongInputInside = false;
                                    break;
                                case ("3"):
                                    System.out.println("Please choose the date that before deadline in format dd/MM/yyyy");
                                    content = input.nextLine();
                                    ArrayList<Task> tempArr3 = new ArrayList<Task>();
                                    for (int i = 0; i < SelectedArrForTask.size(); i++) {
                                        if (SelectedArrForTask.get(i).isDeadline('<', content)) {
                                            tempArr3.add(SelectedArrForTask.get(i));
                                        }
                                    }
                                    SelectedArrForTask = tempArr3;
                                    wrongInputInside = false;
                                    break;
                                case ("4"):
                                    System.out.println("Please choose the date that after deadline in format dd/MM/yyyy");
                                    content = input.nextLine();
                                    ArrayList<Task> tempArr4 = new ArrayList<Task>();
                                    for (int i = 0; i < SelectedArrForTask.size(); i++) {
                                        if (SelectedArrForTask.get(i).isDeadline('>', content)) {
                                            tempArr4.add(SelectedArrForTask.get(i));
                                        }
                                    }
                                    SelectedArrForTask = tempArr4;
                                    wrongInputInside = false;
                                    break;
                                case ("5"):
                                    System.out.println("Please choose the date that before deadline in format dd/MM/yyyy");
                                    content = input.nextLine();
                                    ArrayList<Task> tempArr5 = new ArrayList<Task>();
                                    for (int i = 0; i < SelectedArrForTask.size(); i++) {
                                        if (SelectedArrForTask.get(i).isDeadline('=', content)) {
                                            tempArr5.add(SelectedArrForTask.get(i));
                                        }
                                    }
                                    SelectedArrForTask = tempArr5;
                                    wrongInputInside = false;
                                    break;
                                default:
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside = true;
                                    break;
                            }
                        }
                        System.out.println("Do you need more condition?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        boolean wrongInputInside2 = true;
                        while (wrongInputInside2) {
                            String option3 = input.nextLine();
                            switch (option3) {
                                case ("1"):
                                    moreConditionForTask = true;
                                    wrongInputInside2 = false;
                                    break;
                                case ("2"):
                                    moreConditionForTask = false;
                                    wrongInputInside2 = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside2 = true;
                                    break;
                            }
                        }
                    }
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
                                    System.out.println("Please type the new deadline for task in format dd/MM/yyyy");
                                    String date = input.nextLine();
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
                    ArrayList<Event> SelectedArrForEvent = ArrForEvent;
                    boolean moreConditionForEvent = true;
                    while (moreConditionForEvent) {
                        System.out.println("Please choose the condition for task:");
                        System.out.println("1. The string contain in description");
                        System.out.println("2. The string not contain in description");
                        System.out.println("3. The date before starting times");
                        System.out.println("4. The date after starting times");
                        System.out.println("5. The date equal starting times");
                        System.out.println("6. The date before alarm");
                        System.out.println("7. The date after alarm");
                        System.out.println("8. The date equal alarm");
                        boolean wrongInputInside = true;
                        while (wrongInputInside) {
                            String option2 = input.nextLine();
                            switch (option2) {
                                case ("1"):
                                    System.out.println("Please choose the string to be contained in description");
                                    content = input.nextLine();
                                    ArrayList<Event> tempArr = new ArrayList<Event>();
                                    for (int i = 0; i < SelectedArrForEvent.size(); i++) {
                                        if (SelectedArrForEvent.get(i).isDescription(content)) {
                                            tempArr.add(SelectedArrForEvent.get(i));
                                        }
                                    }
                                    SelectedArrForEvent = tempArr;
                                    wrongInputInside = false;
                                    break;
                                case ("2"):
                                    System.out.println("Please choose the string not to be contained in description");
                                    content = input.nextLine();
                                    ArrayList<Event> tempArr2 = new ArrayList<Event>();
                                    for (int i = 0; i < SelectedArrForEvent.size(); i++) {
                                        if (!(SelectedArrForEvent.get(i).isDescription(content))) {
                                            tempArr2.add(SelectedArrForEvent.get(i));
                                        }
                                    }
                                    SelectedArrForEvent = tempArr2;
                                    wrongInputInside = false;
                                    break;
                                case ("3"):
                                    System.out.println("Please choose the date that before starting time in format dd/MM/yyyy");
                                    content = input.nextLine();
                                    ArrayList<Event> tempArr3 = new ArrayList<Event>();
                                    for (int i = 0; i < SelectedArrForEvent.size(); i++) {
                                        if (SelectedArrForEvent.get(i).isStart('<', content)) {
                                            tempArr3.add(SelectedArrForEvent.get(i));
                                        }
                                    }
                                    SelectedArrForEvent = tempArr3;
                                    wrongInputInside = false;
                                    break;
                                case ("4"):
                                    System.out.println("Please choose the date that after starting time in format dd/MM/yyyy");
                                    content = input.nextLine();
                                    ArrayList<Event> tempArr4 = new ArrayList<Event>();
                                    for (int i = 0; i < SelectedArrForEvent.size(); i++) {
                                        if (SelectedArrForEvent.get(i).isStart('>', content)) {
                                            tempArr4.add(SelectedArrForEvent.get(i));
                                        }
                                    }
                                    SelectedArrForEvent = tempArr4;
                                    wrongInputInside = false;
                                    break;
                                case ("5"):
                                    System.out.println("Please choose the date that equal starting time in format dd/MM/yyyy");
                                    content = input.nextLine();
                                    ArrayList<Event> tempArr5 = new ArrayList<Event>();
                                    for (int i = 0; i < SelectedArrForEvent.size(); i++) {
                                        if (SelectedArrForEvent.get(i).isStart('=', content)) {
                                            tempArr5.add(SelectedArrForEvent.get(i));
                                        }
                                    }
                                    SelectedArrForEvent = tempArr5;
                                    wrongInputInside = false;
                                    break;
                                case ("6"):
                                    System.out.println("Please choose the date that before alarm in format dd/MM/yyyy");
                                    content = input.nextLine();
                                    ArrayList<Event> tempArr6 = new ArrayList<Event>();
                                    for (int i = 0; i < SelectedArrForEvent.size(); i++) {
                                        if (SelectedArrForEvent.get(i).isAlarm('<', content)) {
                                            tempArr6.add(SelectedArrForEvent.get(i));
                                        }
                                    }
                                    SelectedArrForEvent = tempArr6;
                                    wrongInputInside = false;
                                    break;
                                case ("7"):
                                    System.out.println("Please choose the date that after alarm in format dd/MM/yyyy");
                                    content = input.nextLine();
                                    ArrayList<Event> tempArr7 = new ArrayList<Event>();
                                    for (int i = 0; i < SelectedArrForEvent.size(); i++) {
                                        if (SelectedArrForEvent.get(i).isAlarm('>', content)) {
                                            tempArr7.add(SelectedArrForEvent.get(i));
                                        }
                                    }
                                    SelectedArrForEvent = tempArr7;
                                    wrongInputInside = false;
                                    break;
                                case ("8"):
                                    System.out.println("Please choose the date that equal alarm in format dd/MM/yyyy");
                                    content = input.nextLine();
                                    ArrayList<Event> tempArr8 = new ArrayList<Event>();
                                    for (int i = 0; i < SelectedArrForEvent.size(); i++) {
                                        if (SelectedArrForEvent.get(i).isAlarm('=', content)) {
                                            tempArr8.add(SelectedArrForEvent.get(i));
                                        }
                                    }
                                    SelectedArrForEvent = tempArr8;
                                    wrongInputInside = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside = true;
                                    break;
                            }
                        }
                        System.out.println("Do you need more condition?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        boolean wrongInputInside2 = true;
                        while (wrongInputInside2) {
                            String option3 = input.nextLine();
                            switch (option3) {
                                case ("1"):
                                    moreConditionForEvent = true;
                                    wrongInputInside2 = false;
                                    break;
                                case ("2"):
                                    moreConditionForEvent = false;
                                    wrongInputInside2 = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside2 = true;
                                    break;
                            }
                        }
                    }
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
                                    System.out.println("Please type the new starting time for task in format dd/MM/yyyy");
                                    String startTime = input.nextLine();
                                    ArrForEvent.get(result).setStart(startTime);
                                    System.out.println("Successfully modified");
                                    wrongInputInside4 = false;
                                    break;
                                case ("3"):
                                    System.out.println("Please type the new alarm for task in format dd/MM/yyyy");
                                    String alarm = input.nextLine();
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
                    ArrayList<Contact> SelectedArrForContact = ArrForContact;
                    boolean moreConditionForContact = true;
                    while (moreConditionForContact) {
                        System.out.println("Please choose the condition for task:");
                        System.out.println("1. The string contain in name");
                        System.out.println("2. The string not contain in name");
                        System.out.println("3. The string contain in address");
                        System.out.println("4. The string not contain in address");
                        System.out.println("5. The string contain in mobile number");
                        System.out.println("6. The string not contain in mobile number");
                        boolean wrongInputInside = true;
                        while (wrongInputInside) {
                            String option2 = input.nextLine();
                            switch (option2) {
                                case ("1"):
                                    System.out.println("Please choose the string to be contained in name");
                                    content = input.nextLine();
                                    ArrayList<Contact> tempArr = new ArrayList<Contact>();
                                    for (int i = 0; i < SelectedArrForContact.size(); i++) {
                                        if (SelectedArrForContact.get(i).isName(content)) {
                                            tempArr.add(SelectedArrForContact.get(i));
                                        }
                                    }
                                    SelectedArrForContact = tempArr;
                                    wrongInputInside = false;
                                    break;
                                case ("2"):
                                    System.out.println("Please choose the string not to be contained in name");
                                    content = input.nextLine();
                                    ArrayList<Contact> tempArr2 = new ArrayList<Contact>();
                                    for (int i = 0; i < SelectedArrForContact.size(); i++) {
                                        if (!(SelectedArrForContact.get(i).isName(content))) {
                                            tempArr2.add(SelectedArrForContact.get(i));
                                        }
                                    }
                                    SelectedArrForContact = tempArr2;
                                    wrongInputInside = false;
                                    break;
                                case ("3"):
                                    System.out.println("Please choose the string to be contained in address");
                                    content = input.nextLine();
                                    ArrayList<Contact> tempArr3 = new ArrayList<Contact>();
                                    for (int i = 0; i < SelectedArrForContact.size(); i++) {
                                        if (SelectedArrForContact.get(i).isAddress(content)) {
                                            tempArr3.add(SelectedArrForContact.get(i));
                                        }
                                    }
                                    SelectedArrForContact = tempArr3;
                                    wrongInputInside = false;

                                    break;
                                case ("4"):
                                    System.out.println("Please choose the string not to be contained in address");
                                    content = input.nextLine();
                                    ArrayList<Contact> tempArr4 = new ArrayList<Contact>();
                                    for (int i = 0; i < SelectedArrForContact.size(); i++) {
                                        if (!(SelectedArrForContact.get(i).isAddress(content))) {
                                            tempArr4.add(SelectedArrForContact.get(i));
                                        }
                                    }
                                    SelectedArrForContact = tempArr4;
                                    wrongInputInside = false;
                                    break;
                                case ("5"):
                                    System.out.println("Please choose the string to be contained in mobile number");
                                    content = input.nextLine();
                                    ArrayList<Contact> tempArr5 = new ArrayList<Contact>();
                                    for (int i = 0; i < SelectedArrForContact.size(); i++) {
                                        if (SelectedArrForContact.get(i).isPhone(content)) {
                                            tempArr5.add(SelectedArrForContact.get(i));
                                        }
                                    }
                                    SelectedArrForContact = tempArr5;
                                    wrongInputInside = false;
                                    break;
                                case ("6"):
                                    System.out.println("Please choose the string not to be contained in mobile number");
                                    content = input.nextLine();
                                    ArrayList<Contact> tempArr6 = new ArrayList<Contact>();
                                    for (int i = 0; i < SelectedArrForContact.size(); i++) {
                                        if (!(SelectedArrForContact.get(i).isPhone(content))) {
                                            tempArr6.add(SelectedArrForContact.get(i));
                                        }
                                    }
                                    SelectedArrForContact = tempArr6;
                                    wrongInputInside = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside = true;
                                    break;
                            }
                        }
                        System.out.println("Do you need more condition?");
                        System.out.println("1. Yes");
                        System.out.println("2. No");
                        boolean wrongInputInside2 = true;
                        while (wrongInputInside2) {
                            String option3 = input.nextLine();
                            switch (option3) {
                                case ("1"):
                                    moreConditionForContact = true;
                                    wrongInputInside2 = false;
                                    break;
                                case ("2"):
                                    moreConditionForContact = false;
                                    wrongInputInside2 = false;
                                    break;
                                default :
                                    System.out.println("Wrong value inputted, please type again!");
                                    wrongInputInside2 = true;

                            }
                        }
                    }
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
                                    System.out.println("Please type the new name for task");
                                    String content2 = input.nextLine();
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
        mainInterface();
    }

    public static void printAllInterface() {
        Scanner input = new Scanner(System.in);
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
        mainInterface();
    }

    public static void main(String[] args) {
        // Make directory for first operation
        Controller.makeDir();
        Controller.mainInterface();

    }
}
