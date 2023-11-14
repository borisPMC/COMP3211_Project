import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner; // Import the Scanner class to read text files

class Task extends PIR {

    private String description;
    private Date deadline;

    public Task(String filename, String description, String date) {
        setFN(filename);
        setDescription(description);
        setDeadline(date);
    }

    public Task(String filename) {
        setFN(filename);
    }
    // Setter
    public void setDescription(String arg) {this.description = arg;}
    public void setDeadline(String s) {
        try {
            this.deadline = new SimpleDateFormat("dd/MM/yyyy").parse(s);
        }   catch (ParseException e) {
            System.out.println("Failed reading");
        }
    }
    // Getter
    public String getDescription() {return this.description;}
    public Date getDeadline() {return this.deadline;}

    public void readFile(String filename) {

        final String path = "repository\\" + filename + ".pim";

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            // Line 1: Classification Code
            if ((myReader.nextLine()).equals("T")) {
                // Line 2: description
                setDescription(myReader.nextLine());
                // Line 3: deadline
                setDeadline(myReader.nextLine());
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
                myWriter.write("T\n"); // Line 1: Classification Code
                myWriter.write(getDescription() + "\n"); // Line 2: Description

                // Line 3: Deadline
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                myWriter.write(df.format(getDeadline()));

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

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        return  "Title: " + getFN() + "\n" +
                "Description: " + getDescription() +
                "Deadline " + df.format(getDeadline());
    }

    // Searching
    public boolean isDescription(String arg) {
        return PIR.isContainString(getDescription(), arg);
    }
    public boolean isDeadline(char operator, String arg) { // arg format: dd/MM/yyyy
        return PIR.isDateSatisfy(getDeadline(), operator, arg);
    }
}
