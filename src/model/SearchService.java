package model;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchService {
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
    public static ArrayList<Note> searchNote(Scanner input, ArrayList<Note> ArrForNote) {

        String content = "";
        ArrayList<Note> SelectedArrForNote = ArrForNote;

        boolean moreConditionForNote = true;
        while (moreConditionForNote) {
            System.out.println("Please choose the condition for note:");
            System.out.println("1. The string contain in content");
            System.out.println("2. The string not contain in content");
            boolean wrongInputInside = true;
            while (wrongInputInside) {
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
            moreConditionForNote = requestCriteria(input);
        }
        return SelectedArrForNote;
    }

    public static ArrayList<Task> searchTask(Scanner input, ArrayList<Task> ArrForTask) {

        String content;
        ArrayList<Task> SelectedArrForTask = ArrForTask;

        boolean moreConditionForTask = true;
        while (moreConditionForTask) {
            System.out.println("Please choose the condition for task:");
            System.out.println("1. The string contain in description");
            System.out.println("2. The string not contain in description");
            System.out.println("3. Selected date (>) deadline");
            System.out.println("4. Selected date (<) deadline");
            System.out.println("5. Selected date (=) deadline");
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
                        System.out.println("The chosen deadline shall be before (dd/MM/yyyy): ");
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
                        System.out.println("The chosen deadline shall be after (dd/MM/yyyy): ");
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
                        System.out.println("The chosen deadline shall be on (dd/MM/yyyy): ");
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
            moreConditionForTask = requestCriteria(input);
        }
        return SelectedArrForTask;
    }

    public static ArrayList<Event> searchEvent(Scanner input, ArrayList<Event> ArrForEvent) {

        ArrayList<Event> SelectedArrForEvent = ArrForEvent;
        String content;

        boolean moreConditionForEvent = true;
        while (moreConditionForEvent) {
            System.out.println("Please choose the condition for event:");
            System.out.println("1. The string contain in description");
            System.out.println("2. The string not contain in description");
            System.out.println("3. Selected date (>) starting time");
            System.out.println("4. Selected date (<) starting time");
            System.out.println("5. Selected date (=) starting time");
            System.out.println("6. Selected date (>) alarm time");
            System.out.println("7. Selected date (<) alarm time");
            System.out.println("8. Selected date (=) alarm time");
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
                        System.out.println("The chosen start time shall be before (dd/MM/yyyy): ");
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
                        System.out.println("The chosen start time shall be after (dd/MM/yyyy): ");
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
                        System.out.println("The chosen start time shall be on (dd/MM/yyyy): ");
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
                        System.out.println("The chosen alarm time shall be before (dd/MM/yyyy): ");
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
                        System.out.println("The chosen alarm time shall be after (dd/MM/yyyy): ");
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
                        System.out.println("The chosen alarm time shall be on (dd/MM/yyyy): ");
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
            moreConditionForEvent = requestCriteria(input);
        }
        return SelectedArrForEvent;
    }

    public static ArrayList<Contact> searchContact(Scanner input, ArrayList<Contact> ArrForContact) {

        String content;
        ArrayList<Contact> SelectedArrForContact = ArrForContact;

        boolean moreConditionForContact = true;
        while (moreConditionForContact) {
            System.out.println("Please choose the condition for contact:");
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
            moreConditionForContact = requestCriteria(input);
        }
        return SelectedArrForContact;
    }
}
