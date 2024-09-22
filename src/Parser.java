import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Parser {
    //TODO implement Parser class here
   private static ArrayList<Token> tokens = new ArrayList<>();
   private final idTable idTable;
   private static Lexer lexer;
    int index;
    public Parser(Lexer lexer) throws IOException {
   this.tokens = lexer.getAllTokens();
   this.idTable = new idTable();
   this.index = 0;
    }
  public void parseProgram(){
        parseId();
        parseExpression();
        parseAssignOp();
  }
    public void parseAssignOp(){
   if (!Objects.equals(tokens.get(index).type, Lexer.ASSMTTOKEN)){
       error("Expecting assignment operator");
   }
    }
   public void parseId(){
       if(!Objects.equals(tokens.get(index).type, Lexer.IDTOKEN)){
           error("Expecting identifier");
       }
       idTable.add(tokens.get(index).value);
        }
  public void parseExpression(){
       while(index < tokens.size() && Objects.equals(tokens.get(index).type, Lexer.PLUSTOKEN)){
           nextToken();
           if(Objects.equals(tokens.get(index).type, Lexer.IDTOKEN)){
               if (idTable.getAdresse(tokens.get(index).value)==-1){
                   error("Identifier not defined: "+tokens.get(index).value);
               }
           }else if (Objects.equals(tokens.get(index).type, Lexer.INTTOKEN)){

           }else {error("Expecting Identifier or Integer");}
       }
  }
    public void error(String message){
        throw new RuntimeException(message+" On line ") ;
    }
    private Token nextToken() {
        if (index < tokens.size()) {
            return tokens.get(index++);
        }else return null;
    }
    public String getLineNumber(){
        return "";
    }
}
