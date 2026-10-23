package gh.edu.bigboys.scanner;

import java.util.Map;

// AI-assistance disclosure: initial draft generated with ChatGPT; assigned contributor 236 should review the keyword list against the class language.

/** Exact, case-sensitive lookup for the provisional keyword set. */
public final class KeywordTable {
    private static final Map<String, TokenType> KEYWORDS = Map.ofEntries(
            Map.entry("if", TokenType.IF),
            Map.entry("else", TokenType.ELSE),
            Map.entry("while", TokenType.WHILE),
            Map.entry("for", TokenType.FOR),
            Map.entry("do", TokenType.DO),
            Map.entry("break", TokenType.BREAK),
            Map.entry("continue", TokenType.CONTINUE),
            Map.entry("return", TokenType.RETURN),
            Map.entry("int", TokenType.TYPE_INT),
            Map.entry("float", TokenType.TYPE_FLOAT),
            Map.entry("double", TokenType.TYPE_DOUBLE),
            Map.entry("bool", TokenType.TYPE_BOOL),
            Map.entry("char", TokenType.TYPE_CHAR),
            Map.entry("string", TokenType.TYPE_STRING),
            Map.entry("void", TokenType.TYPE_VOID),
            Map.entry("true", TokenType.TRUE),
            Map.entry("false", TokenType.FALSE),
            Map.entry("print", TokenType.PRINT),
            Map.entry("read", TokenType.READ),
            Map.entry("func", TokenType.FUNC),
            Map.entry("var", TokenType.VAR),
            Map.entry("const", TokenType.CONST),
            Map.entry("class", TokenType.CLASS),
            Map.entry("and", TokenType.AND),
            Map.entry("or", TokenType.OR),
            Map.entry("not", TokenType.NOT)
    );

    private KeywordTable() { }

    public static TokenType classify(String lexeme) {
        return KEYWORDS.getOrDefault(lexeme, TokenType.IDENTIFIER);
    }

    /** Return a literal value for boolean keywords, or null for other token kinds. */
    public static Object literalFor(TokenType type) {
        return switch (type) {
            case TRUE -> Boolean.TRUE;
            case FALSE -> Boolean.FALSE;
            default -> null;
        };
    }
}
