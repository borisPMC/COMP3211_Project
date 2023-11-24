import java.util.ArrayList;

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

    public String toString() {

        return  "Title: " + getFN() + "\n" +
                "Content: " + getContent();
    }

    // Searching
    public boolean isContent(String arg) {
        return PIR.isContainString(getContent(), arg);
    }

    // Checking for creating PIR
    public static boolean checkSameTitle(String content, ArrayList<Note> list) {
        for (int i = 0;i < list.size();i++) {
            if (content.equals(list.get(i).filename)) {
                return true;
            }
        }
        return false;
    }
}