# Compiles and runs King's Call. Requires a JDK (17+) on PATH.
$ErrorActionPreference = "Stop"
$root = $PSScriptRoot
$jar  = Join-Path $root "resources/SaxionApp.jar"
$out  = Join-Path $root "out/production/kingscall"

New-Item -ItemType Directory -Force -Path $out | Out-Null
$sources = Get-ChildItem -Recurse -Path (Join-Path $root "BasicGame/src") -Filter *.java |
    ForEach-Object { $_.FullName }

Write-Host "Compiling..."
& javac -cp $jar -d $out $sources

Write-Host "Launching (run from project root so resources/ resolves)..."
Push-Location $root
try {
    & java -cp "$out;$jar" kingscall.KingsCall
} finally {
    Pop-Location
}
