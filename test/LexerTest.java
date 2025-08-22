import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class LexerTest {

    private Token token;
    private String input;
    private Stack<Token> stack;

    @Before
    public void setup(){
        stack = new Stack<>();
        System.out.println("This is the Lexer Test setup");
    }

    @After
    public void teardown(){
        stack.clear();
        input = null;
        token = null;
        System.out.println("This is the teardown");
        System.out.println("========================");
    }

    @Test
    public void testLexingForSimpleGraph(){
        //Arrange
        input = "digraph{" +
                "a -> b;" +
                "c -> d;" +
                "e -> f;" +
                "g -> h;" +
                "i -> j;" +
                "k -> l;" +
                "m -> n;" +
                "p -> o;" +
                "q -> p;" +
                "r -> q;" +
                "s -> t;" +
                "u -> v;" +
                "w -> x;" +
                "y -> z; }";
        Lexer lexer = new Lexer(input);

        System.out.println("Test Lexing Simple Graph: \n");
        System.out.println(input + "\n");

        // Act
        do {
            token = lexer.nextToken();
            System.out.println("Lexed: " + token.toString());
            stack.push(token);
        } while (token.getTokenType() != Token.TokenType.EOF);

        // Assert
        assertEquals("Check for End Of File: ",Token.TokenType.EOF, stack.pop().getTokenType());
        System.out.println("Lexed: EOF\n");
        assertEquals("Check for RBRACE: ", Token.TokenType.RBRACE, stack.pop().getTokenType());
        System.out.println("Lexed: RBRACE\n");

        while(stack.peek().getTokenType() != Token.TokenType.LBRACE){
            //System.out.println(stack.peek().toString());
            assertEquals("Check for semicolon: ", Token.TokenType.SEMICOLON, stack.pop().getTokenType());
            System.out.println("Lexed: SEMICOLON\n");

            //System.out.println(stack.peek().toString());
            assertEquals("Check for 'to' ID: ", Token.TokenType.ID,        stack.pop().getTokenType());
            System.out.println("Lexed: ID: \n");

            //System.out.println(stack.peek().toString());
            assertEquals("Check for arrow", Token.TokenType.ARROW,     stack.pop().getTokenType());
            System.out.println("Lexed: ARROW\n");

            //System.out.println(stack.peek().toString());
            assertEquals("Check for 'from' ID: " ,Token.TokenType.ID,        stack.pop().getTokenType());
            System.out.println("Lexed: ID\n");
        }

        assertEquals("Check for LBRACE: ", Token.TokenType.LBRACE, stack.pop().getTokenType());
        System.out.println("Lexed: LBRACE\n");
        assertEquals("Check for digraph: ", Token.TokenType.DIGRAPH, stack.pop().getTokenType());
        System.out.println("Lexed: DIGRAPH\n");
    }

    @Test
    public void testStringEndOfFile(){
        // Arrange
        input = "";
        Lexer lexer = new Lexer(input);

        System.out.println("Test EOF, No Graph\n");

        // Act
        token = lexer.nextToken();
        stack.push(token);

        System.out.println("Lexed: EOF\n");

        // Assert
        assertEquals("Check for End Of File: ",Token.TokenType.EOF, stack.pop().getTokenType());
        System.out.println("EOF\n");
    }

    @Test
    public void testLexingBraces(){
        // Arrange
        input = "{ }";
        Lexer lexer = new Lexer(input);

        System.out.println("Test Lexing RBRACE and LBRACE\n");

        // Act
        do {
            token = lexer.nextToken();
            System.out.println("Lexed: " + token.toString());
            stack.push(token);
        } while (token.getTokenType() != Token.TokenType.EOF);


        // Assert
        assertEquals("Check for End Of File: ",Token.TokenType.EOF, stack.pop().getTokenType());
        System.out.println("Lexed: EOF\n");
        assertEquals("Check for RBRACE: ", Token.TokenType.RBRACE, stack.pop().getTokenType());
        System.out.println("Lexed: RBRACE\n");
        assertEquals("Check for LBRACE: ", Token.TokenType.LBRACE, stack.pop().getTokenType());
        System.out.println("Lexed: LBRACE\n");
    }

    @Test
    public void testLexingArrow(){
        // Arrange
        input = "->";
        Lexer lexer = new Lexer(input);
        System.out.println("Test Lexing ARROW");

        // Act
        do {
            token = lexer.nextToken();
            System.out.println("Lexed: " + token.toString());
            stack.push(token);
        } while (token.getTokenType() != Token.TokenType.EOF);


        // Assert
        assertEquals("Check for End Of File: ",Token.TokenType.EOF, stack.pop().getTokenType());
        System.out.println("Lexed: EOF\n");
        assertEquals("Check for Arrow: ", Token.TokenType.ARROW, stack.pop().getTokenType());
        System.out.println("Lexed: ARROW\n");
    }

    @Test
    public void testLexingID(){
        // Arrange
        input = "a123123 abc12323 123123 abcdef sdnasdl";
        Lexer lexer = new Lexer(input);

        System.out.println("Test Lexing ID");

        // Act
        do {
            token = lexer.nextToken();
            System.out.println("Lexed: " + token.toString());
            stack.push(token);
        } while (token.getTokenType() != Token.TokenType.EOF);


        // Assert
        assertEquals("Check for End Of File: ",Token.TokenType.EOF, stack.pop().getTokenType());
        System.out.println("Lexed: EOF\n");
        while(!stack.isEmpty()){
            assertEquals("Check for ID: ",Token.TokenType.ID, stack.peek().getTokenType());
            System.out.println("Lexed: ID: " + stack.pop().toString() + "\n");
        }
    }

    @Test
    public void testLexerLineNumTracker(){
        int expectedLineNum = 5;
        input = "a \n" +
                "a \n" +
                "a \n" +
                "a \n" +
                "a";
        Lexer lexer = new Lexer(input);

        System.out.println("Testing for Line Number Tracking\n");

        do {
            token = lexer.nextToken();
            System.out.println("Lexed: " + token.toString());
            stack.push(token);
        } while (token.getTokenType() != Token.TokenType.EOF);

        assertEquals("Check for Line Number: ", expectedLineNum, lexer.getLineNumber());
        System.out.println("Lexed: EOF\n");
        System.out.println("Expected: " + expectedLineNum + "\n");
        System.out.println("Outcome: " + lexer.getLineNumber() + "\n");
    }

    @Test
    public void testLexerException(){
        input = "%";
        Lexer lexer = new Lexer(input);

        System.out.println("Test Lexer Exception: \n");

        LexerException exception = assertThrows(LexerException.class, () -> {
            do{
                token = lexer.nextToken();
                stack.push(token);
            } while (token.getTokenType() != Token.TokenType.EOF);
        });

        System.out.println("Expect: Lexer Exception of % caught at Line 1\n");

        assertEquals("Unexpected character '%'-- at Line: 1", exception.getMessage());
        System.out.println(exception.getMessage());
    }

}
