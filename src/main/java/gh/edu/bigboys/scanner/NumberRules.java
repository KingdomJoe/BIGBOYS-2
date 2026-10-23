package gh.edu.bigboys.scanner;

import java.math.BigDecimal;

// AI-assistance disclosure: initial draft generated with ChatGPT; assigned contributor 234 should review and adapt numeric grammar.

/** Helpers for the provisional number grammar: digits, optional fraction and optional exponent. */
public final class NumberRules {
    private NumberRules() { }

    public record Match(int length, BigDecimal value, String error) { }

    public static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    /**
     * Match a number starting at {@code start}. The caller should advance by {@code length}.
     * A malformed exponent is consumed as one malformed numeric lexeme and returned as an error.
     */
    public static Match match(String source, int start) {
        int i = start;
        while (i < source.length() && isDigit(source.charAt(i))) i++;

        if (i + 1 < source.length() && source.charAt(i) == '.' && isDigit(source.charAt(i + 1))) {
            i++; // decimal point
            while (i < source.length() && isDigit(source.charAt(i))) i++;
        }

        if (i < source.length() && (source.charAt(i) == 'e' || source.charAt(i) == 'E')) {
            i++;
            if (i < source.length() && (source.charAt(i) == '+' || source.charAt(i) == '-')) i++;
            int digitsStart = i;
            while (i < source.length() && isDigit(source.charAt(i))) i++;
            if (digitsStart == i) {
                return new Match(Math.max(1, i - start), null,
                        "Malformed numeric literal: exponent requires at least one digit");
            }
        }

        String lexeme = source.substring(start, i);
        try {
            return new Match(i - start, new BigDecimal(lexeme), null);
        } catch (NumberFormatException ex) {
            return new Match(Math.max(1, i - start), null, "Malformed numeric literal");
        }
    }
}
