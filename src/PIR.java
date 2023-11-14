import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner; // Import the Scanner class to read text files

abstract class PIR {
    
    protected String filename;

    public void setFN(String filename) {
        this.filename = filename;
    }
    public String getFN() {
        return this.filename;
    }

    // To know which subtype of PIR is
    static public String getPirType(String filename) {

        String result = "";
        final String path = "repository\\" + filename + ".pim";

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            // Line 1: Classification Code
            result = myReader.nextLine();

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
          
        return result;
    }

    // search method 1: string
    static public boolean isContainString(String s, String arg) {
        return (s.contains(arg));
    }

    // search method 2: date
    static public boolean isDateSatisfy(Date d, char operator, String arg) {
        Boolean result = false;

        try {
            Date argDate = new SimpleDateFormat("dd/MM/yyyy").parse(arg);
            switch (operator) {
                case '<': // Condition: is before argDate?
                    result = d.before(argDate);
                    break;
                case '=':  // Condition: is equals argDate?
                    result = d.equals(argDate);
                    break;
                case '>':  // Condition: is after argDate?
                    result = d.after(argDate);
                    break;
            }
        } catch (ParseException e) {
            System.out.println("Cannot convert to Date!");
        } finally {
            return result;
        }
    }
    
    abstract public void readFile(String filename);
    abstract public void writeFile();
    abstract public String toString();
}