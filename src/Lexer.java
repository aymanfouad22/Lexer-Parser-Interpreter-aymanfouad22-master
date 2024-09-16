import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
    public String fileName;
    /**
     * call getInput to get the file data into our buffer
     * @param fileName the file we open
     */
    public Lexer(String fileName) {
      this.fileName = fileName;
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

    /**
     * Return all the token in the file
     * @return ArrayList of Token
     */

    public ArrayList<Token> getAllTokens(String fileName) throws IOException {
        this.fileName = fileName;
        //TODO: place your code here for lexing file
        ArrayList<Token> tokens = new ArrayList<>();
        StringBuilder value = new StringBuilder();
        Lexer obj = new Lexer(fileName);
       String buffer = obj.buffer;
        while (index < buffer.length()) {
            char c = buffer.charAt(index);
            if(Character.isWhitespace(c)){
                index++;
                continue;
            }
          if(Character.isLetter(c)){
              value.setLength(0);
              while (index < buffer.length() && Character.isLetterOrDigit(buffer.charAt(index))) {
                  value.append(c);
                  index++;
              }
              tokens.add(new Token(value.toString(),IDTOKEN));
              continue;
          }
          if(Character.isDigit(c)){
              value.setLength(0);
              while (index < buffer.length() && Character.isDigit(buffer.charAt(index))) {
                  value.append(c);
                  index++;
              }
              tokens.add(new Token(value.toString(),INTTOKEN));
              continue;
          }
          if(c == '='){
              tokens.add(new Token("=",ASSMTTOKEN));
              index++;
              continue;
          }
            if(c == '+'){
                tokens.add(new Token("+",PLUSTOKEN));
                index++;
                continue;
            }

        }
        tokens.add(new Token("", EOFTOKEN));  // Add EOF token at the end
        index++;
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
        Lexer lexer = new Lexer("test.txt");
        // just print out the text from the file
        lexer.getAllTokens("test.txt");
        System.out.println(lexer.getAllTokens("test.txt").get(0));
        System.out.println(lexer.buffer);
        // here is where you'll call getAllTokens

    }
}
	