package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Task extends PIR {

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
        }   catch (ParseException e) {}
    }
    // Getter
    public String getDescription() {return this.description;}
    public Date getDeadline() {return this.deadline;}

    public String toString() {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        return  "Title: " + getFN() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Deadline: " + df.format(getDeadline());
    }

    // Searching
    public boolean isDescription(String arg) {
        return isContainString(getDescription(), arg);
    }
    public boolean isDeadline(char operator, String arg) { // arg format: dd/MM/yyyy
        return isDateSatisfy(getDeadline(), operator, arg);
    }

    // Checking for creating PIR
    public static boolean checkSameTitle(String content, ArrayList<Task> list) {
        for (int i = 0;i < list.size();i++) {
            if (content.equals(list.get(i).filename)) {
                return true;
            }
        }
        return false;
    }

    public static boolean checkDate(String content) {
        Date testingDate;
        try {
            testingDate = new SimpleDateFormat("dd/MM/yyyy").parse(content);
            return false;
        }   catch (ParseException e) {
            return true;
        }
    }
}
