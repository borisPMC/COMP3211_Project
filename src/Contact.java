import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class
import java.util.Scanner; // Import the Scanner class to read text files

class Contact extends PIR {

    private String name;
    private String address;
    private String phone;

    public Contact(String name, String address, String phone) {
        setFN(name);
        setName(name);
        setAddress(address);
        setPhone(phone);
    }

    public Contact(String filename) {
        setFN(filename);
    }

    // Setter
    public void setName(String arg) {this.name = arg;}
    public void setAddress(String arg) {this.address = arg;}
    public void setPhone(String arg) {this.phone = arg;}
    // Getter
    public String getName() {return this.name;}
    public String getAddress() {return this.address;}
    public String getPhone() {return this.phone;}

    public void readFile(String filename) {

        final String path = "repository\\" + filename + ".pim";

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);

            // Line 1: Classification Code
            if ((myReader.nextLine()).equals("C")) {
                // Line 2: Name
                setName(myReader.nextLine());
                // Line 3: Address
                setAddress(myReader.nextLine());
                // Line 4: Phone
                setPhone(myReader.nextLine());
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
                myWriter.write("C\n"); // Line 1: Classification Code
                myWriter.write(getName()+"\n"); // Line 2: Name
                myWriter.write(getAddress()+"\n"); // Line 3: Address
                myWriter.write(getPhone()+"\n"); // Line 4: Phone
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

        return  "Name: " + getName() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Phone number: " + getPhone();
    }

    // Searching
    public boolean isName(String arg) {
        return PIR.isContainString(getName(), arg);
    }
    public boolean isAddress(String arg) {
        return PIR.isContainString(getAddress(), arg);
    }
    public boolean isPhone(String arg) {
        return PIR.isContainString(getPhone(), arg);
    }
}