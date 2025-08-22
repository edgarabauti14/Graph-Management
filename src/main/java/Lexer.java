public class Lexer {
    private final String input;
    private int index;
    private int lineNumber;

    // Constructor
    public Lexer(String input) {
        this.input = input;
        this.index = 0;
        this.lineNumber = 1;
    }

    // Get next Token
    public Token nextToken() {
        char c;

        // Skip whitespaces and if its a new line add to index
        while(index < input.length() && Character.isWhitespace(input.charAt(index))) {
            if(input.charAt(index) == '\n'){
                lineNumber++;
            }
            index++;
        }

        Token currentToken;
        if (index >= input.length()) {
            currentToken = new Token(Token.TokenType.EOF, "EOF");
            return currentToken;
        }

        c = input.charAt(index);
        while (Character.isWhitespace(c)) {
            index++;
            if (index >= input.length()) {
                currentToken = new Token(Token.TokenType.EOF, "EOF");
                return currentToken;
            }
            c = input.charAt(index);
        }

        // Read digraph
        if (input.startsWith("digraph", index)) {
            index += 7;
            currentToken = new Token(Token.TokenType.DIGRAPH, "digraph");
            return currentToken;
        }

        // rEad LBRACE
        if (c == '{') {
            index++;

            currentToken = new Token(Token.TokenType.LBRACE, "{");
            return currentToken;
        }

        // Read RBRACE
        if (c == '}') {
            index++;

            currentToken = new Token(Token.TokenType.RBRACE, "}");
            return currentToken;
        }

        // Add to index if new line
        if(c == '\n'){
            lineNumber++;
        }

        // Read semicolon
        if (c == ';') {
            index++;

            currentToken = new Token(Token.TokenType.SEMICOLON, ";");
            return currentToken;
        }

        // Read arrow
        if(input.startsWith("->", index)) {
            index += 2;

            currentToken = new Token(Token.TokenType.ARROW, "->");
            return currentToken;
        }

        // Read ID
        if(Character.isLetterOrDigit(c)) {
            StringBuilder id = new StringBuilder();

            while (Character.isLetterOrDigit(c)) {
                id.append(c);
                index++;

                if (index >= input.length()) break;
                c = input.charAt(index);
            }

            currentToken = new Token(Token.TokenType.ID, id.toString());
            return currentToken;
        }

        throw new LexerException("Unexpected character '" + c + "'-- at Line: " + lineNumber);

    }

    // get Line number
    public int getLineNumber() {
        return lineNumber;
    }
}
