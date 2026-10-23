# Contributing to BIGBOYS-2

## Repository role

BIGBOYS-2 is the current active development workspace/source of truth for the scanner while the group reviews and refines this experimental implementation. The separate official submission repository will be named `COMPILER_GROUP_ONE`. Do the ongoing development here for now; transfer reviewed project files into the official repository when it is ready. Invite the full group and lecturer to the official submission repository, not this experimental workspace.

## Required chronological build and merge order

The canonical sequence is documented in the README under **Chronological implementation order**. Follow it when integrating work into `main`: Index 236 has two assigned stages—keywords in Stage 2 and Lexer + CLI integration in Stage 5.

1. **0228 first:** establish and commit the shared token contract in `TokenType.java` and `Token.java`.
2. **236 + 231:** implement the keyword table and identifier rules.
3. **234 + 230:** implement number rules and operator/punctuation matching.
4. **227 + 232:** implement string rules and the lexical-diagnostic/result contracts.
5. **236:** integrate the completed components into `Lexer.java` and `Main.java` (Stage 5). Index 236 owns this in addition to keyword recognition in Stage 2.
6. **233:** finish the end-to-end JUnit tests and sample inputs. Index 233 should draft test cases early after Stage 1 and add cases alongside the other components; the final integration tests depend on Stage 5.
7. **229:** finalise the README and supporting documentation after the actual code, build commands, and test results have been checked. Documentation can be drafted earlier.

Independent component owners may work in parallel after Stage 1's token contract has been agreed and committed. **Parallel development does not change the dependency-aware merge order above.** Merge into `main` only when the relevant public interfaces are stable and downstream components can use them.

Stage 0 also applies before feature development: check the provisional keywords, operators, number rules, strings and comments against the lecturer's actual mini-language notes. The assignment logistics PDF does not contain the complete lexical grammar.

## Ground rules

- Before work, update from `main`.
- Create a feature branch for your assigned component.
- Make small, meaningful commits as work progresses; do not batch a whole phase into one last-minute commit.
- Add or update tests alongside code changes and run the Maven suite before requesting review.
- Review the AI-assisted starter, understand the behavior, and record AI help according to the lecturer's current policy.
- Do not claim another person's work or manufacture another person's commit history.

## Standard workflow

```bash
git switch main
git pull --ff-only origin main
git switch -c feature/0228-token-model
# make a small change
git status
git diff
git add src/main/java/gh/edu/bigboys/scanner/Token.java src/main/java/gh/edu/bigboys/scanner/TokenType.java
git commit -m "Review and refine scanner token model"
# continue with focused changes and tests
git push -u origin feature/0228-token-model
```

Replace the branch name and paths with your assigned task. Make more than one commit when you implement, test, refine or fix distinct pieces. Use messages that describe the change, not just `update` or `final code`.

## Suggested branch names, ordered by build sequence

- `feature/0228-token-model` — first shared contract
- `feature/236-keywords` — Stage 2; may run in parallel with the next item
- `feature/231-identifiers` — Stage 2; may run in parallel with the previous item
- `feature/234-numbers` — Stage 3; may run in parallel with the next item
- `feature/230-operators` — Stage 3; may run in parallel with the previous item
- `feature/227-strings` — Stage 4; may run in parallel with diagnostics
- `feature/232-lexical-errors` — Stage 4; diagnostics/result contract
- `integration/236-lexer-cli` — Stage 5; owned by index 236 after Stages 2–4 components are ready
- `test/233-scanner-cases` — test design begins early; final integration coverage follows Stage 5
- `docs/229-scanner-readme` — documentation drafts early; sign-off is the final stage

Index 236 owns both keyword recognition (Stage 2) and Lexer + CLI integration (Stage 5); begin integration after the interfaces from Stages 2–4 are stable.

## Before merging a pull request

```bash
mvn clean test
git diff --check
git status
```

Describe the behavior changed, tests run, known limitations, and AI help according to the active policy. Ask another group member to review interactions with the shared token types and lexer. Merge into `main` only after review and tests.
