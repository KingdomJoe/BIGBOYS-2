# Scanner test inputs

These are **sample source programs**, not Java/JUnit test classes. The automated Java unit tests are kept in the single canonical file at \`src/test/java/gh/edu/bigboys/scanner/LexerTest.java\`.

## Input files

- \`inputs/basic-program.lang\`: valid illustrative token stream using the provisional keyword/operator set. It supports an end-to-end scanner/CLI run; it does not test a parser or runtime.
- \`inputs/invalid-string.lang\`: deliberately invalid input. The scanner should report an unknown escape sequence for \`\\q\` and exit with status \`1\`.

## Work sequence for index 233

Test planning should begin **after Stage 1**, when index 0228 has agreed and committed the token contract. From then on, add or refine test cases incrementally while indices 236/231, 234/230 and 227/232 build their components. Once the group-appointed integration owner has connected the components in \`Lexer.java\` and \`Main.java\` (Stage 5 in the main README), complete the end-to-end tests and verify valid/invalid CLI examples.

Keep unit tests under Maven's \`src/test/java\` source tree, and keep runnable language examples under \`tests/inputs/\`. Do not create a second Java test class in \`tests/\` for the same scanner behavior unless the group has a specific, documented reason.

AI-assistance disclosure: these initial sample inputs and the unit-test draft were generated with ChatGPT. Index 233 should review, extend and verify them against the actual class mini-language, while following the lecturer's current AI policy.
