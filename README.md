# BIGBOYS-2 — Compiler Group 1, Phase 1: Scanner

**Language:** Java 17+  
**Build tool:** Maven  
**Phase 1 deadline listed in the assignment PDF:** Sunday, 25 October 2026

## Repository role

**BIGBOYS-2 is the active development workspace and current source of truth for this scanner implementation.** We are using it to build and review the project before transferring the finished code into the separate official, private submission repository named `COMPILER_GROUP_ONE`.

Continue real development here for now. When the official repository is ready, transfer the reviewed project files and relevant documentation into it, then continue following the lecturer's shared-repository workflow there. This experimental repository is not the place to invite the full group or lecturer for grading; those invitations belong to the official submission repository.

This project is Phase 1 of a compiler. The scanner reads source text and produces tokens with source locations, plus lexical diagnostics for invalid input. The long-term official repository will contain all five phases.

> **Important: confirm the language rules.** The attached assignment-instructions PDF gives project logistics and deadlines; it does not define the class mini-language's complete token list or lexical rules. This starter uses a conventional, provisional baseline. Compare it with the language taught in class and correct it before submission; this README does not override class notes.

## Current scanner scope

- Keywords and identifiers, with keyword lookup separated from identifier rules.
- Integer, decimal and scientific-notation numeric literals.
- Quoted string literals with backslash, quote, newline, carriage-return, tab and NUL escapes.
- Common arithmetic, assignment, comparison, logical, bitwise and shift operators; common punctuation.
- `//` line comments and `/* ... */` block comments.
- Line/column reporting, recoverable lexical errors, and an explicit `EOF` token.
- One Maven/JUnit unit-test class and separate example input files for valid and invalid CLI runs.

The provisional keyword list is `if`, `else`, `while`, `for`, `do`, `break`, `continue`, `return`, `int`, `float`, `double`, `bool`, `char`, `string`, `void`, `true`, `false`, `print`, `read`, `func`, `var`, `const`, `class`, `and`, `or`, and `not`. These are assumptions until checked against the class mini-language specification.

## Chronological implementation order

The assignment's allocation list identifies who owns each area, but it did not explicitly define a chronological build sequence. Use the following dependency-aware order for implementing and integrating the work. **The order below is the expected order of the shared interfaces and integration milestones, not a demand that every contributor sit idle until the previous person finishes.** Once Stage 1's token contract is agreed and committed, independent contributors can work on their own feature branches in parallel, then merge in this order.

### Stage 0 — Confirm the token rules before implementation

**Group decision; no individual index assigned.**

Before extending the provisional starter, check the lecturer's actual mini-language notes and agree on the required keywords, identifier form, operators, numeric formats, string syntax, comments and token names. The uploaded assignment logistics PDF does not supply this lexical grammar. Record decisions so every feature owner implements the same language.

### Stage 1 — Establish the shared token contract

**First code owner: index 0228.**

Files: `TokenType.java`, `Token.java`.

Index 0228 should be first on the implementation critical path because all other scanner components need to agree on token kinds and the token data structure. Commit the token contract before the other components rely on it. Notify other contributors when the public names and fields are stable; later changes to this contract should be coordinated.

**Gate to proceed:** shared token types and token fields are agreed, committed and visible on `main`.

### Stage 2 — Implement keywords and identifiers

**Owners: index 236 (keywords) and index 231 (identifiers).**

Files: `KeywordTable.java`, `IdentifierRules.java`.

These components use the token kinds from Stage 1 and define how a name is recognised, then classified as a keyword or regular identifier. Index 236 and index 231 can work in parallel after the token contract is available.

**Gate to proceed:** keyword matching is distinct from identifier matching and both components use the agreed token names.

### Stage 3 — Implement numbers and operators

**Owners: index 234 (numbers) and index 230 (operators/punctuation).**

Files: `NumberRules.java`, `OperatorRules.java`.

These components can also be developed in parallel after Stage 1. Number rules must document valid forms and malformed-number handling. Operator matching must consistently choose the longest valid symbol where operators share prefixes.

