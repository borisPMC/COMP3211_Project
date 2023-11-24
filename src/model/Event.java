package model;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

public class Event extends PIR {

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
        }   catch (ParseException e) {}
    }
    public void setAlarm(String s) {
        try {
            this.alarm = new SimpleDateFormat("dd/MM/yyyy").parse(s);
            // Remind that Alarm is over Start Time
            if (isStart('<', s)) {
                System.out.println("Alarm Time is after the start time!");
            }
        }   catch (ParseException e) {}
    }
    // Getter
    public String getDescription() {return this.description;}
    public Date getStart() {return this.start;}
    public Date getAlarm() {return this.alarm;}
    public String toString() {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

        return  "Title: " + getFN() + "\n" +
                "Description: " + getDescription() + "\n" +
                "Start Time: " + df.format(getStart()) + "\n" +
                "Alarm Time: " + df.format(getAlarm());
    }

    // Searching
    public boolean isDescription(String arg) {
        return isContainString(getDescription(), arg);
    }

    public boolean isStart(char operator, String arg) { // arg format: dd/MM/yyyy
        return isDateSatisfy(getStart(), operator, arg);
    }

    public boolean isAlarm(char operator, String arg) {
        return isDateSatisfy(getAlarm(), operator, arg);
    }

    // Checking for creating PIR
    public static boolean checkSameTitle(String content, ArrayList<Event> list) {
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
