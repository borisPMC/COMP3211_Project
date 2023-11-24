import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

abstract class PIR {
    
    protected String filename;

    public void setFN(String filename) {
        this.filename = filename;
    }
    public String getFN() {
        return this.filename;
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
    abstract public String toString();
}