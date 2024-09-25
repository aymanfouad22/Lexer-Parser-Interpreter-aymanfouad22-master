public class Token {
    public String type;
    public String value;
    public int lineNumber; // Add this field
    public Token(String type, String value,int lineNumber) {
        this.type=type;
        this.value=value;
        this.lineNumber=lineNumber;
    }


    public String toString(){

        return type+" "+value+" "+lineNumber;
    }


}
