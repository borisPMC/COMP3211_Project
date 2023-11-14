import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner; // Import the Scanner class to read text files

class Event extends PIR {

    private String description;
    private Date start;
    private Date alarm;

    public Event(String filename, String description, String start, String alarm) {
        setFN(filename);
        setDescription(description);
        setStart(start);
        setAlarm(alarm);
    }

    public Event(String filename) {
        setFN(filename);
    }
    // Setter
    public void setDescription(String arg) {this.description = arg;}
    public void setStart(String s) {
        try {
            this.start = new SimpleDateFormat("dd/MM/yyyy").parse(s);
        }   catch (ParseException e) {
            System.out.println("Failed reading");
        }
    }
    public void setAlarm(String s) {
        try {
            this.alarm = new SimpleDateFormat("dd/MM/yyyy").parse(s);
            // Remind that Alarm is over Start Time
            if (isStart('<', s)) {
                System.out.println("Alarm Time is after the start time!");
            }
        }   catch (ParseException e) {
            System.out.println("Failed reading");
        }
    }
    // Getter
    public String getDescription() {return this.description;}
    public Date getStart() {return this.start;}
    public Date getAlarm() {return this.alarm;}

    public void readFile(String filename) {

        final String path = "repository\\" + filename + ".pim";

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            // Line 1: Classification Code
            if ((myReader.nextLine()).equals("E")) {
                // Line 2: description
                setDescription(myReader.nextLine());
                // Line 3: start time
                setStart(myReader.nextLine());
                // Line 4: alarm time
                setAlarm(myReader.nextLine());
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
                myWriter.write("E\n"); // Line 1: Classification Code
                myWriter.write(getDescription() + "\n"); // Line 2: Description

                // Line 3: Start Time
                DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                myWriter.write(df.format(getStart()) + "\n");

                // Line 4: Alarm
                myWriter.write(df.format(getAlarm()) + "\n");

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
                "Description: " + getDescription() + "\n" +
                "Start Time: " + df.format(getStart()) + "\n" +
                "Alarm Time: " + df.format(getAlarm());
    }

    // Searching
    public boolean isDescription(String arg) {
        return PIR.isContainString(getDescription(), arg);
    }

    public boolean isStart(char operator, String arg) { // arg format: dd/MM/yyyy
        return PIR.isDateSatisfy(getStart(), operator, arg);
    }

    public boolean isAlarm(char operator, String arg) {
        return PIR.isDateSatisfy(getAlarm(), operator, arg);
    }
}
