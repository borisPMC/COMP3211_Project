import java.io.File;
import java.nio.channels.SelectionKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;  // Import the Scanner class
import java.util.Date;

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

        option = input.nextLine();
        switch (option) {
            case ("1"):
                Controller.createInterface();
                break;
            case ("2"):
                Controller.searchInterface();
                break;
            case ("3"):
                Controller.printAllInterface();
                break;
            case ("4"):
                System.out.println("Goodbye!");
                System.exit(0);
        }
    }

    public static void createInterface() {

        String option = "";
        Scanner input = new Scanner(System.in);  // Create a Scanner object

        System.out.println("Please choose the input format of PIR");
        System.out.println("1: Input pim files");
        System.out.println("2: Create a new PIR");
        option = input.nextLine();
        switch (option) {
            case ("1"):

                break;
            case ("2"):
                System.out.println("Please choose the type of PIR");
                System.out.println("1: Notes");
                System.out.println("2: Tasks");
                System.out.println("3: Events");
                System.out.println("4: Contacts");
                    String optionInside = input.nextLine();
                    switch (optionInside) {
                        case ("1"):
                            System.out.println("Please input the texts to the note");
                            String contentForContent = input.nextLine();
                            Note note = new Note("123", contentForContent);
                            ArrForNote.add(note);
                            break;
                        case ("2"):
                            System.out.println("Please input the descriptions of task");
                            String descriptionsForTask = input.nextLine();
                            System.out.println("Please input the deadline of tasks in format dd/MM/yyyy");
                            String deadlineForTask = input.nextLine();
                            Task task = new Task("123", descriptionsForTask, deadlineForTask);
                            ArrForTask.add(task);
                            break;
                        case ("3"):
                            System.out.println("Please input the descriptions of event");
                            String descriptionsForEvent = input.nextLine();
                            System.out.println("Please input the starting times of event in format dd/MM/yyyy");
                            String startingTimeForEvent = input.nextLine();
                            System.out.println("Please input the alarm of event in format dd/MM/yyyy");
                            String alarmForEvent = input.nextLine();
                            Event event = new Event("123", descriptionsForEvent, startingTimeForEvent, alarmForEvent);
                            ArrForEvent.add(event);
                            break;
                        case ("4"):
                            System.out.println("Please input the name of contact");
                            String nameForContact = input.nextLine();
                            System.out.println("Please input the address of contact");
                            String addressForContact = input.nextLine();
                            System.out.println("Please input the mobile number of contact");
                            String mobileNumberForContact = input.nextLine();
                            Contact contact = new Contact("123", nameForContact, addressForContact, mobileNumberForContact);
                            ArrForContact.add(contact);
                            break;
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
                            break;
                    }
                    System.out.println("Do you need more condition?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    String option3 = input.nextLine();
                    switch (option3) {
                        case ("1"):
                            moreConditionForNote = true;
                            break;
                        case ("2"):
                            moreConditionForNote = false;
                            break;
                    }
                }
                int result = 0;
                if (SelectedArrForNote.size() != 0) {
                    int index = 1;
                    System.out.println("Please choose from specify Note");
                    for (int i = 0; i < SelectedArrForNote.size(); i++)  {
                        System.out.println(index + "  " + SelectedArrForNote.get(i).toString());
                    }
                    String option2 = input.nextLine();
                    int require = Integer.parseInt(option2) - 1;
                    for (int i = 0; i < ArrForNote.size();i++) {
                        if (SelectedArrForNote.get(require) == ArrForNote.get(i)) {
                            result = i;
                            break;
                        }
                    }
                    System.out.println("Please choose the type for operation");
                    System.out.println("1. Modify");
                    System.out.println("2. Delete");
                    String option3 = input.nextLine();
                    switch (option3) {
                        case("1"):
                            System.out.println("Please type the new content for note");
                            String content2 = input.nextLine();
                            ArrForNote.get(result).setContent(content2);
                            System.out.println("Successfully modified");
                            break;
                        case ("2"):
                            ArrForNote.remove(result);
                            System.out.println("Successfully deleted");
                            break;
                    }
                } else {
                    System.out.println("No notes fulfill you conditions");
                }
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
                            break;
                    }
                    System.out.println("Do you need more condition?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    String option3 = input.nextLine();
                    switch (option3) {
                        case ("1"):
                            moreConditionForTask = true;
                            break;
                        case ("2"):
                            moreConditionForTask = false;
                            break;
                    }
                }
                result = 0;
                if (SelectedArrForTask.size() != 0) {
                    int index = 1;
                    System.out.println("Please choose from specify Task");
                    for (int i = 0; i < SelectedArrForTask.size(); i++)  {
                        System.out.println(index + "  " + SelectedArrForTask.get(i).toString());
                    }
                    String option2 = input.nextLine();
                    int require = Integer.parseInt(option2) - 1;
                    for (int i = 0; i < ArrForTask.size();i++) {
                        if (SelectedArrForTask.get(require) == ArrForTask.get(i)) {
                            result = i;
                            break;
                        }
                    }
                    System.out.println("Please choose the type for operation");
                    System.out.println("1. Modify Description");
                    System.out.println("2. Modify Deadline");
                    System.out.println("3. Delete");
                    String option3 = input.nextLine();
                    switch (option3) {
                        case("1"):
                            System.out.println("Please type the new description for task");
                            String content2 = input.nextLine();
                            ArrForTask.get(result).setDescription(content2);
                            System.out.println("Successfully modified");
                            break;
                        case("2"):
                            System.out.println("Please type the new deadline for task in format dd/MM/yyyy");
                            String date = input.nextLine();
                            ArrForTask.get(result).setDeadline(date);
                            System.out.println("Successfully modified");
                            break;
                        case ("3"):
                            ArrForTask.remove(result);
                            System.out.println("Successfully deleted");
                            break;
                    }
                } else {
                    System.out.println("No tasks fulfill you conditions");
                }
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
                            break;
                    }
                    System.out.println("Do you need more condition?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    String option3 = input.nextLine();
                    switch (option3) {
                        case ("1"):
                            moreConditionForTask = true;
                            break;
                        case ("2"):
                            moreConditionForTask = false;
                            break;
                    }
                }
                result = 0;
                if (SelectedArrForEvent.size() != 0) {
                    int index = 1;
                    System.out.println("Please choose from specify Event");
                    for (int i = 0; i < SelectedArrForEvent.size(); i++)  {
                        System.out.println(index + "  " + SelectedArrForEvent.get(i).toString());
                    }
                    String option2 = input.nextLine();
                    int require = Integer.parseInt(option2) - 1;
                    for (int i = 0; i < ArrForEvent.size();i++) {
                        if (SelectedArrForEvent.get(require) == ArrForEvent.get(i)) {
                            result = i;
                            break;
                        }
                    }
                    System.out.println("Please choose the type for operation");
                    System.out.println("1. Modify Description");
                    System.out.println("2. Modify Starting Time");
                    System.out.println("3. Modify Alarm");
                    System.out.println("4. Delete");
                    String option3 = input.nextLine();
                    switch (option3) {
                        case("1"):
                            System.out.println("Please type the new description for task");
                            String content2 = input.nextLine();
                            ArrForEvent.get(result).setDescription(content2);
                            System.out.println("Successfully modified");
                            break;
                        case("2"):
                            System.out.println("Please type the new starting time for task in format dd/MM/yyyy");
                            String startTime = input.nextLine();
                            ArrForEvent.get(result).setStart(startTime);
                            System.out.println("Successfully modified");
                            break;
                        case("3"):
                            System.out.println("Please type the new alarm for task in format dd/MM/yyyy");
                            String alarm = input.nextLine();
                            ArrForEvent.get(result).setAlarm(alarm);
                            System.out.println("Successfully modified");
                            break;
                        case ("4"):
                            ArrForEvent.remove(result);
                            System.out.println("Successfully deleted");
                            break;
                    }
                } else {
                    System.out.println("No events fulfill you conditions");
                }
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
                            break;
                    }
                    System.out.println("Do you need more condition?");
                    System.out.println("1. Yes");
                    System.out.println("2. No");
                    String option3 = input.nextLine();
                    switch (option3) {
                        case ("1"):
                            moreConditionForContact = true;
                            break;
                        case ("2"):
                            moreConditionForContact = false;
                            break;
                    }
                }
                result = 0;
                if (SelectedArrForContact.size() != 0) {
                    int index = 1;
                    System.out.println("Please choose from specify Event");
                    for (int i = 0; i < SelectedArrForContact.size(); i++)  {
                        System.out.println(index + "  " + SelectedArrForContact.get(i).toString());
                    }
                    String option2 = input.nextLine();
                    int require = Integer.parseInt(option2) - 1;
                    for (int i = 0; i < ArrForContact.size();i++) {
                        if (SelectedArrForContact.get(require) == ArrForContact.get(i)) {
                            result = i;
                            break;
                        }
                    }
                    System.out.println("Please choose the type for operation");
                    System.out.println("1. Modify Name");
                    System.out.println("2. Modify Address");
                    System.out.println("3. Modify Mobile Number");
                    System.out.println("4. Delete");
                    String option3 = input.nextLine();
                    switch (option3) {
                        case("1"):
                            System.out.println("Please type the new name for task");
                            String content2 = input.nextLine();
                            ArrForContact.get(result).setName(content2);
                            System.out.println("Successfully modified");
                            break;
                        case("2"):
                            System.out.println("Please type the new address for task");
                            String startTime = input.nextLine();
                            ArrForContact.get(result).setAddress(startTime);
                            System.out.println("Successfully modified");
                            break;
                        case("3"):
                            System.out.println("Please type the new mobile number for task");
                            String alarm = input.nextLine();
                            ArrForContact.get(result).setPhone(alarm);
                            System.out.println("Successfully modified");
                            break;
                        case ("4"):
                            ArrForContact.remove(result);
                            System.out.println("Successfully deleted");
                            break;
                    }
                } else {
                    System.out.println("No tasks fulfill you conditions");
                }
                break;
        }
        mainInterface();
    }

    public static void printAllInterface() {
        System.out.println("Printing out all PIRs");
        mainInterface();
    }

    public static void main(String[] args) {

        // Make directory for first operation
        Controller.makeDir();
        Scanner input = new Scanner(System.in);
        Controller.mainInterface();

    }
}
