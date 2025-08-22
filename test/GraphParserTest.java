import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GraphParserTest {

    private Lexer lexer;
    private GraphManager graphManager;
    private GraphParser graphParser;
    private String input;

    @Before
    public void setUp(){
        graphManager = new GraphManager();
        System.out.println("This is the Graph Parser setup");
    }

    @After
    public void teardown(){
        graphManager = null;
        lexer = null;
        graphParser = null;
        System.out.println("This is the teardown");
        System.out.println("===========================");
    }

    @Test
    public void testValidGraph(){
        // Arrange

        System.out.println("Test Parse Simple Graph: \n");

        input = "digraph { a -> b; b -> c; c -> d; }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println(input + "\n");

        // Act
        graphParser.beginParsingGraph();
        System.out.println("Expected all edges to be added: \n");

        // Assert
        assertTrue("Test if edge was added: ", graphManager.hasEdge("a","b"));
        assertTrue("Test if edge was added: ", graphManager.hasEdge("b","c"));
        assertTrue("Test if edge was added: ", graphManager.hasEdge("c","d"));

        assertEquals("Test count of edges", 3, graphManager.getEdgeCount());

        System.out.println("Outcome: ");
        System.out.println(graphManager.toString());

    }

    @Test
    public void testGraphParserException(){
        // Arrange
        input = "digraph { -> -> a; }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println("Test Graph Parser Syntax Error: \n");
        System.out.println(input + "\n");

        // Act
        GraphParserException exception = assertThrows(GraphParserException.class, () -> {
            graphParser.beginParsingGraph();
        });
        System.out.println("Outcome: Expected ID at line 1.\n");

        // Assert
        assertEquals("Expected ID at line: 1", exception.getMessage());
        System.out.println(exception.getMessage());

    }


    @Test
    public void testMissingSemicolon(){
        // Arrange
        input = "digraph { a -> b }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println("Test Missing Semicolon: \n");
        System.out.println(input + "\n");

        // Act
        GraphParserException exception = assertThrows(GraphParserException.class, () -> {
            graphParser.beginParsingGraph();
        });
        System.out.println("Outcome: Expected ID at line 1.\n");

        // Assert
        assertEquals("Expected SEMICOLON at line: 1", exception.getMessage());
        System.out.println(exception.getMessage());
    }

    @Test
    public void testMissingDigraph(){
        // Arrange
        input = " { a -> b }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println("Test Missing digraph: \n");
        System.out.println(input + "\n");

        // Act
        GraphParserException exception = assertThrows(GraphParserException.class, () -> {
            graphParser.beginParsingGraph();
        });

        System.out.println("Outcome: Expected DIGRAPH at line 1.\n");

        // Assert
        assertEquals("Expected DIGRAPH at line: 1", exception.getMessage());
        System.out.println(exception.getMessage());
    }

    @Test
    public void testMissingLBrace(){
        // Arrange
        input = "digraph  a -> b }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println("Test Missing LBRACE: \n");
        System.out.println(input + "\n");

        // Act
        GraphParserException exception = assertThrows(GraphParserException.class, () -> {
            graphParser.beginParsingGraph();
        });

        System.out.println("Outcome: Expected LBRACE at line 1.\n");

        // Assert
        assertEquals("Expected LBRACE at line: 1", exception.getMessage());
        System.out.println(exception.getMessage());
    }

    @Test
    public void testSingleNodeParsing(){
        // Arrange
        input = "digraph { a; b; c; d; e; }";
        lexer = new Lexer(input);
        graphParser = new GraphParser(lexer, graphManager);

        System.out.println("Test Single Node Parsing: \n");
        System.out.println(input + "\n");

        // Act
        graphParser.beginParsingGraph();

        System.out.println("Expected all single nodes to be added: \n");

        // Assert
        assertTrue("Test if node was parsed: ", graphManager.hasNode("a"));
        assertTrue("Test if node was parsed: ", graphManager.hasNode("b"));
        assertTrue("Test if node was parsed: ", graphManager.hasNode("c"));
        assertTrue("Test if node was parsed: ", graphManager.hasNode("d"));
        assertTrue("Test if node was parsed: ", graphManager.hasNode("e"));

        System.out.println("Outcome: ");
        System.out.println(graphManager.toString());
    }
}
