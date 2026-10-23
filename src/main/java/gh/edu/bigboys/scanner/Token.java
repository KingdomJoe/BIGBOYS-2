package gh.edu.bigboys.scanner;

import java.math.BigDecimal;
import java.util.Locale;

// AI-assistance disclosure: initial draft generated with ChatGPT; assigned contributor 0228 should review and adapt.

/** Represents a token's category, original lexeme, optional literal value, and 1-based source location. */
public record Token(TokenType type, String lexeme, Object literal, int line, int column) {
    public Token {
        if (type == null) throw new IllegalArgumentException("type must not be null");
        if (lexeme == null) throw new IllegalArgumentException("lexeme must not be null");
        if (line < 1 || column < 1) throw new IllegalArgumentException("line and column are 1-based");
    }

    @Override
    public String toString() {
        String literalText = literal == null ? "" : " literal=" + formatLiteral(literal);
        return String.format(Locale.ROOT, "%4d:%-3d %-20s lexeme=%s%s",
                line, column, type, quote(lexeme), literalText);
    }

    private static String formatLiteral(Object value) {
        if (value instanceof String s) return quote(s);
        if (value instanceof BigDecimal number) return number.stripTrailingZeros().toPlainString();
        return String.valueOf(value);
    }

    static String quote(String value) {
        StringBuilder escaped = new StringBuilder("\"");
        for (int i = 0; i < value.length(); i++) {
            char ch = value.charAt(i);
            switch (ch) {
                case '\\' -> escaped.append("\\\\");
                case '"' -> escaped.append("\\\"");
                case '\n' -> escaped.append("\\n");
                case '\r' -> escaped.append("\\r");
                case '\t' -> escaped.append("\\t");
                case '\0' -> escaped.append("\\0");
                default -> escaped.append(ch);
            }
        }
        return escaped.append('"').toString();
    }
}
