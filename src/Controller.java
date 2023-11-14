import java.io.File;
import java.util.Scanner;  // Import the Scanner class

public class Controller {

    public static void makeDir() {
        final String directoryName = "repository";
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
        }
    }

    public static void mainInterface() {

        String option = "";
        Scanner input = new Scanner(System.in);  // Create a Scanner object


        System.out.println("Welcome to PIM. Please choose your operation:");
        System.out.println("1: Create new PIR");
        System.out.println("2: Search PIR");
        System.out.println("3: Print all PIR");

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
        }
    }

    public static void main(String[] args) {

        // Make directory for first operation
        Controller.makeDir();

    }
}
