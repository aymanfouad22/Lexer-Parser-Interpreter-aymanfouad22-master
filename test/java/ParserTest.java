import java.io.IOException;

/**
 * Create a for loop to test Parser result
 */
public class ParserTest {

    public static void main(String[] args) throws IOException {
        String[] fileArray = {
                "test.txt",
                "testExpectingId2.txt",
                "testExpectingAssignOp.txt",
                "testExpectingIdOrInt2.txt",
                "testMultiplePlus.txt",
                "testWhiteSpace.txt"
        };

        for (String file : fileArray) {
            System.out.println("Processing file: " + file);
            Lexer lexer = new Lexer(file);
            Parser parser = new Parser(lexer);
            Intrepreter intrepreter = new Intrepreter();
            System.out.println("Parser Output: ");
            try {
                parser.parseProgram();
                intrepreter.intrepreterProgram(parser.getBytecode());
                System.out.println("Parsing and interpretation completed successfully.");
            } catch (RuntimeException e) {
                System.err.println("Parsing failed: " + e.getMessage());
            }

            System.out.println("Parser State: " + parser);
            intrepreter.toString();

            System.out.println("-----------------------------------------------------------------------------------------");
        }
    }
}
