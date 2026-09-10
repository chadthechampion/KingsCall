#!/usr/bin/env bash
# Compiles and runs King's Call. Requires a JDK (17+) on PATH.
set -euo pipefail
cd "$(dirname "$0")"

OUT="out/production/kingscall"
mkdir -p "$OUT"

echo "Compiling..."
find BasicGame/src -name '*.java' -print0 | xargs -0 javac -cp resources/SaxionApp.jar -d "$OUT"

echo "Launching (from project root so resources/ resolves)..."
java -cp "$OUT:resources/SaxionApp.jar" kingscall.KingsCall
