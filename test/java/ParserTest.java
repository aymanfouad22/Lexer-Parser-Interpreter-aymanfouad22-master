import java.io.IOException;

/**
 * Create a for loop to test Parser result
 * Find expected result here:  https://docs.google.com/document/d/1Nfx15sYNPsPS3PS-qK8fjQ8VvEozeijMfCECXlYZx2o/edit#heading=h.d51f4t5t6g61
 */
public class ParserTest {

    public static void main(String[] args) throws IOException {
        String[] fileArray={"test.txt","testExpectingId2.txt","testExpectingAssignOp.txt","testExpectingIdOrInt2.txt","testMultiplePlus.txt","testWhiteSpace.txt"};

        for (String file : fileArray) {
            System.out.println(file);
            Lexer lexer =new Lexer(file);
   Parser parser = new Parser(lexer);
            for (Token token : lexer.getAllTokens()) {
                System.out.println(token);
            }
            System.out.println("Parser Output: ");
            try {
                parser.parseProgram();
                System.out.println("Parsing completed successfully.");
            } catch (RuntimeException e) {
                System.err.println("Parsing failed: " + e.getMessage());
            }
   System.out.println(parser);
            System.out.println("-----------------------------------------------------------------------------------------");

        }
    }
}
