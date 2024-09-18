import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Class to build an array of Tokens from an input file
 * @author wolberd
 * @see Token
 * @see Parser
 */
public class Lexer {

    String buffer;
    int index = 0;
    public static final String INTTOKEN="INT";
    public static final String IDTOKEN="ID";
    public static final String ASSMTTOKEN="ASSMT";
    public static final String PLUSTOKEN="PLUS";
    public static final String EOFTOKEN="EOF";
    Token token = new Token(" "," ");
    public String fileName;
    /**
     * call getInput to get the file data into our buffer
     * @param fileName the file we open
     */
    public Lexer(String fileName) {;
        getInput(fileName);
    }

    /**
     * Reads given file into the data member buffer
     * @param fileName name of file to parse
    */
    private void getInput(String fileName)  {
        try {
            Path filePath = Paths.get(fileName);
            byte[] allBytes = Files.readAllBytes(filePath);
            buffer = new String (allBytes);
        } catch (IOException e) {
            System.out.println ("You did not enter a valid file name in the run arguments.");
            System.out.println ("Please enter a string to be parsed:");
            Scanner scanner = new Scanner(System.in);
            buffer=scanner.nextLine();
        }
    }
    //Detects the identifier token and returns it in the token object
private Token getIdentifier(){
            StringBuilder value = new StringBuilder();
    while (index < buffer.length() && Character.isLetterOrDigit(buffer.charAt(index))) {
        value.append(buffer.charAt(index));
        index++;
    }
   return token = new Token(IDTOKEN,value.toString());
}
//Detects the integer token and returns it
private  Token getInteger(){
        StringBuilder value = new StringBuilder();
    while (index < buffer.length() && Character.isDigit(buffer.charAt(index))) {
        value.append(buffer.charAt(index));
        index++;
    }
   return  token = new Token(INTTOKEN,value.toString());
}
    /**
     * Return all the token in the file
     * @return ArrayList of Token
     */
//Returns the next token from the buffer
public Token getNextToken() {
    StringBuilder value = new StringBuilder();
   char c = buffer.charAt(index);
    if (Character.isWhitespace(c)) {
        index++;
    }
    else if (Character.isLetter(c)) {
        value.setLength(0);
      this.getIdentifier();
    }
   else if (Character.isDigit(c)) {
        value.setLength(0);
        this.getInteger();
    }
   else if (c == '=') {
        token = new Token(ASSMTTOKEN,"=");
        index++;
    }
    else if (c == '+') {
        token = new Token(PLUSTOKEN,"+");
        index++;
    }
   else{ token= new Token(EOFTOKEN,"") ;// Add EOF token at the end
    index++;
   }
    return token;
}
    //calls getsNextToken and store all the tokens in an array
    public ArrayList<Token> getAllTokens(String fileName) throws IOException {
        //TODO: place your code here for lexing file
        ArrayList<Token> tokens = new ArrayList<>(23);
        while (index <buffer.length() ) {
            tokens.add(getNextToken());
        }
        return tokens; // don't forget to change the return statement
    }




    /**
     * Before your run this starter code
     * Select Run | Edit Configurations from the main menu.
     * In Program arguments add the name of file you want to test (e.g., test.txt)
     * @param args args[0]
     */
    public static void main(String[] args) throws IOException {
        String fileName="";
        if (args.length==0) {
            System.out.println("You can test a different file by adding as an argument");
            System.out.println("See comment above main");
            System.out.println("For this run, test.txt used");
            fileName="test.txt";
        } else {

            fileName=args[0];
        }
      ArrayList<Token> tokens = new ArrayList<>();
        Lexer lexer = new Lexer("test.txt");
        tokens = lexer.getAllTokens("test.txt");
        // just print out the text from the file
        for (Token token : tokens) {

                System.out.println(token);

        }
        System.out.println(lexer.buffer);
        // here is where you'll call getAllTokens

    }
}

	