package gh.edu.bigboys.scanner;

// AI-assistance disclosure: initial draft generated with ChatGPT; assigned contributor 0228 should review and adapt.

/** Categories emitted by the Phase 1 scanner; reserved words remain provisional until checked against the class grammar. */
public enum TokenType {
    // Delimiters and punctuation
    LEFT_PAREN, RIGHT_PAREN,
    LEFT_BRACE, RIGHT_BRACE,
    LEFT_BRACKET, RIGHT_BRACKET,
    COMMA, DOT, SEMICOLON, COLON, QUESTION,

    // Arithmetic operations and compound assignments
    PLUS, MINUS, STAR, SLASH, PERCENT,
    PLUS_PLUS, MINUS_MINUS,
    PLUS_EQUAL, MINUS_EQUAL, STAR_EQUAL, SLASH_EQUAL, PERCENT_EQUAL,
    EQUAL, EQUAL_EQUAL,

    // Relational and logical operators
    BANG, BANG_EQUAL,
    GREATER, GREATER_EQUAL, LESS, LESS_EQUAL,
    AND_AND, OR_OR, AND, OR, NOT,

    // Bitwise operations and shift operators
    AMPERSAND, PIPE, CARET, TILDE,
    AMPERSAND_EQUAL, PIPE_EQUAL, CARET_EQUAL,
    SHIFT_LEFT, SHIFT_RIGHT, SHIFT_LEFT_EQUAL, SHIFT_RIGHT_EQUAL,

    // Arrow operators supported by the provisional grammar
    ARROW, FAT_ARROW,

    // Identifiers and literal values
    IDENTIFIER, NUMBER, STRING_LITERAL,

    // Provisional reserved words and type names
    IF, ELSE, WHILE, FOR, DO, BREAK, CONTINUE, RETURN,
    TYPE_INT, TYPE_FLOAT, TYPE_DOUBLE, TYPE_BOOL, TYPE_CHAR, TYPE_STRING, TYPE_VOID,
    TRUE, FALSE, PRINT, READ, FUNC, VAR, CONST, CLASS,

    EOF
}
