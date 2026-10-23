#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
BUILD_DIR="$(mktemp -d)"
trap 'rm -rf "$BUILD_DIR"' EXIT
mkdir -p "$BUILD_DIR/classes"
find "$ROOT/src/main/java" -name '*.java' -print > "$BUILD_DIR/sources.txt"

javac --release 17 -d "$BUILD_DIR/classes" @"$BUILD_DIR/sources.txt"
java -cp "$BUILD_DIR/classes" gh.edu.bigboys.scanner.Main "$ROOT/tests/inputs/basic-program.lang" > "$BUILD_DIR/valid.out" 2> "$BUILD_DIR/valid.err"
grep -q 'TYPE_INT' "$BUILD_DIR/valid.out"
grep -q 'STRING_LITERAL' "$BUILD_DIR/valid.out"
grep -q '0 lexical error(s)' "$BUILD_DIR/valid.out"

set +e
printf '%s\n' 'print("bad\q");' | java -cp "$BUILD_DIR/classes" gh.edu.bigboys.scanner.Main > "$BUILD_DIR/invalid.out" 2> "$BUILD_DIR/invalid.err"
STATUS=$?
set -e
if [[ "$STATUS" -eq 0 ]]; then
    echo 'FAIL: invalid input unexpectedly passed' >&2
    exit 1
fi
grep -q 'Unknown escape sequence' "$BUILD_DIR/invalid.err"

printf '%s\n' 'x++ >= 2 && y != 0 || !ok <<= 1' | java -cp "$BUILD_DIR/classes" gh.edu.bigboys.scanner.Main > "$BUILD_DIR/operators.out"
grep -q 'PLUS_PLUS' "$BUILD_DIR/operators.out"
grep -q 'GREATER_EQUAL' "$BUILD_DIR/operators.out"
grep -q 'AND_AND' "$BUILD_DIR/operators.out"
grep -q 'SHIFT_LEFT_EQUAL' "$BUILD_DIR/operators.out"

set +e
printf '%s\n' '12e+' | java -cp "$BUILD_DIR/classes" gh.edu.bigboys.scanner.Main > "$BUILD_DIR/number.out" 2> "$BUILD_DIR/number.err"
NUMBER_STATUS=$?
printf '%s\n' '/* unfinished' | java -cp "$BUILD_DIR/classes" gh.edu.bigboys.scanner.Main > "$BUILD_DIR/comment.out" 2> "$BUILD_DIR/comment.err"
COMMENT_STATUS=$?
set -e
[[ "$NUMBER_STATUS" -ne 0 ]]
[[ "$COMMENT_STATUS" -ne 0 ]]
grep -q 'exponent' "$BUILD_DIR/number.err"
grep -q 'Unterminated block comment' "$BUILD_DIR/comment.err"

echo 'PASS: production sources compile with javac; valid input, longest-match operators, malformed numbers, invalid strings and unterminated comments behave as expected.'
