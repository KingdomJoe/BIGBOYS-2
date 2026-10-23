package gh.edu.bigboys.scanner;

import java.util.ArrayList;
import java.util.List;

// AI-assistance disclosure: initial integration and scanning flow drafted with ChatGPT; index 236 owns Stage 5 Lexer + CLI integration and should review/adapt this draft against the lecturer's current policy and language specification.

/** Converts source text into tokens and records lexical diagnostics without stopping at recoverable errors. */
public final class Lexer {
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private final List<LexicalError> errors = new ArrayList<>();

    // Track the current source offset and the start of the lexeme being scanned.
    private int start = 0;
    private int current = 0;
    // Source coordinates are 1-based; token coordinates are captured before scanning each lexeme.
    private int line = 1;
    private int column = 1;
    private int tokenLine = 1;
    private int tokenColumn = 1;
    private boolean scanned = false;

    public Lexer(String source) {
        this.source = source == null ? "" : source;
    }

    /** Scans the source once and appends an EOF token at the final source position. */
    public ScanResult scanTokens() {
        if (scanned) throw new IllegalStateException("A Lexer instance can scan its source only once");
        scanned = true;

        while (!isAtEnd()) {
            start = current;
            tokenLine = line;
            tokenColumn = column;
            scanToken();
        }
        tokens.add(new Token(TokenType.EOF, "", null, line, column));
        return new ScanResult(tokens, errors);
    }

    private void scanToken() {
        char ch = advance();

        switch (ch) {
            case ' ', '\r', '\t', '\n' -> { return; }
            case '/' -> {
                if (match('/')) {
                    while (!isAtEnd() && peek() != '\n') advance();
                    return;
                }
                if (match('*')) {
                    scanBlockComment();
                    return;
                }
            }
            case '"' -> {
                scanString();
                return;
            }
            default -> { }
        }

        if (IdentifierRules.isStart(ch)) {
            scanIdentifier();
            return;
        }
        if (NumberRules.isDigit(ch)) {
            scanNumber();
            return;
        }

        OperatorRules.Match symbol = OperatorRules.match(source, start);
        if (symbol == null) {
            addError("Unexpected character", source.substring(start, current));
            return;
        }
        consumeUntil(start + symbol.length());
        addToken(symbol.type(), null);
    }

    private void scanIdentifier() {
        while (!isAtEnd() && IdentifierRules.isPart(peek())) advance();
        String lexeme = source.substring(start, current);
        TokenType type = KeywordTable.classify(lexeme);
        addToken(type, KeywordTable.literalFor(type));
    }

    private void scanNumber() {
        NumberRules.Match match = NumberRules.match(source, start);
        consumeUntil(start + match.length());
        String lexeme = source.substring(start, current);
        if (match.error() != null) {
            addError(match.error(), lexeme);
        } else {
            addToken(TokenType.NUMBER, match.value());
        }
    }

    private void scanString() {
        StringRules.Match match = StringRules.match(source, start);
        consumeUntil(start + match.length());
        String lexeme = source.substring(start, current);
        if (match.error() != null) {
            addError(match.error(), lexeme);
        } else {
            addToken(TokenType.STRING_LITERAL, match.value());
        }
    }

    private void scanBlockComment() {
        while (!isAtEnd()) {
            if (peek() == '*' && peekNext() == '/') {
                advance();
                advance();
                return;
            }
            advance();
        }
        addError("Unterminated block comment: missing */", source.substring(start, current));
    }

    /** Consumes one character and keeps the line and column counters synchronised. */
    private char advance() {
        char ch = source.charAt(current++);
        if (ch == '\n') {
            line++;
            column = 1;
        } else {
            column++;
        }
        return ch;
    }

    private boolean match(char expected) {
        if (isAtEnd() || source.charAt(current) != expected) return false;
        advance();
        return true;
    }

    private char peek() {
        return isAtEnd() ? '\0' : source.charAt(current);
    }

    private char peekNext() {
        return current + 1 >= source.length() ? '\0' : source.charAt(current + 1);
    }

    private boolean isAtEnd() {
        return current >= source.length();
    }

    /** Advances to {@code targetOffset} while updating the source coordinates. */
    private void consumeUntil(int targetOffset) {
        while (current < targetOffset && !isAtEnd()) advance();
    }

    /** Emits a token using the original lexeme and its captured starting position. */
    private void addToken(TokenType type, Object literal) {
        tokens.add(new Token(type, source.substring(start, current), literal, tokenLine, tokenColumn));
    }

    /** Associates a diagnostic with the starting position of the current lexeme. */
    private void addError(String message, String lexeme) {
        errors.add(new LexicalError(tokenLine, tokenColumn, message, lexeme));
    }
}
