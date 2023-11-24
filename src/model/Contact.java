package model;

import java.util.ArrayList;

public class Contact extends PIR {

    private String name;
    private String address;
    private String phone;

    public Contact(String name, String address, String phone) {
        setFN(name);
        setName(name);
        setAddress(address);
        setPhone(phone);
    }

    // Setter
    public void setName(String arg) {this.name = arg;}
    public void setAddress(String arg) {this.address = arg;}
    public void setPhone(String arg) {this.phone = arg;}
    // Getter
    public String getName() {return this.name;}
    public String getAddress() {return this.address;}
    public String getPhone() {return this.phone;}

    public String toString() {

        return  "Name: " + getName() + "\n" +
                "Address: " + getAddress() + "\n" +
                "Phone number: " + getPhone();
    }

    // Searching
    public boolean isName(String arg) {
        return isContainString(getName(), arg);
    }
    public boolean isAddress(String arg) {
        return isContainString(getAddress(), arg);
    }
    public boolean isPhone(String arg) {
        return isContainString(getPhone(), arg);
    }

    // Checking for creating PIR
    public static boolean checkSameTitle(String content, ArrayList<Contact> list) {
        for (int i = 0;i < list.size();i++) {
            if (content.equals(list.get(i).filename)) {
                return true;
            }
        }
        return false;
    }
}