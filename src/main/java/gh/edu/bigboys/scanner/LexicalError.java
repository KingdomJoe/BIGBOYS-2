package gh.edu.bigboys.scanner;

// AI-assistance disclosure: initial draft generated with ChatGPT; assigned contributor 232 should review and adapt.

/** Records a recoverable lexical error, its message, and the 1-based source position where it was detected. */
public record LexicalError(int line, int column, String message, String lexeme) {
    public LexicalError {
        if (line < 1 || column < 1) throw new IllegalArgumentException("line and column are 1-based");
        if (message == null || message.isBlank()) throw new IllegalArgumentException("message must not be blank");
        if (lexeme == null) lexeme = "";
    }

    @Override
    public String toString() {
        return line + ":" + column + ": error: " + message
                + (lexeme.isEmpty() ? "" : " near " + Token.quote(lexeme));
    }
}
