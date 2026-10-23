package gh.edu.bigboys.scanner;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// AI-assistance disclosure: initial test draft generated with ChatGPT; index 233 should extend and verify against the class mini-language.
class LexerTest {
    private ScanResult scan(String source) {
        return new Lexer(source).scanTokens();
    }

    private List<TokenType> types(ScanResult result) {
        return result.tokens().stream().map(Token::type).toList();
    }

    @Test
    void recognisesKeywordsAndIdentifiersExactly() {
        ScanResult result = scan("if iffy _name item2 return true false");

        assertEquals(List.of(TokenType.IF, TokenType.IDENTIFIER, TokenType.IDENTIFIER, TokenType.IDENTIFIER,
                TokenType.RETURN, TokenType.TRUE, TokenType.FALSE, TokenType.EOF), types(result));
        assertFalse(result.hasErrors());
        assertEquals(Boolean.TRUE, result.tokens().get(5).literal());
        assertEquals(Boolean.FALSE, result.tokens().get(6).literal());
    }

    @Test
    void recognisesIntegerDecimalAndExponentNumbers() {
        ScanResult result = scan("0 42 3.14 6.02e23 5E-2");

        assertEquals(List.of(TokenType.NUMBER, TokenType.NUMBER, TokenType.NUMBER,
                TokenType.NUMBER, TokenType.NUMBER, TokenType.EOF), types(result));
        assertFalse(result.hasErrors());
        assertEquals("3.14", result.tokens().get(2).literal().toString());
    }

    @Test
    void choosesLongestOperatorMatch() {
        ScanResult result = scan("x++ >= 2 && y != 0 || !ok <<= 1");

        assertEquals(List.of(TokenType.IDENTIFIER, TokenType.PLUS_PLUS, TokenType.GREATER_EQUAL,
                TokenType.NUMBER, TokenType.AND_AND, TokenType.IDENTIFIER, TokenType.BANG_EQUAL,
                TokenType.NUMBER, TokenType.OR_OR, TokenType.BANG, TokenType.IDENTIFIER,
                TokenType.SHIFT_LEFT_EQUAL, TokenType.NUMBER, TokenType.EOF), types(result));
        assertFalse(result.hasErrors());
    }

    @Test
    void decodesSupportedStringEscapes() {
        ScanResult result = scan("print(\"line1\\nline2 and \\\"quoted\\\"\");");

        assertFalse(result.hasErrors());
        assertEquals(TokenType.STRING_LITERAL, result.tokens().get(2).type());
        assertEquals("line1\nline2 and \"quoted\"", result.tokens().get(2).literal());
    }

    @Test
    void ignoresCommentsAndTracksLineNumbers() {
        ScanResult result = scan("int x = 1; // line comment\n/* block\ncomment */ print(x);");

        assertFalse(result.hasErrors());
        Token print = result.tokens().stream().filter(t -> t.type() == TokenType.PRINT).findFirst().orElseThrow();
        assertEquals(3, print.line());
    }

    @Test
    void recordsUnknownCharactersAndContinuesScanning() {
        ScanResult result = scan("before @ after");

        assertEquals(List.of(TokenType.IDENTIFIER, TokenType.IDENTIFIER, TokenType.EOF), types(result));
        assertEquals(1, result.errors().size());
        assertTrue(result.errors().get(0).message().contains("Unexpected character"));
        assertEquals(1, result.errors().get(0).line());
    }

    @Test
    void reportsUnterminatedStringsAndInvalidEscapes() {
        ScanResult unterminated = scan("print(\"missing);\n");
        assertTrue(unterminated.hasErrors());
        assertTrue(unterminated.errors().get(0).message().contains("Unterminated string literal"));

        ScanResult badEscape = scan("\"bad\\q\"");
        assertTrue(badEscape.hasErrors());
        assertTrue(badEscape.errors().get(0).message().contains("Unknown escape sequence"));
    }

    @Test
    void reportsMalformedExponentAndUnterminatedBlockComment() {
        ScanResult malformedNumber = scan("12e+");
        assertTrue(malformedNumber.hasErrors());
        assertTrue(malformedNumber.errors().get(0).message().contains("exponent"));

        ScanResult comment = scan("/* never closed");
        assertTrue(comment.hasErrors());
        assertTrue(comment.errors().get(0).message().contains("Unterminated block comment"));
    }

    @Test
    void reportsEofPositionForEmptyInput() {
        ScanResult result = scan("");
        assertEquals(1, result.tokens().size());
        assertEquals(new Token(TokenType.EOF, "", null, 1, 1), result.tokens().get(0));
    }
}