**Gate to proceed:** the numeric and operator token names match `TokenType.java`, with cases prepared for valid and invalid inputs.

### Stage 4 — Implement strings and lexical diagnostics

**Owners: index 227 (strings) and index 232 (error handling).**

Files: `StringRules.java`, `LexicalError.java`, `ScanResult.java`.

Index 227 owns string scanning/escape rules. Index 232 owns the error and result contracts used to return tokens plus diagnostics. Their work depends on the shared token contract from Stage 1. Agree on how errors are represented before the main lexer is integrated.

**Gate to proceed:** string results, lexical errors and scan results have stable interfaces that the integration layer can call.

### Stage 5 — Integrate the lexer and command-line entry point

**Owner: index 236 (in addition to keyword recognition in Stage 2).**

Files: `Lexer.java`, `Main.java`.

Index 236 owns this integration stage as well as the keyword table. After the components from Stages 2–4 are available, index 236 should connect the identifier/keyword, number, operator, string and diagnostic components; track line/column locations; handle comments and whitespace; emit EOF; and let the CLI read a file or standard input. The initial implementation was AI-assisted, so index 236 should review, correct and understand it rather than treating the initial draft as unaided work.

Integration should connect the identifier/keyword, number, operator, string and diagnostic components; track line/column locations; handle comments and whitespace; emit EOF; and let the CLI read a file or standard input.

**Gate to proceed:** the project compiles and a small valid and invalid input can be scanned end-to-end.

### Stage 6 — Complete tests and sample inputs

**Owner: index 233. Start test planning earlier; complete end-to-end tests after Stage 5.**

Files: `src/test/java/gh/edu/bigboys/scanner/LexerTest.java`, `tests/inputs/`, `tests/README.md`.

Index 233 should not wait until the end to think about tests: prepare a test matrix after Stage 1 and add cases alongside each feature. The final integration tests can only be completed once Stage 5 provides a callable scanner. Keep the single JUnit test class in Maven's `src/test/java` tree. The `tests/inputs` files are runnable source examples, not duplicate JUnit classes.

**Gate to proceed:** `mvn clean test` passes, and valid/invalid examples behave as documented. If Maven is not available on a developer machine, install Maven or use CI to execute the JUnit suite; do not report a test as passing unless it ran.

### Stage 7 — Finalise documentation and review

**Owner: index 229. Draft early; finalise after the implementation and test commands are stable.**

Files: `README.md`, `CONTRIBUTING.md`, `docs/ASSIGNMENT_CHECKLIST.md`.

Documentation can be drafted during earlier stages, but index 229 should verify file names, build/run commands, test results, limitations, chronological task ownership and AI-assistance disclosures against the actual repository before the Phase 1 hand-off.

## Build-order summary

| Chronological stage | Owner(s) by index | First files to work on | Dependency / exit condition |
|---|---|---|---|
| 0. Confirm language rules | Group; no index assigned | Class mini-language notes / agreed token list | Rules agreed before changing assumptions |
| 1. Token contract | **0228 first** | `TokenType.java`, `Token.java` | Shared token API committed |
| 2. Keywords + identifiers | 236, 231 | `KeywordTable.java`, `IdentifierRules.java` | Uses Stage 1 token names |
| 3. Numbers + operators | 234, 230 | `NumberRules.java`, `OperatorRules.java` | Uses Stage 1 token names; boundaries tested |
| 4. Strings + diagnostics | 227, 232 | `StringRules.java`, `LexicalError.java`, `ScanResult.java` | Stable results/error interfaces |
| 5. Lexer + CLI integration | **236** (also owns keywords in Stage 2) | `Lexer.java`, `Main.java` | Connects all scanner components after Stages 2–4 |
| 6. Final tests + inputs | 233 (test design starts after Stage 1) | `LexerTest.java`, `tests/inputs/` | End-to-end suite passes |
| 7. Documentation sign-off | 229 (draft early) | `README.md`, `CONTRIBUTING.md` | Commands, status and ownership match actual repo |

