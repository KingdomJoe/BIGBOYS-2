# Contributing to BIGBOYS-2

## Ground rules

1. Keep all five compiler phases in this one repository.
2. Before work, update from \`main\`.
3. Work on a feature branch associated with your assigned task.
4. Make small, meaningful commits as work progresses. Do not batch an entire phase into one last-minute commit.
5. Add tests for behavior changed and run them before requesting review.
6. Review existing code instead of blindly accepting the generated starter. Record AI help according to the lecturer's current policy.
7. Do not claim another person's work or manufacture another person's commit history.

## Standard workflow

\`\`\`bash
git switch main
git pull --ff-only origin main
git switch -c feature/0228-token-model
# make a small change
git status
git diff
git add src/main/java/gh/edu/bigboys/scanner/Token.java src/main/java/gh/edu/bigboys/scanner/TokenType.java
git commit -m "Define scanner token model"
# continue with focused changes and tests
git push -u origin feature/0228-token-model
\`\`\`

Replace the branch name and paths with the work you were assigned. Make more than one commit when you implement, test, refine or fix distinct pieces. Use commit messages that describe what changed, not just \`update\` or \`final code\`.

## Suggested branch names

- \`feature/0228-token-model\`
- \`feature/236-keywords\`
- \`feature/231-identifiers\`
- \`feature/234-numbers\`
- \`feature/230-operators\`
- \`feature/227-strings\`
- \`feature/232-lexical-errors\`
- \`test/233-scanner-cases\`
- \`docs/229-scanner-readme\`

## Before a pull request

\`\`\`bash
mvn clean test
bash scripts/smoke-test.sh
git diff --check
git status
\`\`\`

Describe the behavior changed, tests run, known limitations, and any AI help according to the active policy. Ask another group member to review interactions with the shared token types and lexer. Merge into \`main\` only after review and tests.
