# Scanner test inputs

- \`inputs/basic-program.lang\`: valid illustrative token stream using the provisional keyword/operator set. It tests scanner behavior only; no parser or runtime is included in Phase 1.
- \`inputs/invalid-string.lang\`: deliberately invalid input. The scanner should produce a lexical diagnostic for the unsupported \`\\q\` escape and exit with status \`1\`.

AI-assistance disclosure: these initial sample inputs were drafted with ChatGPT. Index 233 should add edge cases from the actual class mini-language and verify their expected output.
