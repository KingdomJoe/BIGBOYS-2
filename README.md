# BIGBOYS-2 — Compiler Project, Phase 1: Scanner

**Language:** Java 17+  
**Build tool:** Maven  
**Phase 1 deadline listed in the assignment PDF:** Sunday, 25 October 2026

This repository is intended to hold **all five phases** of the compiler project over the term. Phase 1 delivers a lexical scanner (lexer): it reads source text and produces a stream of tokens with source positions, plus lexical diagnostics for invalid input.

> **Important: confirm the language rules.** The attached two-page assignment-instructions PDF gives project logistics and deadlines; it does not define the class mini-language's complete token list or lexical rules. This starter uses an explicitly documented, conventional baseline (below). Before submission, compare it with the mini-language taught in class and adjust it to match the lecturer's actual specification. Do not assume the illustrative language here supersedes class notes.

## Current starter scope

- Keywords and identifiers, with keyword matching separated from identifier rules.
- Integer, decimal and scientific-notation numeric literals (numbers require a digit before a decimal point; for example, \`0.5\`, not \`.5\`).
- Quoted string literals with \`\\\\\`, \`\\"\`, \`\\n\`, \`\\r\`, \`\\t\` and \`\\0\` escapes.
- Common arithmetic, assignment, comparison, logical, bitwise and shift operators; common punctuation.
- \`//\` line comments and \`/* ... */\` block comments.
- Line/column reporting, recoverable lexical errors, and an explicit \`EOF\` token.
- Maven/JUnit tests, sample input files and a JDK-only smoke-test script.

The provisional keyword list is \`if\`, \`else\`, \`while\`, \`for\`, \`do\`, \`break\`, \`continue\`, \`return\`, \`int\`, \`float\`, \`double\`, \`bool\`, \`char\`, \`string\`, \`void\`, \`true\`, \`false\`, \`print\`, \`read\`, \`func\`, \`var\`, \`const\`, \`class\`, \`and\`, \`or\`, and \`not\`. The list and operators are design assumptions until checked against the class mini-language specification.

## Quick start

Install **JDK 17 or newer** and **Maven 3.8+**. From this directory:

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

### Run the smoke test without Maven

If Maven is not installed yet, the repository also includes a smoke-test script that compiles production code using \`javac\` and tests both valid and invalid input:

\`\`\`bash
bash scripts/smoke-test.sh
\`\`\`

The automated JUnit suite still requires Maven to resolve its test dependency.

## Project layout

\`\`\`text
BIGBOYS-2/
├── pom.xml
├── README.md
├── CONTRIBUTING.md
├── AI_ASSISTANCE.md
├── docs/
│   └── ASSIGNMENT_CHECKLIST.md
├── scripts/
│   └── smoke-test.sh
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

## Work allocation by index number

This table records the **team's assigned areas**, not a claim that each person has already completed or personally authored the corresponding code. The starter files were AI-assisted and must be reviewed, understood, corrected and committed by the relevant team members. Update the status/evidence column only after checking actual work and Git history.

| Index number | Assigned responsibility | Main files / evidence to maintain |
|---|---|---|
| **0228** | Token structures and shared scanner declarations | \`Token.java\`, \`TokenType.java\`, and agreement on the token API |
| **236** | Keyword recognition | \`KeywordTable.java\`; tests for exact keyword matching |
| **231** | Identifier rules | \`IdentifierRules.java\`; tests for valid and invalid identifier boundaries |
| **234** | Numeric literals | \`NumberRules.java\`; tests for integers, decimals, exponents and malformed numbers |
| **230** | Operators and punctuation | \`OperatorRules.java\`; tests for longest-match behavior and unsupported characters |
| **227** | String literal handling | \`StringRules.java\`; tests for escapes, invalid escapes and unterminated strings |
| **232** | Lexical error handling | \`LexicalError.java\`, \`ScanResult.java\`, and recovery/error-reporting tests |
| **233** | Test files and sample inputs | \`src/test/java/.../LexerTest.java\`, \`tests/inputs/\`, \`tests/README.md\` |
| **229** | Project documentation | \`README.md\`, \`CONTRIBUTING.md\`, and documentation updates |

\`Lexer.java\` and \`Main.java\` integrate these components. The integration in this snapshot is also AI-assisted and is **not attributed to any index number**. The group should review it together and record the actual integration work through real commits or pull requests.

### Reporting actual contributions

For the final submission, maintain a small evidence-backed contribution record: the files or behavior a member changed, the tests they ran, and the relevant commit or pull-request links. An allocation by itself is not evidence that work has been completed. Do not rewrite Git authorship or create commits that falsely appear to have been made by another person.

## Required Git workflow

The assignment PDF requires collaborators to pull the latest \`main\`, create feature branches, commit incrementally, and continue committing as features are completed. See [CONTRIBUTING.md](CONTRIBUTING.md) for commands. Do not use a single final commit to represent all of an individual's work.

## Repository setup notes from the assignment PDF

- The lecturer's document calls for **one private repository for all five phases**, created under the group name **\`COMPILER_GROUP_ONE\`**. This package uses the name you requested, **\`BIGBOYS-2\`**; confirm that the lecturer/group accepts the naming difference before publishing it.
- Add all group members with write access.
- Add the lecturer as a collaborator using \`emmanueldery31@gmail.com\` (as written in the provided PDF).
- Keep visible, incremental commits throughout every phase.
- The listed deadlines after Phase 1 are: Parser — 8 November 2026; Semantic Analysis / IR — 22 November 2026; Code Generation — 6 December 2026; Optimizer + Final Demo — 13 December 2026.

## AI-assistance / academic-integrity note

The supplied PDF permits certain AI uses (such as boilerplate, documentation and debugging suggestions) but says AI must not generate graded solutions or design/implement core algorithms. You stated that the lecturer has since changed that rule, but the uploaded PDF does not include the updated permission. Review [AI_ASSISTANCE.md](AI_ASSISTANCE.md), follow the lecturer's current instructions, and disclose AI assistance inline wherever required by the current policy. The current source files have explicit AI-assistance comments; do not represent the initial scaffold as unaided student work.
