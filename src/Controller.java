import java.io.File;

public class Controller {

    public static void makeDir() {
        final String directoryName = "repository";
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
            // If you require it to make the entire directory path including parents,
            // use directory.mkdirs(); here instead.
        }
    }

    public static void main(String[] args) {

        // Make directory for first operation
        Controller.makeDir();

        String filename = "Test Note";
        String content = "Hello World";

        Note N = new Note(filename, content);
        N.writeFile();
        System.out.println(N.toString());

        Note A = new Note(filename);
        System.out.println(A);
    }
}
