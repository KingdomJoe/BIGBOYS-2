package gh.edu.bigboys.scanner;

import java.util.List;

// AI-assistance disclosure: initial draft generated with ChatGPT; assigned contributor 232 should review and adapt.

/** Immutable snapshot of the tokens emitted by one scan and the recoverable errors recorded during it. */
public record ScanResult(List<Token> tokens, List<LexicalError> errors) {
    public ScanResult {
        tokens = List.copyOf(tokens);
        errors = List.copyOf(errors);
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }
}
