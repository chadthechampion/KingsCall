#!/usr/bin/env bash
# Builds a self-contained build of King's Call with jpackage.
#
#   default        portable app-image -> build/dist/KingsCall/  (run KingsCall / KingsCall.exe)
#   --installer    also build a native installer (needs platform tooling:
#                  WiX on Windows, dpkg/rpmbuild on Linux)
#
# Requires a JDK 17+ (for jpackage) on PATH.
set -euo pipefail
cd "$(dirname "$0")"

VERSION="1.0.0"
JAR="resources/SaxionApp.jar"
BUILD="build"
CLASSES="$BUILD/classes"
STAGE="$BUILD/app-input"
DIST="$BUILD/dist"
ICON="packaging/KingsCall.ico"

echo "==> Cleaning $BUILD/"
rm -rf "$BUILD"
mkdir -p "$CLASSES" "$STAGE" "$DIST"

echo "==> Compiling"
find BasicGame/src -name '*.java' -print0 | xargs -0 javac -cp "$JAR" -d "$CLASSES"

echo "==> Building KingsCall.jar"
printf 'Main-Class: kingscall.KingsCall\nClass-Path: SaxionApp.jar\n' > "$BUILD/manifest.txt"
jar --create --file "$STAGE/KingsCall.jar" --manifest "$BUILD/manifest.txt" -C "$CLASSES" .

echo "==> Staging resources"
cp "$JAR" "$STAGE/SaxionApp.jar"
cp -r resources "$STAGE/resources"
rm -f "$STAGE/resources/SaxionApp.jar"

COMMON=(
    --name KingsCall
    --app-version "$VERSION"
    --vendor KingsCall
    --input "$STAGE"
    --main-jar KingsCall.jar
    --main-class kingscall.KingsCall
    --java-options '-Dkingscall.home=$APPDIR'
    --java-options '-Xmx512m'
    --dest "$DIST"
)
[ -f "$ICON" ] && COMMON+=(--icon "$ICON")

echo "==> jpackage (app-image)"
jpackage --type app-image "${COMMON[@]}"
echo "    -> $DIST/KingsCall/"

if [ "${1:-}" = "--installer" ]; then
    echo "==> jpackage (installer)"
    jpackage "${COMMON[@]}"
fi

echo
echo "Done."
