package gh.edu.bigboys.scanner;

// AI-assistance disclosure: initial draft generated with ChatGPT; assigned contributor 227 should review string escape rules.

/** Matching and decoding rules for quoted string literals. */
public final class StringRules {
    private StringRules() { }

    /** Length counts source characters from the opening quote, including a closing quote if present. */
    public record Match(int length, String value, String error) { }

    public static Match match(String source, int start) {
        StringBuilder value = new StringBuilder();
        String firstError = null;
        int i = start + 1; // opening quote

        while (i < source.length()) {
            char ch = source.charAt(i);
            if (ch == '"') {
                return new Match(i - start + 1, value.toString(), firstError);
            }
            if (ch == '\n' || ch == '\r') {
                return new Match(i - start, null,
                        firstError != null ? firstError : "Unterminated string literal: newline before closing quote");
            }
            if (ch == '\\') {
                if (i + 1 >= source.length()) {
                    return new Match(source.length() - start, null,
                            firstError != null ? firstError : "Unterminated string literal: escape sequence at end of input");
                }
                char escaped = source.charAt(i + 1);
                switch (escaped) {
                    case '\\' -> value.append('\\');
                    case '"' -> value.append('"');
                    case 'n' -> value.append('\n');
                    case 'r' -> value.append('\r');
                    case 't' -> value.append('\t');
                    case '0' -> value.append('\0');
                    default -> {
                        if (firstError == null) firstError = "Unknown escape sequence: \\" + escaped;
                        value.append(escaped);
                    }
                }
                i += 2;
                continue;
            }
            value.append(ch);
            i++;
        }

        return new Match(source.length() - start, null,
                firstError != null ? firstError : "Unterminated string literal: missing closing quote");
    }
}
