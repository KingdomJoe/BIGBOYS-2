package gh.edu.bigboys.scanner;

import java.util.Map;

// AI-assistance disclosure: initial draft generated with ChatGPT; assigned contributor 230 should confirm the supported operators.

/** Longest-match recognition for operators and punctuation. */
public final class OperatorRules {
    private static final Map<String, TokenType> SYMBOLS = Map.ofEntries(
            Map.entry("(", TokenType.LEFT_PAREN), Map.entry(")", TokenType.RIGHT_PAREN),
            Map.entry("{", TokenType.LEFT_BRACE), Map.entry("}", TokenType.RIGHT_BRACE),
            Map.entry("[", TokenType.LEFT_BRACKET), Map.entry("]", TokenType.RIGHT_BRACKET),
            Map.entry(",", TokenType.COMMA), Map.entry(".", TokenType.DOT),
            Map.entry(";", TokenType.SEMICOLON), Map.entry(":", TokenType.COLON),
            Map.entry("?", TokenType.QUESTION),
            Map.entry("+", TokenType.PLUS), Map.entry("-", TokenType.MINUS),
            Map.entry("*", TokenType.STAR), Map.entry("/", TokenType.SLASH),
            Map.entry("%", TokenType.PERCENT),
            Map.entry("++", TokenType.PLUS_PLUS), Map.entry("--", TokenType.MINUS_MINUS),
            Map.entry("+=", TokenType.PLUS_EQUAL), Map.entry("-=", TokenType.MINUS_EQUAL),
            Map.entry("*=", TokenType.STAR_EQUAL), Map.entry("/=", TokenType.SLASH_EQUAL),
            Map.entry("%=", TokenType.PERCENT_EQUAL),
            Map.entry("=", TokenType.EQUAL), Map.entry("==", TokenType.EQUAL_EQUAL),
            Map.entry("!", TokenType.BANG), Map.entry("!=", TokenType.BANG_EQUAL),
            Map.entry(">", TokenType.GREATER), Map.entry(">=", TokenType.GREATER_EQUAL),
            Map.entry("<", TokenType.LESS), Map.entry("<=", TokenType.LESS_EQUAL),
            Map.entry("&&", TokenType.AND_AND), Map.entry("||", TokenType.OR_OR),
            Map.entry("&", TokenType.AMPERSAND), Map.entry("|", TokenType.PIPE),
            Map.entry("^", TokenType.CARET), Map.entry("~", TokenType.TILDE),
            Map.entry("&=", TokenType.AMPERSAND_EQUAL), Map.entry("|=", TokenType.PIPE_EQUAL),
            Map.entry("^=", TokenType.CARET_EQUAL),
            Map.entry("<<", TokenType.SHIFT_LEFT), Map.entry(">>", TokenType.SHIFT_RIGHT),
            Map.entry("<<=", TokenType.SHIFT_LEFT_EQUAL), Map.entry(">>=", TokenType.SHIFT_RIGHT_EQUAL),
            Map.entry("->", TokenType.ARROW), Map.entry("=>", TokenType.FAT_ARROW)
    );

    private OperatorRules() { }

    public record Match(TokenType type, int length) { }

    public static Match match(String source, int start) {
        // Three-character forms must win over two- and one-character forms.
        for (int length = Math.min(3, source.length() - start); length >= 1; length--) {
            String candidate = source.substring(start, start + length);
            TokenType type = SYMBOLS.get(candidate);
            if (type != null) return new Match(type, length);
        }
        return null;
    }
}
