import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class
import java.util.Scanner; // Import the Scanner class to read text files

class Note extends PIR {

    private String content;

    public Note(String filename, String content) {
        super.filename = filename;
        this.content = content;
    }

    public Note(String filename) {
        super.filename = filename;
        this.content = readFile(filename);
    }

    public String readFile(String filename) {

        String result = "";

        final String path = "repository\\" + filename + ".pim";

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            // Line 1: Classification Code
            if ((myReader.nextLine()).equals("N")) {
                // Line 2: Content
                result = myReader.nextLine();
            };
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } finally {

        }
          
        return result;
    }

    @Override
    public String getPirType() {
        return "N";
    }

    public void writeFile() {

        final String path = "repository\\" + super.filename + ".pim";

        try {
            // Create file
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

                // Write to file
                FileWriter myWriter = new FileWriter(path);
                myWriter.write(getPirType()); // Line 1: Classification Code
                myWriter.write("\n");
                myWriter.write(this.content); // Line 2: Note content
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
        }
    }

    public String toString() {
        
        String result = "";

        result =    "Title: " + this.filename + "\n" +
                    "Content: " + this.content;

        return result;
    }
}