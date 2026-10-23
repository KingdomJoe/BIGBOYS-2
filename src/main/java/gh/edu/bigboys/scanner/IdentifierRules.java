package gh.edu.bigboys.scanner;

// AI-assistance disclosure: initial draft generated with ChatGPT; assigned contributor 231 should review identifier rules against the class language.

/** Defines the provisional identifier grammar: a Unicode letter or underscore first, followed by letters, underscores, or ASCII digits. */
public final class IdentifierRules {
    private IdentifierRules() { }

    public static boolean isStart(char ch) {
        return ch == '_' || Character.isLetter(ch);
    }

    public static boolean isPart(char ch) {
        return isStart(ch) || isAsciiDigit(ch);
    }

    private static boolean isAsciiDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }
}
