# Assignment checklist from the supplied PDF

This checklist summarises logistics from the two-page document \`Assignment Instruction(1).pdf\`. The PDF does not define the detailed token grammar for the class mini-language.

## Phase 1: Scanner

- Due date shown: **25 October 2026**.
- Use Java/Maven for this group's agreed implementation (JDK 17+ is recommended in the lecturer's document).
- Make the first working scanner feature available by the deadline and continue with incremental feature commits.
- Check token/keyword/number/string/operator rules against the actual class mini-language specification.
- Test valid programs, malformed literals, unknown characters, comments and source locations.
- Document build/run steps and known limitations.

## Repository roles

- **BIGBOYS-2:** the separate experimental development workspace and active source of truth during the scanner review/refinement period. Continue working here for now.
- **COMPILER_GROUP_ONE:** the official, private shared submission repository requested by the assignment. It should contain all five phases and is where the full group and lecturer should be invited.
- The lecturer email supplied in the PDF is \`emmanueldery31@gmail.com\`.
- When transferring the work, copy reviewed source/configuration/documentation and retain accurate AI disclosures. Do not copy the experimental repository's commit history as if it were made by the group members, and don't claim AI scaffold commits as student contributions.

## Repository-wide requirements for the official repository

- One shared private repository for all five phases.
- Add all group members as collaborators with write access.
- Add the lecturer as a collaborator.
- Maintain visible, incremental commits throughout each phase; repository history is checked.
- Follow the lecturer's current AI-use policy and disclosure requirements. The PDF attached to this conversation contains the older restrictive policy described in \`AI_ASSISTANCE.md\`.

## Later phase dates shown in the PDF

| Phase | Deliverable | Deadline |
|---|---|---|
| 2 | Parser | 8 November 2026 |
| 3 | Semantic Analysis / IR | 22 November 2026 |
| 4 | Code Generation | 6 December 2026 |
| 5 | Optimizer + Final Demo | 13 December 2026 |

Phase 5 also requires a sample program in the class mini-language and evidence that the group's finished compiler compiles and runs it, according to the Phase 5 assignment sheet.
