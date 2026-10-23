# BIGBOYS-2 — Compiler Group 1, Phase 1: Scanner

**Language:** Java 17+  
**Build tool:** Maven  
**Phase 1 deadline listed in the assignment PDF:** Sunday, 25 October 2026

## Repository role

**BIGBOYS-2 is the active development workspace and current source of truth for this scanner implementation.** We are using it to build and review the project before transferring the finished code into the separate official, private submission repository named \`COMPILER_GROUP_ONE\`.

Continue real development here for now. When the official repository is ready, transfer the reviewed project files and relevant documentation into it, then continue following the lecturer's shared-repository workflow there. This experimental repository is not the place to invite the full group or lecturer for grading; those invitations belong to the official submission repository.

This project is Phase 1 of a compiler. The scanner reads source text and produces tokens with source locations, plus lexical diagnostics for invalid input. The long-term official repository will contain all five phases.

> **Important: confirm the language rules.** The attached two-page assignment-instructions PDF gives project logistics and deadlines; it does not define the class mini-language's complete token list or lexical rules. This starter uses a conventional, provisional baseline. Compare it with the language taught in class and correct it before submission; this README does not override class notes.

## Current scanner scope

- Keywords and identifiers, with keyword lookup separated from identifier rules.
- Integer, decimal and scientific-notation numeric literals.
- Quoted string literals with backslash, quote, newline, carriage-return, tab and NUL escapes.
- Common arithmetic, assignment, comparison, logical, bitwise and shift operators; common punctuation.
- \`//\` line comments and \`/* ... */\` block comments.
- Line/column reporting, recoverable lexical errors, and an explicit \`EOF\` token.
- One Maven/JUnit unit-test class and separate example input files for valid and invalid CLI runs.

The provisional keyword list is \`if\`, \`else\`, \`while\`, \`for\`, \`do\`, \`break\`, \`continue\`, \`return\`, \`int\`, \`float\`, \`double\`, \`bool\`, \`char\`, \`string\`, \`void\`, \`true\`, \`false\`, \`print\`, \`read\`, \`func\`, \`var\`, \`const\`, \`class\`, \`and\`, \`or\`, and \`not\`. These are assumptions until checked against the class mini-language specification.

## Quick start

Install **JDK 17 or newer** and **Maven 3.8+**. From the project root:

\`\`\`bash
mvn clean test
mvn exec:java -Dexec.args="tests/inputs/basic-program.lang"
\`\`\`

To scan text from standard input:

\`\`\`bash
printf 'int score = 42; print(score);\\n' | mvn exec:java
\`\`\`

To package a runnable JAR:

\`\`\`bash
mvn package
java -jar target/bigboys-2-scanner-1.0.0-SNAPSHOT.jar tests/inputs/basic-program.lang
\`\`\`

A file path argument scans that UTF-8 file. With no argument, the scanner reads all source text from standard input. Tokens are printed to standard output. Lexical diagnostics are printed to standard error; the program exits with status \`1\` when lexical errors occur and \`2\` for command-line or input-file errors.

## Project layout

\`\`\`text
BIGBOYS-2/
├── pom.xml
├── README.md
├── CONTRIBUTING.md
├── AI_ASSISTANCE.md
├── docs/
│   └── ASSIGNMENT_CHECKLIST.md
├── src/
│   ├── main/java/gh/edu/bigboys/scanner/
│   │   ├── Main.java
│   │   ├── Lexer.java
│   │   ├── Token.java
│   │   ├── TokenType.java
│   │   ├── ScanResult.java
│   │   ├── LexicalError.java
│   │   ├── KeywordTable.java
│   │   ├── IdentifierRules.java
│   │   ├── NumberRules.java
│   │   ├── OperatorRules.java
│   │   └── StringRules.java
│   └── test/java/gh/edu/bigboys/scanner/LexerTest.java
└── tests/
    ├── README.md
    └── inputs/
        ├── basic-program.lang
        └── invalid-string.lang
\`\`\`

### Test-file convention

There is one JUnit test class at \`src/test/java/gh/edu/bigboys/scanner/LexerTest.java\`. This is the canonical automated test suite for the Maven project. The two files in \`tests/inputs/\` are example source programs, not duplicate Java test classes: the valid program supports a CLI run, while the invalid program demonstrates a lexical error. Keep these separate roles clear when transferring the project to \`COMPILER_GROUP_ONE\`.

## Work allocation by index number

This table records the **team's assigned areas**, not a claim that each person has already completed or personally authored the corresponding code. The initial source was AI-assisted and must be reviewed, understood, corrected and committed by the relevant team members.

| Index number | Assigned responsibility | Main files / evidence to maintain |
|---|---|---|
| **0228** | Token structures and shared scanner declarations | \`Token.java\`, \`TokenType.java\`, token API review |
| **236** | Keyword recognition | \`KeywordTable.java\`; exact keyword tests |
| **231** | Identifier rules | \`IdentifierRules.java\`; valid/invalid identifier tests |
| **234** | Numeric literals | \`NumberRules.java\`; integer, decimal, exponent and malformed-number tests |
| **230** | Operators and punctuation | \`OperatorRules.java\`; longest-match and unsupported-character tests |
| **227** | String literal handling | \`StringRules.java\`; escape and unterminated-string tests |
| **232** | Lexical error handling | \`LexicalError.java\`, \`ScanResult.java\`, error-recovery tests |
| **233** | Test files and sample inputs | \`LexerTest.java\`, \`tests/inputs/\`, \`tests/README.md\` |
| **229** | Documentation | \`README.md\`, \`CONTRIBUTING.md\`, documentation updates |

\`Lexer.java\` and \`Main.java\` integrate these components. Their initial integration is also AI-assisted and is **not attributed to any index number**. Record real contributions through actual edits, tests, commits or pull requests.

### Contribution evidence

For the final submission, maintain a contribution record with the behavior changed, tests run, and relevant commit or pull-request links. An allocation by itself is not evidence that work has been completed. Do not rewrite Git authorship or create commits falsely attributed to another person.

## Git workflow

Pull the latest \`main\`, create your own feature branch, and commit in small, meaningful increments. See [CONTRIBUTING.md](CONTRIBUTING.md). The initial scaffold commits are AI-assisted setup commits, not evidence of student contributions.

## Assignment notes

- The provided PDF names the official shared repository \`COMPILER_GROUP_ONE\` and calls for it to be private. This \`BIGBOYS-2\` repository is the separate active development workspace requested for experimentation.
- The official repository should contain all five phases and should be the repository where the full group and lecturer are invited.
- The lecturer's email in the PDF is \`emmanueldery31@gmail.com\`.
- Later deadlines listed in the PDF: Parser — 8 November 2026; Semantic Analysis / IR — 22 November 2026; Code Generation — 6 December 2026; Optimizer + Final Demo — 13 December 2026.

## AI assistance and academic integrity

The supplied PDF permits certain AI uses (such as boilerplate, documentation and debugging suggestions) but says AI must not generate graded solutions or design/implement core algorithms. You said the lecturer has since changed that rule, but the updated instruction was not included in the uploaded PDF. Follow the lecturer's current instructions and disclose AI assistance inline as required. The current source files have explicit AI-assistance comments; do not represent the initial scaffold as unaided student work.
