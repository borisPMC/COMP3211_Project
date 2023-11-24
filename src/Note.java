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
}