package gh.edu.bigboys.scanner;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

// AI-assistance disclosure: initial CLI draft generated with ChatGPT; index 236 owns Stage 5 Lexer + CLI integration and should review/adapt usage and diagnostics.

/** Command-line entry point for the scanner. */
public final class Main {
    private Main() { }

    public static void main(String[] args) {
        int status = run(args, System.in, System.out, System.err);
        if (status != 0) System.exit(status);
    }

    static int run(String[] args, InputStream input, PrintStream output, PrintStream error) {
        if (args.length > 1 || (args.length == 1 && args[0].equals("--help"))) {
            printUsage(output);
            return args.length == 1 ? 0 : 2;
        }

        final String source;
        try {
            source = args.length == 1
                    ? Files.readString(Path.of(args[0]), StandardCharsets.UTF_8)
                    : new String(input.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException | RuntimeException ex) {
            error.println("scanner: unable to read input: " + ex.getMessage());
            if (args.length == 0) return 2;
            return 2;
        }

        ScanResult result = new Lexer(source).scanTokens();
        output.println("=== Tokens ===");
        for (Token token : result.tokens()) output.println(token);

        if (!result.errors().isEmpty()) {
            error.println("=== Lexical errors ===");
            for (LexicalError diagnostic : result.errors()) error.println(diagnostic);
        }
        output.printf("Scanned source: %d token(s), %d lexical error(s).%n",
                Math.max(0, result.tokens().size() - 1), result.errors().size());
        return result.hasErrors() ? 1 : 0;
    }

    private static void printUsage(PrintStream output) {
        output.println("Usage: scanner [source-file]");
        output.println("With no source-file, read UTF-8 source text from standard input.");
    }
}
