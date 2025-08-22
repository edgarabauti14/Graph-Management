public class GraphParser {
    private final Lexer lexer;
    private Token token;
    private final GraphManager graph;

    // Constructor
    public GraphParser(Lexer lexer, GraphManager graphManager) {
        this.lexer = lexer;
        this.token = lexer.nextToken();
        this.graph = graphManager;
    }

    // Begin parsing graph
    public void beginParsingGraph() {
        if(token.getTokenType() != Token.TokenType.DIGRAPH){
            throw new GraphParserException("Expected DIGRAPH at line: " + lexer.getLineNumber());
        }
        token = lexer.nextToken();
        parseDigraph();
    }

    // Start by parsing digraph
    private void parseDigraph() {
        if(token.getTokenType() != Token.TokenType.LBRACE){
            throw new GraphParserException("Expected LBRACE at line: " + lexer.getLineNumber());
        }
        token = lexer.nextToken();

        parseStmtList();

        if(token.getTokenType() != Token.TokenType.RBRACE){
            throw new GraphParserException("Expected RBRACE at line: " + lexer.getLineNumber());
        }
    }

    // Move to the statement list
    private void parseStmtList() {
        parseStmt();

        if(token.getTokenType() == Token.TokenType.ID){
            parseStmtList();
        }
    }

    // Parse each individual statement
    private void parseStmt() {
        if(token.getTokenType() != Token.TokenType.ID){
            throw new GraphParserException("Expected ID at line: " + lexer.getLineNumber());
        }
        String from = token.getTokenValue();
        token = lexer.nextToken();

        if(token.getTokenType() == Token.TokenType.SEMICOLON){
            graph.addNode(from);
            token = lexer.nextToken();
            return;
        }

        if (token.getTokenType() != Token.TokenType.ARROW) {
            throw new GraphParserException("Expected ARROW at line: " + lexer.getLineNumber());
        }
        token = lexer.nextToken();

        if (token.getTokenType() != Token.TokenType.ID) {
            throw new GraphParserException("Expected ID at line: " + lexer.getLineNumber());
        }
        String to = token.getTokenValue();
        token = lexer.nextToken();

        if (token.getTokenType() != Token.TokenType.SEMICOLON) {
            throw new GraphParserException("Expected SEMICOLON at line: " + lexer.getLineNumber());
        }
        token = lexer.nextToken();

        // graph manager
        graph.addEdge(from, to);

    }
}