## Quick start

Install **JDK 17 or newer** and **Maven 3.8+**. From the project root:

```bash
mvn clean test
mvn exec:java -Dexec.args="tests/inputs/basic-program.lang"
```

To scan text from standard input:

```bash
printf 'int score = 42; print(score);\\n' | mvn exec:java
```

To package a runnable JAR:

```bash
mvn package
java -jar target/bigboys-2-scanner-1.0.0-SNAPSHOT.jar tests/inputs/basic-program.lang
```

A file path argument scans that UTF-8 file. With no argument, the scanner reads all source text from standard input. Tokens are printed to standard output. Lexical diagnostics are printed to standard error; the program exits with status `1` when lexical errors occur and `2` for command-line or input-file errors.

## Project layout

```text
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
```

### Test-file convention

There is one JUnit test class at `src/test/java/gh/edu/bigboys/scanner/LexerTest.java`. This is the canonical automated test suite for the Maven project. The two files in `tests/inputs/` are example source programs, not duplicate Java test classes: the valid program supports a CLI run, while the invalid program demonstrates a lexical error. Keep these separate roles clear when transferring the project to `COMPILER_GROUP_ONE`.

## Work allocation by index number

The following table records the group's current task allocation. Index 236's Stage 5 integration responsibility is an addition to the original keyword-recognition task; the numbered sequence above defines when each component should be built and integrated.

| Index number | Assigned responsibility | Main files / evidence to maintain |
|---|---|---|
| **0228** | Token structures and shared scanner declarations | `Token.java`, `TokenType.java`, token API review |
| **236** | Keyword recognition (Stage 2) and Lexer + CLI integration (Stage 5) | `KeywordTable.java`, `Lexer.java`, `Main.java`; keyword and end-to-end CLI tests |
| **231** | Identifier rules | `IdentifierRules.java`; valid/invalid identifier tests |
| **234** | Numeric literals | `NumberRules.java`; integer, decimal, exponent and malformed-number tests |
| **230** | Operators and punctuation | `OperatorRules.java`; longest-match and unsupported-character tests |
| **227** | String literal handling | `StringRules.java`; escape and unterminated-string tests |
| **232** | Lexical error handling | `LexicalError.java`, `ScanResult.java`, error-recovery tests |
| **233** | Test files and sample inputs | `LexerTest.java`, `tests/inputs/`, `tests/README.md` |
| **229** | Documentation | `README.md`, `CONTRIBUTING.md`, documentation updates |

### Contribution evidence

For the final submission, maintain a contribution record with the behavior changed, tests run, and relevant commit or pull-request links. An allocation by itself is not evidence that work has been completed. Do not rewrite Git authorship or create commits falsely attributed to another person.

## Git workflow

Pull the latest `main`, create your own feature branch, and commit in small, meaningful increments. See [CONTRIBUTING.md](CONTRIBUTING.md). The initial scaffold commits are AI-assisted setup commits, not evidence of student contributions.

## Assignment notes

- The provided PDF names the official shared repository `COMPILER_GROUP_ONE` and calls for it to be private. This `BIGBOYS-2` repository is the separate active development workspace requested for experimentation.
- The official repository should contain all five phases and should be the repository where the full group and lecturer are invited.
- The lecturer's email in the PDF is `emmanueldery31@gmail.com`.
- Later deadlines listed in the PDF: Parser — 8 November 2026; Semantic Analysis / IR — 22 November 2026; Code Generation — 6 December 2026; Optimizer + Final Demo — 13 December 2026.

## AI assistance and academic integrity

The supplied PDF permits certain AI uses (such as boilerplate, documentation and debugging suggestions) but says AI must not generate graded solutions or design/implement core algorithms. You said the lecturer has since changed that rule, but the updated instruction was not included in the uploaded PDF. Follow the lecturer's current instructions and disclose AI assistance inline as required. The current source files have explicit AI-assistance comments; do not represent the initial scaffold as unaided student work.
