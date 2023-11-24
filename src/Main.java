import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Make directory for first operation
        Controller.makeDir();

        Scanner scanner = new Scanner(System.in);  // Input from prompt manually

        Controller.mainInterface(scanner);
    }
}
