// Token class to define the Tokens
//  needed for the parser
public class Token {
    public enum TokenType {
        DIGRAPH, ID, ARROW,
        SEMICOLON, LBRACE, RBRACE,
        EOF
    }

    private final TokenType tokenType;
    private final String tokenValue;

    // Constructor
    public Token(TokenType tokenType, String tokenValue) {
        this.tokenType = tokenType;
        this.tokenValue = tokenValue;
    }

    // Gets type
    public TokenType getTokenType() {
        return tokenType;
    }

    // Gets value of token
    public String getTokenValue() {
        return tokenValue;
    }

    // Token to string
    public String toString() {
        return "Token Type: " + tokenType + ", Token Value: " + tokenValue + "\n";
    }
}
