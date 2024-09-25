import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Parser {
    //TODO implement Parser class here
   private static ArrayList<Token> tokens = new ArrayList<>();
   private final idTable idTable;
    int index;
    public Parser(Lexer lexer) throws IOException {
   this.tokens = lexer.getAllTokens();
   this.idTable = new idTable();
   this.index = 0;
    }
    public void parseProgram(){
        while (index<tokens.size()){
            parseAssignment();
            if(tokens.get(index).type.equals(Lexer.EOFTOKEN)){
                System.out.println("valid program");
                break;
            }
        }

    }
  public void parseAssignment(){

        parseId();
        parseAssignOp();
      parseExpression();

    }

    public void parseAssignOp(){
        System.out.println("Current Token: " + tokens.get(index));
   if (!Objects.equals(tokens.get(index).type, Lexer.ASSMTTOKEN)){
       error("Expecting assignment operator");
   }
        nextToken();
    }
   public void parseId(){
       System.out.println("Current Token: " + tokens.get(index));
       if(!Objects.equals(tokens.get(index).type, Lexer.IDTOKEN)){
           error("Expecting identifier");
       }
       idTable.add(tokens.get(index).value);
       nextToken();
        }
        //got to be an id or int and then loop to see if there + or id or int
  public void parseExpression(){
      parseTerm();
      while(index < tokens.size() && Objects.equals(tokens.get(index).type, Lexer.PLUSTOKEN)){
           nextToken();
           System.out.println("Current Token: " + tokens.get(index));
           parseTerm();
       }
  }
    public void parseTerm() {
        if (index < tokens.size() &&
                (Objects.equals(tokens.get(index).type, Lexer.INTTOKEN) ||
                        Objects.equals(tokens.get(index).type, Lexer.IDTOKEN))) {
            nextToken(); // Valid Term, move to the next token
        } else {
            error("Expecting Identifier or Integer");
        }
    }
    public void error(String message){
        throw new RuntimeException(message+" On line "+getLineNumber()) ;
    }
    private Token nextToken() {
        if (index < tokens.size()) {
            return tokens.get(index++);
        }else return null;
    }
    public String getLineNumber(){
        if (index < tokens.size()) {
            return String.valueOf(tokens.get(index).lineNumber);
        }
        return "unknown"; // Return a default value if index is out of bounds
    }
    public String toString() {
        return "Tokens: " + tokens.toString() + "\nIdentifiers: " + idTable.toString();
    }
}
