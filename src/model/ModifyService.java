package model;

import java.util.ArrayList;
import java.util.Scanner;

public class ModifyService {

    public static void processNoteSearchResults(ArrayList<Note> SelectedArrForNote, ArrayList<Note> ArrForNote, Scanner input) {
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
    }

    public static void processTaskSearchResults(ArrayList<Task> SelectedArrForTask, ArrayList<Task> ArrForTask, Scanner input) {
        int result = 0;
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
    }

    public static void processEventSearchResults(ArrayList<Event> SelectedArrForEvent, ArrayList<Event> ArrForEvent, Scanner input) {
        int result = 0;
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
    }

    public static void processContactSearchResults(ArrayList<Contact> SelectedArrForContact, ArrayList<Contact> ArrForContact, Scanner input) {
        int result = 0;
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
    }
}