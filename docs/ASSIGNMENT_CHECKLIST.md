# Assignment checklist from the supplied PDF

This checklist summarises logistics from the two-page document \`Assignment Instruction(1).pdf\`. The PDF does not define the detailed token grammar for the class mini-language.

## Phase 1: Scanner

- Due date shown: **25 October 2026**.
- Use Java/Maven for this group's agreed implementation (JDK 17+ is recommended in the lecturer's document).
- Make the first working scanner feature available by the deadline and continue with incremental feature commits.
- Check token/keyword/number/string/operator rules against the actual class mini-language specification.
- Test valid programs, malformed literals, unknown characters, comments and source locations.
- Document build/run steps and known limitations.

## Chronological implementation order

The README's **Chronological implementation order** section is the authoritative detailed schedule. The dependency-aware sequence is:

| Order | Owner(s) | Responsibility / files |
|---|---|---|
| 0 | Group decision; no index assigned | Confirm the class mini-language rules before treating provisional token rules as final |
| 1 — first code | **0228** | Define shared token contract: \`TokenType.java\`, \`Token.java\` |
| 2 | 236 and 231 | Keyword and identifier helpers: \`KeywordTable.java\`, \`IdentifierRules.java\` |
| 3 | 234 and 230 | Numeric and operator helpers: \`NumberRules.java\`, \`OperatorRules.java\` |
| 4 | 227 and 232 | String, lexical-error and scan-result contracts: \`StringRules.java\`, \`LexicalError.java\`, \`ScanResult.java\` |
| 5 | **Not allocated in the original list; group must appoint** | Integrate components into \`Lexer.java\` and \`Main.java\` |
| 6 | 233 | Draft tests early; finish end-to-end JUnit cases and example inputs once integration exists |
| 7 | 229 | Draft docs early; finalise setup instructions, actual test evidence and contribution notes last |

Feature owners can work in parallel after Stage 1's token contract is agreed and committed. The order shown above is the dependency-aware merge/integration sequence, not a requirement that independent contributors wait idly. The original allocation does not say who owns \`Lexer.java\` or \`Main.java\`; the group must explicitly appoint an integrator or agree to integrate collectively rather than assuming a member owns it.

## Repository roles

- **BIGBOYS-2:** the experimental development workspace and active source of truth during scanner review/refinement.
- **COMPILER_GROUP_ONE:** the official, private shared submission repository requested by the assignment. It should contain all five phases and is where the full group and lecturer should be invited.
- The lecturer email supplied in the PDF is \`emmanueldery31@gmail.com\`.
- When transferring the work, copy reviewed source/configuration/documentation and retain accurate AI disclosures. Do not copy the experimental repository's commit history as if it were made by group members, and don't claim AI scaffold commits as student contributions.

## Repository-wide requirements for the official repository

- One shared private repository for all five phases.
- Add all group members as collaborators with write access.
- Add the lecturer as a collaborator.
- Maintain visible, incremental commits throughout each phase; repository history is checked.
- Follow the lecturer's current AI-use policy and disclosure requirements. The attached PDF contains the older restrictive policy described in \`AI_ASSISTANCE.md\`.

## Later phase dates shown in the PDF

| Phase | Deliverable | Deadline |
|---|---|---|
| 2 | Parser | 8 November 2026 |
| 3 | Semantic Analysis / IR | 22 November 2026 |
| 4 | Code Generation | 6 December 2026 |
| 5 | Optimizer + Final Demo | 13 December 2026 |

Phase 5 also requires a sample program in the class mini-language and evidence that the group's finished compiler compiles and runs it, according to the Phase 5 assignment sheet.
