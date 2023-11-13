import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

abstract class PIR {
    
    protected String filename;

    public void setFN(String filename) {
        this.filename = filename;
    }

    public String getFN() {
        return this.filename;
    }

    public String getPirType() {

        String result = "";

        final String path = "repository\\" + getFN() + ".pim";

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
    
    abstract public String readFile(String filename);
    abstract public void writeFile();
    abstract public String toString();
}