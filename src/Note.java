import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class
import java.util.Scanner; // Import the Scanner class to read text files

class Note extends PIR {

    private String content;

    public Note(String filename, String content) {
        setFN(filename);
        setContent(content);
    }

    public Note(String filename) {
        setFN(filename);
    }

    // Setter
    public void setContent(String arg) {this.content = arg;}
    // Getter
    public String getContent() {return this.content;}

    public void readFile(String filename) {

        final String path = "repository\\" + filename + ".pim";

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            // Line 1: Classification Code
            if ((myReader.nextLine()).equals("N")) {
                // Line 2: Content
                setContent(myReader.nextLine());
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
            e.printStackTrace();
        }
    }

    public void writeFile() {

        final String path = "repository\\" + getFN() + ".pim";

        try {
            // Create file
            File myObj = new File(path);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());

                // Write to file
                FileWriter myWriter = new FileWriter(path);
                myWriter.write("N\n"); // Line 1: Classification Code
                myWriter.write(getContent()); // Line 2: Note content
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("File already exists.");
        }
    }

    public String toString() {

        return  "Title: " + getFN() + "\n" +
                "Content: " + getContent();
    }

    // Searching
    public boolean isContent(String arg) {
        return PIR.isContainString(getContent(), arg);
    }
}